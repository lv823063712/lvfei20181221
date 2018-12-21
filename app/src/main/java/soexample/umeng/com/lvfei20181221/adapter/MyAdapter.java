package soexample.umeng.com.lvfei20181221.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import soexample.umeng.com.lvfei20181221.R;
import soexample.umeng.com.lvfei20181221.bean.MyJiuData;

public class MyAdapter extends BaseAdapter {
    private ArrayList<MyJiuData.ResultsBean> datas;
    private Context context;

    public MyAdapter(ArrayList<MyJiuData.ResultsBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public ArrayList<MyJiuData.ResultsBean> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<MyJiuData.ResultsBean> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.jiugongge,null);
            viewHolder.img = convertView.findViewById(R.id.Image);
            viewHolder.tv = convertView.findViewById(R.id.Title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(datas.get(position).getType());
        Glide.with(context).load(datas.get(position).getUrl()).into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView tv;
    }
}
