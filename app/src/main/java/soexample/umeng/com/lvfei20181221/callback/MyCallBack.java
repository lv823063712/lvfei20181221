package soexample.umeng.com.lvfei20181221.callback;

public interface MyCallBack<T> {

    void setSuccess(T data);

    void setError(T error);
}
