package com.example.shop.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.order.bean.ShopBean;
import com.example.shop.fragment.AdBannerFragment;

import java.util.List;

public class AdBannerAdapter extends FragmentStateAdapter {

    //存放店铺的列表（也是广告的Fragment列表，因为一个店铺就一个广告Fragment）
    private List<ShopBean> shopBeanList; //适配器的数据对象

    //构造函数
    public AdBannerAdapter(FragmentManager fragmentManager,Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    //设置广告数据并更新界面，也就是给类变量赋值
    public void setData(List<ShopBean> sbl) {
        //将网络上访问到的 ShopBean 的数量（广告数量）赋值给适配器
        this.shopBeanList = sbl;
        //更新数据源
        //适配器的内容改变时需要强制刷新每个Item的内容,可以实现动态的刷新列表的功能
        //notifyDataSetChanged();
    }

    //该方法返回当前的 Fragment，交给相关联的 Activity 中的 ViewPager2
    //该方法将Fragment与 ViewPager2 完美结合
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle args = new Bundle();//创建bundle，传递数据给 广告 AdBannerFragment
        if (shopBeanList.size() > 0)
            args.putSerializable("ad", shopBeanList.get(position % shopBeanList.size()));
        return AdBannerFragment.newInstance(args);
    }

    //获取广告总数
    @Override
    public int getItemCount() {
        //return shopBeanList.size();
        return shopBeanList == null ? 0 : shopBeanList.size();
    }
}