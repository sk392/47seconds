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

//                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//                String login_check = pref.getString("login_check", "false");
//
//                Log.d("LoginActivity.class", "login_check : "+ login_check);
//                if ("true".equals(login_check)) {
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(intent);
//                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
//                }
                finish();
            }
        }, 2000);
    }
}
