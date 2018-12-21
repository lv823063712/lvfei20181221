package soexample.umeng.com.lvfei20181221;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;

import soexample.umeng.com.lvfei20181221.base.BaseActivity;
import soexample.umeng.com.lvfei20181221.iview.IView;

public class MainActivity extends BaseActivity implements IView {

    private FlyBanner my_fly;
    private String[] imgs = {"http://www.zhaoapi.cn/images/quarter/ad1.png",
            "http://www.zhaoapi.cn/images/quarter/ad2.png",
            "http://www.zhaoapi.cn/images/quarter/ad3.png",
            "http://www.zhaoapi.cn/images/quarter/ad4.png"};
    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        my_fly = findViewById(R.id.My_Fly);
        initData();
    }

    private void initData() {
        for (int i = 0; i < imgs.length; i++) {
            datas.add(imgs[i]);
        }
        my_fly.setImagesUrl(datas);
        my_fly.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void setOnClick() {

    }

    @Override
    protected void proLogic() {

    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void error(Object error) {

    }
}
