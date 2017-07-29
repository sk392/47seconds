package latte.kr.com.project47seconds;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import latte.kr.com.project47seconds.NetworkUtil.SpeechPost;

/**
 * Created by SK392 on 2017-07-29.
 */

public class MediaPlayerService extends Service {

    public static final String ACTION_STOP = "latte.kr.com.project47seconds.ACTION_STOP";
    public static final String ACTION_PLAY = "latte.kr.com.project47seconds.ACTION_PLAY";
    public static final String ACTION_PAUSE = "latte.kr.com.project47seconds.ACTION_PAUSE";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TATAT","onStartCommand");

        if (intent != null) {
            SpeechPost speechPost = new SpeechPost();
            speechPost.setCallbackEvent(new SpeechPost.CallbackEvent() {
                @Override
                public void getResult(String result) {

                    MediaPlayer mpintro = MediaPlayer.create(getApplicationContext(), Uri.parse(result));
                    mpintro.start();
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                }
            });
            speechPost.execute("");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TATAT","onStartCommand");

        return null;
    }

    private void startPlay() {
        Log.d("TATAT","startPlay()");
        // do the play work here


    }

    private void pausePlay() {
        // do the pause work here
    }

    private void stopPlay() {
        // do the stop work here
    }
}