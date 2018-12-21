package soexample.umeng.com.lvfei20181221;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;

import soexample.umeng.com.lvfei20181221.adapter.MyAdapter;
import soexample.umeng.com.lvfei20181221.adapter.MyGoodsAdapter;
import soexample.umeng.com.lvfei20181221.base.BaseActivity;
import soexample.umeng.com.lvfei20181221.bean.MyJiuData;
import soexample.umeng.com.lvfei20181221.bean.MyListData;
import soexample.umeng.com.lvfei20181221.ipersenter.IPersenterImpl;
import soexample.umeng.com.lvfei20181221.ipersenter.IPersenterImpl2;
import soexample.umeng.com.lvfei20181221.iview.IView;
import soexample.umeng.com.lvfei20181221.iview.IView2;

public class Main2Activity<T> extends BaseActivity implements IView<T>, IView2, View.OnClickListener {
    private String mUrl = "https://gank.io/api/data/福利/10/1";
    private String myUrl = "http://www.zhaoapi.cn/product/searchProducts?keywords=电脑&page=";
    private GridView My_GV;
    private XRecyclerView My_XRecy;
    private ImageView saoyisao;
    private EditText My_Search;
    private ImageView gride;
    private IPersenterImpl iPersenter;
    private MyAdapter myAdapter;
    private ArrayList<MyJiuData.ResultsBean> datas = new ArrayList<>();
    private ArrayList<MyListData.DataBean> lists = new ArrayList<>();
    private int index = 1;
    private int flag = 1;
    private IPersenterImpl2 iPersenterImpl2;
    private MyGoodsAdapter myGoodsAdapter;
    private int REQUEST_CODE_SCAN;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        My_GV = findViewById(R.id.My_GV);
        myAdapter = new MyAdapter(datas, this);
        My_GV.setAdapter(myAdapter);

        My_XRecy = findViewById(R.id.My_XRecy);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        My_XRecy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        My_XRecy.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        My_XRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override//刷新
            public void onRefresh() {
                datas.clear();
                index = 1;
                iPersenterImpl2.startIPersenter(mUrl + 1);
                My_XRecy.refreshComplete();
            }

            @Override//加载
            public void onLoadMore() {
                index++;
                iPersenterImpl2.startIPersenter(mUrl + index);
                My_XRecy.loadMoreComplete();
            }
        });
        myGoodsAdapter = new MyGoodsAdapter(lists, flag);
        My_XRecy.setAdapter(myGoodsAdapter);
        //扫一扫的实现
        saoyisao = findViewById(R.id.saoyisao);
        My_Search = findViewById(R.id.My_Search);
        gride = findViewById(R.id.gride);


    }

    @Override
    protected void setOnClick() {
        saoyisao.setOnClickListener(this);
        My_Search.setOnClickListener(this);
        gride.setOnClickListener(this);
    }

    @Override
    protected void proLogic() {
        iPersenter = new IPersenterImpl(this);
        iPersenterImpl2 = new IPersenterImpl2(this);
        iPersenter.startIPersenter(mUrl);

    }

    @Override
    public void success(T data) {
        MyJiuData myJiuData = (MyJiuData) data;
        //将数据添加进集合
        datas.addAll(myJiuData.getResults());
        //刷新适配器
        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void error(T error) {

    }

    private void setMyAdapter(int flag) {
        if (flag == 1) {
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            My_XRecy.setLayoutManager(manager);
            myGoodsAdapter = new MyGoodsAdapter(lists, flag);
            My_XRecy.setAdapter(myGoodsAdapter);
        } else if (flag == 1) {
            GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            My_XRecy.setLayoutManager(manager);
            myGoodsAdapter = new MyGoodsAdapter(lists, flag);
            My_XRecy.setAdapter(myGoodsAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gride:
                if (flag == 1) {
                    gride.setImageResource(R.drawable.buju2);
                    flag = 2;
                } else {
                    gride.setImageResource(R.drawable.buju1);
                    flag = 1;
                }
                setMyAdapter(flag);
                break;
            case R.id.saoyisao:
                if (!initPermission()) {
                    new AlertDialog.Builder(Main2Activity.this)
                            .setMessage("没有开启摄像机权限，是否去设置开启？")
                            .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //调用系统内部去开启权限
                                    ApplicationInfo(Main2Activity.this);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                    return;
                }
                //跳到扫一扫页面
                Intent intent = new Intent(Main2Activity.this, CaptureActivity.class);

                break;

        }
    }

    @Override
    public void success2(Object data) {
        MyListData data1 = (MyListData) data;
        lists.addAll(data1.getData());
        myGoodsAdapter.notifyDataSetChanged();

    }

    @Override
    public void error2(Object error) {

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //key值都约束好了
                String content = data.getStringExtra(Constant.CODED_CONTENT);

            }
        }
    }

    //查看是否开启摄像头权限
    private boolean initPermission() {
        //需要在Android里面找到你要开的权限
        String permissions = Manifest.permission.CAMERA;
        boolean ret = false;
        //Android 6.0以上才有动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//permission granted 说明权限开了
            ret = PermissionChecker.checkSelfPermission(Main2Activity.this, permissions) == PermissionChecker.PERMISSION_GRANTED;
        }
        return ret;
    }

    public static void ApplicationInfo(Activity activity) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
            }
            activity.startActivity(localIntent);
        } catch (Exception e) {
        }
    }


}
