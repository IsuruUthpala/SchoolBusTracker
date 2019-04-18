package com.example.isuru.des;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Isuru on 8/5/2018.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      //  return super.onStartCommand(intent, flags, startId);
        Toast.makeText(MyService.this,"Service Started",Toast.LENGTH_LONG).show();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        Toast.makeText(MyService.this,"Service Destroyed",Toast.LENGTH_LONG).show();

    }
}
