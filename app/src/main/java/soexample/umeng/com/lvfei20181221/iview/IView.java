package soexample.umeng.com.lvfei20181221.iview;

public interface IView<T> {

    void success(T data);

    void error(T error);
}
