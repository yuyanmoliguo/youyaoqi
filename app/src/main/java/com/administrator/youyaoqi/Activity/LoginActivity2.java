package com.administrator.youyaoqi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.administrator.youyaoqi.R;

/**
 * Created by Administrator on 2016/3/10.
 */
public class LoginActivity2 extends Activity implements Animation.AnimationListener{

    private ImageView iv_login_x1;
    private ImageView iv_login_x2;
    private ImageView iv_login_x3;
    private TranslateAnimation animation1;
    private TranslateAnimation animation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        iv_login_x1 = (ImageView)this.findViewById(R.id.iv_login_x2);
        iv_login_x2 = (ImageView)this.findViewById(R.id.iv_login_x1);
        iv_login_x3 = (ImageView)this.findViewById(R.id.iv_login_x3);

        float x1 = iv_login_x1.getX();
        float y1 = iv_login_x1.getY();

        animation1 = new TranslateAnimation(x1,x1-700,y1,y1+600);
        animation1.setFillAfter(true);
        animation1.setDuration(1500);


        animation2 = new TranslateAnimation(x1,x1-700,y1,y1+600);
        animation2.setFillAfter(true);
        animation2.setDuration(1300);


        Animation animation3 = new TranslateAnimation(x1,x1-700,y1,y1+600);
        animation3.setFillAfter(true);
        animation3.setDuration(1500);
        iv_login_x3.setVisibility(View.VISIBLE);
        iv_login_x3.setAnimation(animation3);

        animation3.setAnimationListener(this);

        new Welcome2().execute();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        iv_login_x1.setVisibility(View.VISIBLE);
        iv_login_x2.setVisibility(View.VISIBLE);
        iv_login_x2.setAnimation(animation2);
        iv_login_x1.setAnimation(animation1);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    class Welcome2 extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent it = new Intent(LoginActivity2.this, WelcomeActivity.class);
            startActivity(it);
            LoginActivity2.this.finish();
        }
    }


}
