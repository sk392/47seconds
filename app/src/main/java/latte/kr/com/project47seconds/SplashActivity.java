package latte.kr.com.project47seconds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //자동로그인.
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
        SharedPreferences sharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        int time=0;
        try{
            sharedPreferences.getInt("time",0);

        }catch (Exception e){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("time",0);
            editor.commit();
        }
    }
}
