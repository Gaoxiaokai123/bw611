package com.bawei.gaochenkai20190611.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.gaochenkai20190611.R;
import com.bawei.gaochenkai20190611.bean.JsonBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * @Auther: 高晨凯
 * @Date: 2019/6/11 09:40:42
 * @Description:  xlistview的适配器
 */
public class MyXListviewAdapter extends BaseAdapter {
    private ArrayList<JsonBean> list;
    private Context context;

    public MyXListviewAdapter(ArrayList<JsonBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = View.inflate(context,R.layout.item,null);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.item_name);
            holder.time = view.findViewById(R.id.item_time);
            holder.tite = view.findViewById(R.id.item_tite);
            holder.image = view.findViewById(R.id.item_image);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
            holder.name.setText(list.get(i).getTheme_name());
            holder.time.setText(list.get(i).getPasstime());
            holder.tite.setText(list.get(i).getText());
        Glide.with(context).load(list.get(i).getProfile_image()).into(holder.image);
        return view;
    }

    static class ViewHolder{
        TextView name;
        TextView tite;
        TextView time;
        ImageView image;
    }
}
