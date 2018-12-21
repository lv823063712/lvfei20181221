package soexample.umeng.com.lvfei20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import soexample.umeng.com.lvfei20181221.R;
import soexample.umeng.com.lvfei20181221.bean.MyListData;

public class MyGoodsAdapter extends RecyclerView.Adapter<MyGoodsAdapter.ViewHolder> {
    private ArrayList<MyListData.DataBean> dataBeans;
    private int flag = 1;
    private Context context;
    private View v;
    private View v1;

    public MyGoodsAdapter(ArrayList<MyListData.DataBean> dataBeans, int flag) {
        this.dataBeans = dataBeans;
        this.flag = flag;
    }

    //接口回调
    public void setItemOnClickCallBack(itemOnClick item) {
        this.item = item;
    }

    public interface itemOnClick {
        void ItemOnClick(int pos);
    }

    private ArrayList<MyListData.DataBean> datas = null;
    private itemOnClick item;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        if (flag == 1) {
            //布局切换
            v = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.gride, viewGroup, false);
        }
        return new ViewHolder(v);
    }

    @Override//适配器优化
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.myName.setText(datas.get(i).getTitle());
        viewHolder.myPrice.setText(datas.get(i).getPrice());
        String url = datas.get(i).getImages();
        if (!url.isEmpty()){
            String[] split = url.split("\\|");
            if (split!=null){
                for (int j = 0; j < split.length; j++) {
                    String replace = split[j].replace("https", "http");
                    Glide.with(context).load(replace).into(viewHolder.imageView);
                }
            }else{
                String replace = url.replace("https", "http");
                Glide.with(context).load(replace).into(viewHolder.imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView myName;
        private final TextView myPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.My_Img);
            myName = itemView.findViewById(R.id.My_Name);
            myPrice = itemView.findViewById(R.id.My_Price);

        }
    }
}
