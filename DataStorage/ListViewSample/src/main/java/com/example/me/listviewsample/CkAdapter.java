package com.example.me.listviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/12/28.
 */
public class CkAdapter extends BaseAdapter {
    // 填充数据的list
    private ArrayList<HashMap> list;
    // 用来控制CheckBox的选中状况
    // private static HashMap<Integer,Boolean> isSelected;
    private String[] districts, postcodes;
    // 上下文
    private Context context;
    // 用来导入布局
    private LayoutInflater inflater = null;

    // 构造器
    public CkAdapter(ArrayList<HashMap> list, Context context) {
        this.context = context;
        this.list = list;

        inflater = LayoutInflater.from(context);
        //  isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        // initDate();
    }

    // 初始化isSelected的数据
    // private void initDate(){
    //      for(int i=0; i<districts.length-1;i++) {
    //        getIsSelected().put(i,false);
    //     }
    // }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();

            // 导入布局并赋值给convertview
            convertView = inflater.inflate(R.layout.list_view, null);
            holder.tv1 = (TextView) convertView.findViewById(R.id.tv_district);
            holder.tv2 = (TextView) convertView.findViewById(R.id.tv_postcode);
            holder.cb = (CheckBox) convertView.findViewById(R.id.ck_select);
            // 为view设置标签
            convertView.setTag(holder);

        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }


        // 设置list中TextView的显示
        holder.tv1.setText((String) ((HashMap) list.get(position)).get("district"));
        holder.tv2.setText((String) ((HashMap) list.get(position)).get("postcode"));
        // 根据isSelected来设置checkbox的选中状况
        holder.cb.setChecked((Boolean) ((HashMap) list.get(position)).get("true"));
        return convertView;
    }

    private static class ViewHolder {
        TextView tv1;
        TextView tv2;
        CheckBox cb;
    }
}

