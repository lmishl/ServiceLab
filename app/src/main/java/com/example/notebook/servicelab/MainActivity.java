package com.example.notebook.servicelab;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private MyBroadcastReceiver mMyBroadcastReceiver;
    private myBroadcastReceiver  myRec;

    MyService2 mService2;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myRec);

    }


    public void countPrime(View view)
    {
        // выводим сообщение
        // Toast.makeText(this, "Зачем вы нажали?", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        EditText text1 = (EditText)findViewById(R.id.editText);
        Intent intent = new Intent(MainActivity.this, MyService.class);
        int number = Integer.parseInt(text1.getText().toString());
        intent.putExtra("number", number);

        startService(intent);

       /* EditText text1 = (EditText)findViewById(R.id.editText);
        EditText text2 = (EditText)findViewById(R.id.editText2);
        // в ключ username пихаем текст из первого текстового поля
        intent.putExtra("text1", text1.getText().toString());
        intent.putExtra("text2", text2.getText().toString());
        startActivityForResult(intent, 0);*/
    }

    public void countPrimeInt(View view)
    {


        EditText text1 = (EditText)findViewById(R.id.editText);
        Intent intent = new Intent(MainActivity.this, MyIntentService.class);
        int number = Integer.parseInt(text1.getText().toString());
        intent.putExtra("number", number);


        myRec = new myBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_RESULT);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(myRec, intentFilter);

        startService(intent);


        //startService(intent);

     //   mMyBroadcastReceiver = new MyBroadcastReceiver();

        // регистрируем BroadcastReceiver
//        IntentFilter intentFilter = new IntentFilter(
//                MyIntentService.ACTION_MYINTENTSERVICE);
//        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
//        registerReceiver(mMyBroadcastReceiver, intentFilter);

    }

    public void countPrimeBind(View view)
    {


        EditText text1 = (EditText)findViewById(R.id.editText);
        int number = Integer.parseInt(text1.getText().toString());


        Intent intent = new Intent(MainActivity.this, MyService2.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        if (mBound) {
            // Call a method from the LocalService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            int res = mService2.getPrime(number);
            Toast.makeText(this, "answer: " + res, Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "не забиндился", Toast.LENGTH_SHORT).show();


    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyService2.LocalBinder binder = (MyService2.LocalBinder) service;
            mService2 = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    private void showResultDialog(String result) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(result);
        dlgAlert.setTitle("123");
        dlgAlert.create().show();
    }


    public class myBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int res = intent.getIntExtra(MyIntentService.RESULT, 1);

            Toast.makeText(getApplicationContext(), "Ответ " + res, Toast.LENGTH_SHORT).show();
            showResultDialog("Ответ " + res);
        }
    }


}
