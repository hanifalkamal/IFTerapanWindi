package com.aplikasi.distaru.bandung;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by HanifAlKamal on 19/11/2017.
 */

public class BootService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
            Toast.makeText(context, "Boot completed!", Toast.LENGTH_SHORT).show();

          //  Services sr = new Services();
         //   Intent a = new Intent(context,KirimLokasi.class);
          //  context.startActivity(a);

            Intent i = new Intent(context,Services.class);
            context.startService(i);



        }
    }

}
