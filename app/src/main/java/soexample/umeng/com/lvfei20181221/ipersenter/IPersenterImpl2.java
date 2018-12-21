package soexample.umeng.com.lvfei20181221.ipersenter;

import soexample.umeng.com.lvfei20181221.callback.MyCallBack;
import soexample.umeng.com.lvfei20181221.iview.IView;
import soexample.umeng.com.lvfei20181221.iview.IView2;
import soexample.umeng.com.lvfei20181221.moudle.MyMoudleImpl;
import soexample.umeng.com.lvfei20181221.moudle.MyMoudleimpl2;

public class IPersenterImpl2 implements IPersenter {
    private IView2 iView2;
    private MyMoudleimpl2 myMoudle2;

    public IPersenterImpl2(IView2 iView2) {
        this.iView2 = iView2;
        myMoudle2 = new MyMoudleimpl2();
    }

    @Override
    public void startIPersenter(String url) {
        myMoudle2.startLogins(url, new MyCallBack() {
            @Override
            public void setSuccess(Object data) {
                //成功添加上数据
                iView2.success2(data);
            }

            @Override//失败回调
            public void setError(Object error) {
                iView2.error2(error);
            }
        });
    }

    //防止内存泄漏
    public void onDatacth() {
        if (myMoudle2 != null) {
            myMoudle2 = null;
        }
        if (iView2 != null) {
            iView2 = null;
        }
    }
}
