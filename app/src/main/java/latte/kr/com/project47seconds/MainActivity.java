package latte.kr.com.project47seconds;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.Switch;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import static latte.kr.com.project47seconds.MediaPlayerService.ACTION_PLAY;


public class MainActivity extends AppCompatActivity {
    private LineChart lineChart;
    private NotificationCompat.Builder mBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ((LinearLayout) findViewById(R.id.ll_main)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((LinearLayout) findViewById(R.id.ll_main_news)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KeywordSelectActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = null;
        PendingIntent pendingIntent = null;

        intent = new Intent(ACTION_PLAY);
        pendingIntent = PendingIntent.getService(getApplicationContext(),
                0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notifi_message_layout);
        contentView.setImageViewResource(R.id.image, R.drawable.icon_144);
        contentView.setTextViewText(R.id.title, "47 Seconds 오늘의 뉴스");
        contentView.setTextViewText(R.id.text1, "오늘의");
        contentView.setTextViewText(R.id.text2, " 핫토픽 ");
        contentView.setTextViewText(R.id.text3, "뉴스를 알려드립니다!");
        contentView.setTextViewText(R.id.text4, "클릭");
        contentView.setTextViewText(R.id.text5, "해주세요!");

        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_144)
                .setContent(contentView)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setOngoing(true);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());

        chartInit();
    }


    public void chartInit() {
        lineChart = (LineChart) findViewById(R.id.graph_profile);
        lineChart.animateY(2000);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        ArrayList<Entry> values = new ArrayList<Entry>();

        // i : 데이터 10개
        values.add(new Entry(1, 147));
        values.add(new Entry(2, 300));
        values.add(new Entry(3, 273));
        values.add(new Entry(4, 192));
        values.add(new Entry(5, 85));
        values.add(new Entry(6, 170));
        SharedPreferences sharedPreferences =getSharedPreferences("main",MODE_PRIVATE);
        values.add(new Entry(7, sharedPreferences.getInt("time",0)));


        LineDataSet d = new LineDataSet(values, "상식을 쌓아올린 시간");
        d.setLineWidth(1f);
        d.setCircleRadius(4f);
        dataSets.add(d);


        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
    }




}
