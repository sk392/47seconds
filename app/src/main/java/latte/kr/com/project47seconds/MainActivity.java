package latte.kr.com.project47seconds;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import static latte.kr.com.project47seconds.MediaPlayerService.ACTION_PLAY;


public class MainActivity extends AppCompatActivity {
    Button btMainLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btMainLogin = (Button) findViewById(R.id.bt_main_login);

        btMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent svc=new Intent(MainActivity.this, MediaPlayerService.class);
                startService(svc);

                /*
                SpeechPost speechPost = new SpeechPost();
                speechPost.setCallbackEvent(new SpeechPost.CallbackEvent() {
                    @Override
                    public void getResult(String result) {

                        soundUri = Uri.parse(result);
                        MediaPlayer mpintro = MediaPlayer.create(getApplicationContext(), soundUri);
                        mpintro.start();
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
                });
                speechPost.execute("");*/
            }
        });
        Intent intent = null;
        PendingIntent pendingIntent = null;

        intent = new Intent(ACTION_PLAY);
        pendingIntent = PendingIntent.getService(getApplicationContext(),
                0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notifi_message_layout);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        contentView.setTextViewText(R.id.title, "47s 오늘의 뉴스");
        contentView.setTextViewText(R.id.text, "탭을 통해 오늘의 IT / 경제 뉴스를 알려드립니다.");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_loop_black_36dp)
                .setContent(contentView)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setOngoing(true);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }



}
