package latte.kr.com.project47seconds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import latte.kr.com.project47seconds.NetworkUtil.SendPost;
import latte.kr.com.project47seconds.NetworkUtil.SendPut;


/*
 * 카테고리구별 [  정치 : 1 , 경제 : 2, 사회 : 3, IT: 4 ]
 */

public class KeywordSelectActivity extends AppCompatActivity {


    private Button btOk;
    //    private Button btKeywordIt, btKeywordSociety, btKeywordPolitics, btKeywordEconomics;
    private Button[] btKeyword;
    private int[] requestKeywords;

    static int KEYWORD_NUM = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_select);

        init();

    }

    private void init() {
        btOk = (Button) findViewById(R.id.bt_keyword_ok);

        btKeyword = new Button[4];
        btKeyword[0] = (Button) findViewById(R.id.bt_keyword_politics);
        btKeyword[1] = (Button) findViewById(R.id.bt_keyword_economics);
        btKeyword[2] = (Button) findViewById(R.id.bt_keyword_society);
        btKeyword[3] = (Button) findViewById(R.id.bt_keyword_it);

        requestKeywords = new int[]{0, 0, 0, 0};

        btKeyword[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestKeywords[0] == 0) {
                    requestKeywords[0] = 1;
                    btKeyword[0].setBackgroundResource(R.drawable.btn_politics_clicked);
                } else {
                    requestKeywords[0] = 0;
                    btKeyword[0].setBackgroundResource(R.drawable.btn_politics);
                }
            }
        });
        btKeyword[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestKeywords[1] == 0) {
                    requestKeywords[1] = 1;
                    btKeyword[1].setBackgroundResource(R.drawable.btn_economy_clicked);
                } else {
                    requestKeywords[1] = 0;
                    btKeyword[1].setBackgroundResource(R.drawable.btn_economy);
                }
            }
        });
        btKeyword[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestKeywords[2] == 0) {
                    requestKeywords[2] = 1;
                    btKeyword[2].setBackgroundResource(R.drawable.btn_social_clicked);
                } else {
                    requestKeywords[2] = 0;
                    btKeyword[2].setBackgroundResource(R.drawable.btn_social);
                }
            }
        });
        btKeyword[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestKeywords[3] == 0) {
                    requestKeywords[3] = 1;
                    btKeyword[3].setBackgroundResource(R.drawable.btn_it_clicked);
                } else {
                    btKeyword[3].setBackgroundResource(R.drawable.btn_it);
                    requestKeywords[3] = 0;
                }
            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Log.d("TAG", requestKeywords[0] + "");
                Log.d("TAG", requestKeywords[1] + "");
                Log.d("TAG", requestKeywords[2] + "");
                Log.d("TAG", requestKeywords[3] + "");
                SendPut sendPut = new SendPut(getApplicationContext());
                sendPut.setUrl("http://220.230.114.8:3447/mypage/category");
                sendPut.setCallbackEvent(new SendPut.CallbackEvent() {
                    @Override
                    public void getResult(String result) {

                    }
                });
                SharedPreferences sharedPreferences = getSharedPreferences("main", MODE_PRIVATE);
                sendPut.setTokenData(sharedPreferences.getString("token", ""));
                sendPut.execute("category1=" + requestKeywords[0]+"&category2="+ requestKeywords[1]+"&category3="+ requestKeywords[2]+"&category4="+ requestKeywords[3]);
                Intent intent = new Intent(KeywordSelectActivity.this, MainActivity.class);

=======
                test();
                Intent intent = new Intent(KeywordSelectActivity.this, HomeActivity.class);
>>>>>>> 3fce49359a8b87914ca7805cae89be86b8096353
                intent.putExtra("keywords", requestKeywords);
                startActivity(intent);
                finish();
            }
        });

    }

}