package latte.kr.com.project47seconds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import latte.kr.com.project47seconds.NetworkUtil.SendPost;
import latte.kr.com.project47seconds.flipanimation.ApplyRotation;

/*
 * 카테고리구별 [  정치 : 1 , 경제 : 2, 사회 : 3, IT: 4 ]
 */

public class KeywordSelectActivity extends AppCompatActivity {


    private Button btOk;
//    private Button btKeywordIt, btKeywordSociety, btKeywordPolitics, btKeywordEconomics;
    private Button[] btKeyword;
    private int[] requestKeywords;

    static int KEYWORD_NUM = 4;
    private ApplyRotation mApplyRotation[];
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


        mApplyRotation = new ApplyRotation[KEYWORD_NUM];


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
                    btKeyword[3].setBackgroundResource(R.drawable.btn_it_clicked);
                    requestKeywords[3] = 0;
                }
            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
                Intent intent = new Intent(KeywordSelectActivity.this, MainActivity.class);
                intent.putExtra("keywords", requestKeywords);
                startActivity(intent);
            }
        });

    }

    private void test() {
        String request = "";
        for (int i = 0; i < 4; i ++) {
            request += requestKeywords[i];
        }
        Log.i("**request", request);
    }



    // 서버에 카테고리 선택 요청보냄
    // requestKeywords
    private void sendRequest() {
        String request = "";
        for (int i = 0; i < 4; i ++) {
            request += requestKeywords[0];
        }
        Log.i("**request", request);

        SendPost sendPost = new SendPost(getApplicationContext());
        sendPost.setUrl("http://52.78.166.21:3000/test");
        sendPost.setCallbackEvent(new SendPost.CallbackEvent() {
            @Override
            public void getResult(String result) {
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }
        });
        sendPost.execute("data:Helloworld");
    }
}
