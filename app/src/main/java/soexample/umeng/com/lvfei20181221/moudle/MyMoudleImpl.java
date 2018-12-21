package soexample.umeng.com.lvfei20181221.moudle;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import soexample.umeng.com.lvfei20181221.bean.MyJiuData;
import soexample.umeng.com.lvfei20181221.bean.MyYinDaoData;
import soexample.umeng.com.lvfei20181221.callback.MyCallBack;
import soexample.umeng.com.lvfei20181221.okhttputils.MyOkHttpUtils;

public class MyMoudleImpl implements MyMoudle {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String jsons = (String) msg.obj;
            Gson gson = new Gson();
            MyJiuData myJiuData = gson.fromJson(jsons, MyJiuData.class);
            myCallBack.setSuccess(myJiuData);
        }
    };

    private MyCallBack myCallBack;

    @Override
    public void startLogin(String url, MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        MyOkHttpUtils.getIntence().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.sendMessage(handler.obtainMessage(0,response.body().string()));
            }
        });
    }
}
