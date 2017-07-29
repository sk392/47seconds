package latte.kr.com.project47seconds;

import android.media.MediaPlayer;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import latte.kr.com.project47seconds.NetworkUtil.SendPost;
import latte.kr.com.project47seconds.NetworkUtil.SpeechPost;


public class MainActivity extends AppCompatActivity {
    Button btMainLogin;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btMainLogin = (Button)findViewById(R.id.bt_main_login);

        btMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendPost sendPost = new SendPost(getApplicationContext());
                sendPost.setUrl("http://52.78.166.21:3000/test");
                sendPost.setCallbackEvent(new SendPost.CallbackEvent() {
                    @Override
                    public void getResult(String result) {
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                    }
                });
                sendPost.execute("data:Helloworld");
                /*SpeechPost speechPost = new SpeechPost(getApplicationContext());
                speechPost.setCallbackEvent(new SpeechPost.CallbackEvent() {
                    @Override
                    public void getResult(String result) {

                        MediaPlayer mPlayer;
                        mPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(result));

                        mPlayer.setLooping(false);
                        mPlayer.start();
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                    }
                });
                speechPost.execute("");*/
            }
        });
    }
}
