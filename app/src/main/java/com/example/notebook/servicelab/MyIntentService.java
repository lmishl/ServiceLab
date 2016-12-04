package com.example.notebook.servicelab;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.widget.Toast;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    public static final String ACTION_RESULT = "ServiceLab.MyIntentService.action.RESULT";

    public static final String RESULT = "RESULT";


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int N = intent.getIntExtra("number",1);
            int res = getPrime(N);
            //Toast.makeText(this, "Ответ " + res, Toast.LENGTH_SHORT).show();
            //stopSelf();

            Intent responseIntent = new Intent();
            responseIntent.setAction(ACTION_RESULT);
            responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
            responseIntent.putExtra(RESULT, res);
            sendBroadcast(responseIntent);

        }
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
