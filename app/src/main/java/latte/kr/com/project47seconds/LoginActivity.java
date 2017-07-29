package latte.kr.com.project47seconds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;


// 네이버 아이디로 로그인

public class LoginActivity extends AppCompatActivity {

        //네이버 로그인 API에 필요한 값과 구성요소들을 선언
        private static String OAUTH_CLIENT_ID = "NlOMyRmPxwImpV4arNxj";
        private static String OAUTH_CLIENT_SECRET = "jceKItmB58";
        private static String OAUTH_CLIENT_NAME = "네이버 아이디로 로그인";
        private OAuthLoginButton btOAuthLoginButton;
        private OAuthLogin mOAuthLoginModule;
        Context mContext = LoginActivity.this;

        private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean b) {
                if (b) {
                    String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                    Toast.makeText(LoginActivity.this, accessToken, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    String errorCode = mOAuthLoginModule.getLastErrorCode(mContext).getCode();
                    String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                    Toast.makeText(LoginActivity.this,"errorcode: "+errorCode+", errordecs:"
                            +errorDesc,Toast.LENGTH_SHORT).show();
                }
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_login);

            //네이버 로그인 인스턴스 초기화
            mOAuthLoginModule = OAuthLogin.getInstance();
            mOAuthLoginModule.init(
                    LoginActivity.this,
                    OAUTH_CLIENT_ID,
                    OAUTH_CLIENT_SECRET,
                    OAUTH_CLIENT_NAME
            );

            //로그인 버튼을 생성하고 동작을 지정
            btOAuthLoginButton = (OAuthLoginButton)findViewById(R.id.bt_oauth_login);
            btOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
            btOAuthLoginButton.setBgResourceId(R.drawable.btn_login);

            Button tempoBtn = (Button) findViewById(R.id.bt_login_temporary);
            tempoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Intent intent = new Intent(LoginActivity.this, KeywordSelectActivity.class);
                    startActivity(intent);
                }
            });
        }
//
//        public void tempoOnClick(View view) {
//            if (view.getId() == R.id.bt_login_temporary) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        }
    }
