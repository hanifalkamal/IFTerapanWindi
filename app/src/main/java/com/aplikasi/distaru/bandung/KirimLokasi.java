package com.aplikasi.distaru.bandung;

import android.support.v7.app.AppCompatActivity;

import com.kosalgeek.asynctask.AsyncResponse;

import java.util.HashMap;

/**
 * Created by HanifAlKamal on 19/11/2017.
 */

public class KirimLokasi extends AppCompatActivity implements AsyncResponse {
    //LocationManager locMan;


    public void exeUrl(String UrlLink){
        HashMap postData = new HashMap();

        postData.put("mobile","android");
        postData.put("txtUsername", "abc");
        postData.put("txtPassword", "123");

        httpRequest hr = new httpRequest(this , postData);

        hr.execute(UrlLink);
        //hr.execute("http://www.iasiitenasbdg.com/kc/insert_lokasi.php?macaddress=111&latitude="+lat+"&longitude="+longi+"&area="+area+"&lokasi="+lokasi+"&nama_jalan="+namaJalan);
        // PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
        //progressDialog.dismiss();
        // task.execute("http://www.iasiitenasbdg.com/kc/insert_lokasi.php?macaddress=111&latitude=asd&longitude=zxc&area=ppp&lokasi=asd&nama_jalan=asdw");
    }
    @Override
    public void processFinish(String s) {

    }


}
