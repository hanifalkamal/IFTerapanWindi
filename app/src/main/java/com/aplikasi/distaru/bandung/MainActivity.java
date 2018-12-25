package com.aplikasi.distaru.bandung;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aplikasi.distaru.bandung.fragment.fr_perwalDistaru;
import com.aplikasi.distaru.bandung.fragment.fr_renstra;
import com.aplikasi.distaru.bandung.fragment.fr_ppDistaru;
import com.aplikasi.distaru.bandung.fragment.fr_baganDistaru;
import com.aplikasi.distaru.bandung.fragment.fr_visiMisiDistaru;
import com.aplikasi.distaru.bandung.fragment.teknopolis;
import com.aplikasi.distaru.bandung.fragment.fr_home;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AsyncResponse,View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{


    private static final int REQUEST_LOCATION = 1 ;
    Button btn, btn_start,btn_stop,btn_coba,Next;
    Double a,b;
    TextView tv,id;
    LocationManager locMan;
    String latitude,longitude,Area,Lokasi,SendUrl;
    private BroadcastReceiver broadcastReceiver;
    private ProgressDialog progressDialog;
    private SparseIntArray mErrorString ;

    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;


    private ActionBarDrawerToggle mToggle;
    private Fragment f_home, f_misi, f_pp, f_renstra, f_perwal,f_baganDistaru,f_tekno;

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

//                    tv.append("\n"+intent.getExtras().get("coordinates"));
                    //tv.append("\n"+intent.getExtras().get("coordinates"));
                    //SendUrl= (String) intent.getExtras().get("coordinates");
                    //SendUrl = SendUrl.replace(" ","%20");
                   // exeUrl(SendUrl);
                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mErrorString = new SparseIntArray();
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        setContentView(R.layout.act_nav);
        //First Popup Menu


        if (isFirstRun){
            // Place your dialog code here to display the dialog

           // Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG).show();
            Intent in = new Intent(this, FormDaftar.class);
            startActivity(in);
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();

        }
        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE},REQUEST_LOCATION);

        tv = (TextView)findViewById(R.id.tv_location);
        btn_start=(Button)findViewById(R.id.bt_start);
        btn_stop=(Button)findViewById(R.id.bt_stop);
        btn_coba=(Button)findViewById(R.id.bt_coba);
        id=(TextView)findViewById(R.id.tv_id);

        ButterKnife.bind(this);

        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);
        mDrawer.addDrawerListener(mToggle);

        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav.setNavigationItemSelectedListener(this);

        f_home = fr_home.newInstance();
        f_misi = fr_visiMisiDistaru.newInstance();
        f_baganDistaru = fr_baganDistaru.newInstance();
        f_perwal = fr_perwalDistaru.newInstance();
        f_pp = fr_ppDistaru.newInstance();
        f_tekno =  teknopolis.newInstance();
        f_renstra = fr_renstra.newInstance();

        changeFragment(f_home, "INFORMATIKA ITENAS");


            if(!runtime_permissions()) {
                //   enable_button();
                Intent i = new Intent(getApplicationContext(),Services.class);
                startService(i);
            }

    }

    private void changeFragment(Fragment f, String title){
        setTitle(title);
        FragmentTransaction trxfr = getSupportFragmentManager().beginTransaction();
        trxfr.replace(R.id.frame_layout, f);
        trxfr.commit();
        mDrawer.closeDrawer(GravityCompat.START);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.menuHome:
                changeFragment(f_home, "HOME");
                break;
            case R.id.menuVisiMisi:
                changeFragment(f_misi, "VISI MISI");
                break;

            case R.id.menuPp:
                changeFragment(f_pp, "PP DISTARU");
                break;

            case R.id.menuRenstrat:
                changeFragment(f_renstra, "RENSTRA REVISI");
                break;

            case R.id.menuPerwal:
                changeFragment(f_perwal, "PERWAL");
                break;

            case R.id.menuBagan:
                changeFragment(f_baganDistaru, "BAGAN");
                break;

            case R.id.menuTekno:
                changeFragment(f_tekno, "TEKNOPOLIS");
                break;

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void enable_button() {
        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(),Services.class);
                startService(i);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Services.class);
                stopService(i);
            }
        });

        btn_coba.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
        /*
                StringBuilder sb = new StringBuilder();

                TelephonyManager telMan = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                sb.append("IMEI: "+ telMan.getDeviceId() + "\n");

                sb.append("Android ID: "+ Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID + "\n"));

                WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                String macaddr = wm.getConnectionInfo().getMacAddress();
                if(macaddr != null){
                    sb.append("WLAN MACADDR : " + macaddr + "\n");
                } else {
                    sb.append("WLAN MACADD NOT SUPPORTED");
                }

                id.setText(sb.toString()); */

            }
        });


    }

    //public void exeUrl(Double lat, Double longi, String area , String lokasi, String namaJalan){


    private boolean runtime_permissions(){
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission
        .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_PHONE_STATE},100);
            return true;
        } else {
            return false;
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if ( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                enable_button();
            }else{
                runtime_permissions();
            }

        }

    }



    @Override
    public void onClick(View view) {
        locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else if (locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                latitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                String mac = new String("Mac");
                String lat = new String("lat");
                String lng = new String("lng");
                String area = new String("area");
                String loca = new String("loca");
                String n_jalan = new String("n_jalan");

                HashMap postData = new HashMap();
                postData.put("mobile","android");
                postData.put("txtMacAddr",mac);
                postData.put("txtLatitude",lat);

                postData.put("txtLongitude",lng);
                postData.put("txtArea",area);
                postData.put("txtLoc",loca);
                postData.put("txtNamJal",n_jalan);

                PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
               // task.execute("http://192.168.43.3/kc/insert_lokasi.php?macaddress=111&latitude=asd&longitude=zxc&area=ppp&lokasi=asd&nama_jalan=asdw");

                tv.setText("Your current location is"+ "\n" + "Lattitude = " + latitude
                        + "\n" + "Longitude = " + longitude);

            }else{
                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void processFinish(String result) {


    }
}
