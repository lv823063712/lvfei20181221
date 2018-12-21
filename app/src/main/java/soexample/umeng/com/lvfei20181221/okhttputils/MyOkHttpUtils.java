package soexample.umeng.com.lvfei20181221.okhttputils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MyOkHttpUtils {

    private OkHttpClient okHttpClient;

    public MyOkHttpUtils() {
        this.okHttpClient = new OkHttpClient();
    }

    public static MyOkHttpUtils getIntence() {
        return ViewHolder.myokhttp;
    }

    static class ViewHolder {
        private static final MyOkHttpUtils myokhttp = new MyOkHttpUtils();
    }

    public void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
