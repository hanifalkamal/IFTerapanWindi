package com.aplikasi.distaru.bandung;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;

import java.util.HashMap;

//import static com.aplikasi.distaru.bandung.R.id.sp_jabatan;

/**
 * Created by HanifAlKamal on 20/11/2017.
 */

public class FormDaftar extends AppCompatActivity implements AsyncResponse, View.OnClickListener {
    private String array_spinner[], arrayJurusan[];
    Button btNext;
    String nama, nrp, jurusan, lokasi, imei, url, url19;
    EditText edNama, edNrp, edJurusan;
    Spinner spBidang, spJabatan;
    Services srv = new Services();
    private static final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_daftar);
        edNama = (EditText) findViewById(R.id.ed_nama);
        edNrp = (EditText) findViewById(R.id.ed_nrp);
        edJurusan = (EditText) findViewById(R.id.ed_jurusan);
        // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE},REQUEST_LOCATION);

//        array_spinner=new String[3];
//        array_spinner[0]="Perencanaan Tata Ruang Kota";
//        array_spinner[1]="Pemanfaatan Tata Ruang Kota";
//        array_spinner[2]="Pengawasan Dan Pemanfaatan Ruang";
//
//        arrayJurusan=new String[2];
//        arrayJurusan[0]="Kepala";
//        arrayJurusan[1]="Seksi";


//        spBidang = (Spinner) findViewById(R.id.sp_bidang);
//        ArrayAdapter adapter = new ArrayAdapter(this,
//                android.R.layout.simple_spinner_item, array_spinner);
//        spBidang.setAdapter(adapter);
//
//        spJabatan = (Spinner) findViewById(sp_jabatan);
//        ArrayAdapter adp_jabatan = new ArrayAdapter(this,
//                android.R.layout.simple_spinner_item, arrayJurusan);
//        spJabatan.setAdapter(adp_jabatan);

        btNext = (Button) findViewById(R.id.bt_next);
        btNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), Services.class);
        startService(i);
        // String tes;
        StringBuilder sb = new StringBuilder();

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

        nama = String.valueOf(edNama.getText());
        nrp = String.valueOf(edNrp.getText());
        jurusan = String.valueOf(edJurusan.getText());
        lokasi = srv.getLokasi();
        HashMap postData = new HashMap();
        httpRequest hr = new httpRequest(this, postData);
        //postData.put("mobile","android");
        //postData.put("txtUsername", "abc");
        //postData.put("txtPassword", "123");
        //Toast.makeText(this, String.valueOf(edNama.getText()), Toast.LENGTH_LONG).show();


            url = "http://if-lab.itenas.ac.id/itenas/insert_siswa.php?" +
                    "macaddress=" + imei +
                    "&nama=" + nama +
                    "&nrp=" + nrp +
                    "&jurusan=" + jurusan +
                    "&lokasi=" + lokasi;
            url = url.replace(" ", "%20");

            if (nama.matches("")) {
                Toast.makeText(FormDaftar.this, "Form ini Wajib Di isi !!!!", Toast.LENGTH_LONG).show();
            } else {
                hr.execute(url);
            }


    }



    @Override
    public void processFinish(String s) {
        //if (String.valueOf(edNama.getText()).isEmpty() != false) {
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
    }

    public String getUrl() {
        return url;
    }
}
