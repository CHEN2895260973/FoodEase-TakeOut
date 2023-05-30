package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodease.R;
import com.example.order.bean.OrderBean;

import java.util.List;

public class OrderFileAdapter extends BaseAdapter {
    private Context mContext;

    private LayoutInflater mInflate;
    private List<OrderBean> mData;
    public OrderFileAdapter(Context context, LayoutInflater mInflate, List<OrderBean> mData) {
        this.mContext = context;
        this.mInflate = mInflate;
        this.mData = mData;
    }
    /**
     * 设置数据更新界面
     */
    public void setData(List<OrderBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
    /**
     * 获取条目的总数
     */
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }
    /**
     * 根据position得到对应条目的对象
     */
    @Override
    public OrderBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }
    /**
     * 根据position得到对应条目的Id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 得到相应position对应的条目视图，position是当前条目的位置，
     * convertView参数是滚出屏幕的条目的视图
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.order_item,
                    null);
            vh.tv_food_name = convertView.findViewById(R.id.tv_food_name);
            vh.tv_count = convertView.findViewById(R.id.tv_count);
            vh.tv_money = convertView.findViewById(R.id.tv_money);
            vh.iv_food_pic = convertView.findViewById(R.id.iv_food_pic);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的条目数据对象
        final OrderBean bean = getItem(position);
        if (bean != null) {
            vh.tv_food_name.setText(bean.getFoodName());
            vh.tv_count.setText("x"+bean.getCount());
            vh.tv_money.setText("￥"+bean.getPrice());
            vh.iv_food_pic.setImageResource(bean.getFoodPic());
        }
        return convertView;
    }


    class ViewHolder {
        public TextView tv_food_name, tv_count, tv_money;
        public ImageView iv_food_pic;
    }
}