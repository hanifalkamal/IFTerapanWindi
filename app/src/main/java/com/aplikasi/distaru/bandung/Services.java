package com.aplikasi.distaru.bandung;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by HanifAlKamal on 15/11/2017.
 */

public class Services extends Service {

    TelephonyManager telephonymanager;
    LocationManager locationManager;
    LocationListener listener;
    String namaJalan, area, lokasi, imei, Url;

    //private static final int REQUEST_LOCATION = 1 ;


    @Override
    public void onCreate() {
        //Toast.makeText(this, "122. . .", Toast.LENGTH_LONG).show();
        StringBuilder sb = new StringBuilder();
        //ActivityCompat.requestPermissions(ActivityCompat.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE},1);
        TelephonyManager telMan = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        sb.append(telMan.getDeviceId());
        //imei = sb.toString();
        imei = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        //sb.append("Android ID: "+ Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID + "\n"));

        /*
        WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        String macaddr = wm.getConnectionInfo().getMacAddress();
        if(macaddr != null){
            sb.append("WLAN MACADDR : " + macaddr + "\n");
        } else {
            sb.append("WLAN MACADD NOT SUPPORTED");
        }*/

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                try {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    namaJalan = addresses.get(0).getThoroughfare();
                    area = addresses.get(0).getLocality();
                    lokasi = addresses.get(0).getSubLocality();
                    //imei =sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent("location_update");
               // Toast.makeText(this, "333. . .", Toast.LENGTH_LONG).show();
                //i.putExtra("coordinates","Your current location is"+ "\n" + "Lattitude = " + location.getLatitude()
                //        + "\n" + "Longitude = " + location.getLongitude() + "\n"+ "nama jalan = "+namaJalan + "\n"+ "area = "+area + "\n"+ "lokasi = "+lokasi);

                i.putExtra("coordinates","http://if-lab.itenas.ac.id/itenas/insert_lokasi.php?" +
                                "macaddress="+imei+""+
                                "&latitude="+location.getLatitude()+"" +
                                "&longitude="+location.getLongitude()+"" +
                                "&area="+area+"" +
                                "&lokasi="+lokasi+"" +
                                "&nama_jalan="+namaJalan);


               // exeUrl();
                sendBroadcast(i);
                KirimLokasi KL = new KirimLokasi();
                Url = "http://if-lab.itenas.ac.id/itenas/insert_lokasi.php?" +
                        "macaddress="+imei+""+
                        "&latitude="+location.getLatitude()+"" +
                        "&longitude="+location.getLongitude()+"" +
                        "&area="+area+"" +
                        "&lokasi="+lokasi+"" +
                        "&nama_jalan="+namaJalan;
                Url = Url.replace(" ","%20");
                KL.exeUrl(Url);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        };
       // Toast.makeText(this, "333. . .", Toast.LENGTH_LONG).show();
        telephonymanager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);


        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,listener);

    }

    public String getLokasi(){
        return lokasi;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stop. . .", Toast.LENGTH_LONG).show();
        if(locationManager != null){
            locationManager.removeUpdates(listener);
        }

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

      //  Toast.makeText(this, "Service Started. . .", Toast.LENGTH_LONG).show();

        return START_STICKY;
    }






    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
