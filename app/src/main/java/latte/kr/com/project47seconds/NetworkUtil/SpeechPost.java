package latte.kr.com.project47seconds.NetworkUtil;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by sk392 on 2016-10-28.
 */

public class SpeechPost extends AsyncTask<String, Void, String> {
    //
    private static final String TAG = "SpeechPost";
    int sleepTime = 0;

    private String url;
    //결과 값
    private String res;
    private Context mContext;
    private boolean isLoadingImg = true;
    private CallbackEvent callbackEvent;




    public interface CallbackEvent {
        void getResult(String result);
    }

    public SpeechPost(Context context) {
        this.mContext = context;
        callbackEvent = null;

    }

    public void setCallbackEvent(CallbackEvent callbackEvent) {
        this.callbackEvent = callbackEvent;
    }

    public String getUrl() {
        return url;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void onPostExecute(String s) {

        callbackEvent.getResult(s);
        Log.d("SpeechPost", "Result =" + s);
    }

    public String getRes() {
        return res;
    }


    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public String doInBackground(String... params) {
        StringBuffer response = new StringBuffer();
        String clientId;//애플리케이션 클라이언트 아이디값";
        String clientSecret;//애플리케이션 클라이언트 시크릿값";
        clientId = "IK6n9BUXsIgzYMJlHnoH";
        clientSecret = "wHoKohKE5L";
        String filepath = "";
        Log.d("SpeechPost", "Context = " + mContext.getClass().getName() + " / Request =" + params[0].toString());
        if (NetworkUtil.getConnectivityStatus(mContext) != NetworkUtil.TYPE_NOT_CONNECTED) {
            //네트워크가 연결되었을 경우만.

            try {
                String text = URLEncoder.encode("2017-07-29 오늘의 뉴스입니다", "UTF-8"); // 13자
                String apiURL = "https://openapi.naver.com/v1/voice/tts.bin";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                // post request
                String postParams = "speaker=jinho&speed=2&text=" + text;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) { // 정상 호출
                    InputStream is = con.getInputStream();
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    // 랜덤한 이름으로 mp3 파일 생성
                    String tempname = Long.valueOf(new Date().getTime()).toString();
                    File path = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_MOVIES);
                    filepath = path + "/"+tempname + ".mp3";
                    File f = new File(path + "/"+tempname + ".mp3");
                    f.createNewFile();
                    OutputStream outputStream = new FileOutputStream(f);
                    while ((read = is.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                    is.close();

                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine);
                    }
                    br.close();
                    System.out.println(response.toString());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            res = filepath;
            return res;
        } else {
            //네트워크가 연결되어 있지 않을 때.
            res = "{'err':'100', 'err_result':'네트워크 연결을 확인해주세요.'}";
            return res;
        }
    }

    public boolean isLoadingImg() {
        return isLoadingImg;
    }

    public void setLoadingImg(boolean loadingImg) {
        isLoadingImg = loadingImg;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}