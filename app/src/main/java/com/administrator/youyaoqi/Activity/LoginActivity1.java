package com.administrator.youyaoqi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import com.administrator.youyaoqi.R;

/**
 * Created by Administrator on 2016/3/10.
 */
public class LoginActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        new Welcome1().execute();
    }

    @Override
    public void onBackPressed() {
    }

    class Welcome1 extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent it = new Intent(LoginActivity1.this, LoginActivity2.class);
            startActivity(it);
            LoginActivity1.this.finish();
        }
    }
}
