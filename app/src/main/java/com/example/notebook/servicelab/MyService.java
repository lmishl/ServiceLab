package com.example.notebook.servicelab;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int N = intent.getIntExtra("number",1);
        int res = getPrime(N);
        Toast.makeText(this, "Ответ " + res, Toast.LENGTH_SHORT).show();
        stopSelf();
        return Service.START_NOT_STICKY;
    }

    public void onCreate()
    {
        Toast.makeText(this, "Служба создана", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Служба остановлена",
                Toast.LENGTH_SHORT).show();
    }

    int getPrime(int N)
    {
        int count = 0;
        int prime = 0;
        for(int i = 2; count < N; i++ )
        {
            boolean isPrime = true;
            for(int j = 2; j < i; j++)
            {
                if(i % j == 0)
                    isPrime = false;

            }

            if(isPrime)
            {
                prime = i;
                count++;
            }

        }

        return prime;
    }



}
