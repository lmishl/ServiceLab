package com.example.notebook.servicelab;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService2 extends Service {
    public MyService2() {
    }

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService2 getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService2.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public int getPrime(int N)
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
