package com.example.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodease.R;
import com.example.order.bean.ShopBean;


public class AdBannerFragment extends Fragment {

    private ShopBean shopBean;   //广告
    private ImageView imageView;  //图片
    //客户端构造 该广告 Fragment所用
    public static AdBannerFragment newInstance(Bundle args) {
        AdBannerFragment af = new AdBannerFragment();
        af.setArguments(args);
        return af;
    }
    //在这里创建碎片
    //生成广告对象时，获取 bundle 数据，并转换为一个 shopBean
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        shopBean = (ShopBean) arg.getSerializable("ad"); //获取一个店铺对象
    }

    //在当前Activity中添加一个Fragment时，被添加的Fragment都会调用onResume
    @Override
    public void onResume() {
        super.onResume();
        if (shopBean != null) {
            //调用Glide框架加载图片
            Glide
                    .with(getActivity())
                    .load(shopBean.getBanner())
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }
    //该方法创建一个视图，返回 ImageView 控件对象的视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageView = new ImageView(getActivity()); //创建一个 ImageView 控件的对象
        //定义一个视图容器对象，用来存放图片对象,容器的宽度和高度都是 MATCH_PARENT
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(lp);      //设置ImageView控件的宽高参数
        imageView.setScaleType(ImageView.ScaleType.FIT_XY); //把图片填满整个控件
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //跳转到店铺详情界面
//                if (sb == null) return;
//                Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
//                intent.putExtra("shop", shopBean);
//                getActivity().startActivity(intent);
//            }
//        });
        return imageView;
    }
}