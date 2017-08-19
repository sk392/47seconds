package latte.kr.com.project47seconds;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import latte.kr.com.project47seconds.NetworkUtil.SendGet;
import latte.kr.com.project47seconds.NetworkUtil.SendPost;


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
                String token = accessToken;// 네이버 로그인 접근 토큰;
                String header = "Bearer " + token; // Bearer 다음에 공백 추가
                final SendGet sendGet = new SendGet();
                sendGet.setNaverAPi(true);
                sendGet.setUrl("https://openapi.naver.com/v1/nid/me");
                sendGet.setCallbackEvent(new SendGet.CallbackEvent() {
                    @Override
                    public void getResult(String result) {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            jsonObject.getJSONObject("response").getString("email");
                            jsonObject.getJSONObject("response").getString("nickname");
                            jsonObject.getJSONObject("response").getString("id");
                            SendPost sendPost = new SendPost(getApplicationContext());
                            sendPost.setUrl("http://220.230.114.8:3447/login/login");
                            sendPost.setCallbackEvent(new SendPost.CallbackEvent() {
                                @Override
                                public void getResult(String result) {
                                    JSONObject jsonObject1 = null;
                                    try {
                                        jsonObject1 = new JSONObject(result);

                                        SendGet sendGet1 = new SendGet();
                                        sendGet1.setNaverAPi(false);
                                        sendGet1.setUrl("http://220.230.114.8:3447/login/check");
                                        sendGet1.setCallbackEvent(new SendGet.CallbackEvent() {
                                            @Override
                                            public void getResult(String result) {
                                                try {
                                                    JSONObject jsonObject2 = new JSONObject(result);
                                                    if (jsonObject2.getJSONObject("data").getString("check").equals("0")){
                                                        Intent intent = new Intent(getApplicationContext(),KeywordSelectActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }else{

                                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        sendGet1.setTokenData(jsonObject1.getString("token"));
                                        sendGet1.execute("");
                                        SharedPreferences sharedPreferences =getSharedPreferences("main",MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("token",jsonObject1.getString("token"));
                                        editor.commit();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            sendPost.execute("email=" + jsonObject.getJSONObject("response").getString("email") +
                                    "&nickname=" + jsonObject.getJSONObject("response").getString("nickname") +
                                    "&uid=" + jsonObject.getJSONObject("response").getString("id"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                sendGet.setTokenData(header);
                sendGet.execute("");
/*
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
*/
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
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
        btOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.bt_oauth_login);
        btOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        btOAuthLoginButton.setBgResourceId(R.drawable.btn_login);

    }
}
