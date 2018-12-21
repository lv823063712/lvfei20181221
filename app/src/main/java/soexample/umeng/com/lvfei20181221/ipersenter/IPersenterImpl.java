package soexample.umeng.com.lvfei20181221.ipersenter;

import soexample.umeng.com.lvfei20181221.callback.MyCallBack;
import soexample.umeng.com.lvfei20181221.iview.IView;
import soexample.umeng.com.lvfei20181221.moudle.MyMoudleImpl;

public class IPersenterImpl implements IPersenter {
    private IView iView;
    private MyMoudleImpl myMoudle;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        myMoudle = new MyMoudleImpl();
    }

    @Override
    public void startIPersenter(String url) {
        myMoudle.startLogin(url, new MyCallBack() {
            @Override
            public void setSuccess(Object data) {
                iView.success(data);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }

    //防止内存泄漏
    public void onDatacth() {
        if (myMoudle != null) {
            myMoudle = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
