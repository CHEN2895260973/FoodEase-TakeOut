package com.example.foodease;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shop.adapter.AdBannerAdapter;
import com.example.order.bean.ShopBean;
import com.example.shop.adapter.ShopAdapter;
import com.example.shop.utils.Constant;
import com.example.shop.utils.JsonParse;
import com.example.shop.views.ShopListView;
import com.example.shop.views.ViewPagerIndicator;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopActivity extends AppCompatActivity {

    private TextView tv_back,tv_title;        //返回键与标题控件
    //    private ShopListView slv_list;             //列表控件
//    private ShopAdapter adapter;               //列表的适配器
    private RelativeLayout rl_title_bar;
    private ViewPager2 viewPager;         //广告
    private ViewPagerIndicator viewPagerIndicator;   //小圆点
    private View adBannerLay;          //广告条容器
    private AdBannerAdapter adBannerAdapter;      //广告ViewPager2适配器
    public static final int MSG_AD_SLID = 1;  //广告自动滑动
    public static final int MSG_SHOP_OK = 2;  //获取数据
    private MHandler mHandler;
    private ShopListView slv_list;             //列表控件
    private ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mHandler=new MHandler();
        initData(); //访问网络，获取数据并将数据显示在界面上
        init(); //初始化界面控件
    }
    /**
     * 初始化界面控件
     */
    private void init(){
        slv_list= findViewById(R.id.slv_list);
        adapter = new ShopAdapter(this);
        slv_list.setAdapter(adapter);
        tv_back = findViewById(R.id.tv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("店铺");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.blue));
        tv_back.setVisibility(View.GONE);
//        slv_list= findViewById(R.id.slv_list);
//        adapter=new ShopAdapter(this);
//        slv_list.setAdapter(adapter);
        adBannerLay = findViewById(R.id.adbanner_layout);
        viewPager =  findViewById(R.id.slidingAdvertBanner);
        viewPagerIndicator = findViewById(R.id.advert_indicator);
        viewPager.setLongClickable(false);
        adBannerAdapter = new AdBannerAdapter(getSupportFragmentManager(),
                getLifecycle());
        viewPager.setAdapter(adBannerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            //ViewPager2滑动到新的页时，需要完成的功能
            @Override
            public void onPageSelected(int position) {
                //super.onPageSelected(position);
                if (adBannerAdapter.getItemCount() > 0) {
                    //设置当前小圆点
                    viewPagerIndicator.setCurrentPostion(
                            position % adBannerAdapter.getItemCount());
                }
            }
        });
        resetSize();//自定义函数：计算控件大小
        new AdAutoSlidThread().start();//开启一个子线程
    }
    //定义一个无限循环的子线程,子线程每5秒钟发送一个消息个主线程
    //主线程每5秒钟接收到该消息，然后显示一下个广告（Fragment）
    class AdAutoSlidThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(5000); //睡眠5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler != null)
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
            }
        }
    }

    //访问网络，获取数据
    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE +
                Constant.REQUEST_SHOP_URL).build();
        Call call = okHttpClient.newCall(request);
        // 开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string(); //获取店铺数据
                Message msg = new Message();
                msg.what = MSG_SHOP_OK;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {
            }
        });
    }
    /**
     * 事件捕获
     */
    class MHandler extends Handler {
        //处理子线程发送过来的消息
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_SHOP_OK://接收访问网络子线程发送过来的消息
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        Log.i("tag",vlResult);
                        //解析获取的JSON数据
                        List<ShopBean> shopBeanList = JsonParse.getInstance().getShopList(vlResult);
                        Log.i("tag",shopBeanList.toString());
                        adapter.setData(shopBeanList);
                        if (shopBeanList != null) {
                            if (shopBeanList.size() > 0) {
                                //设置广告栏数据到适配器上
                                //将网络上访问到的 ShopBean 的列表数据（广告列表数据）赋值给适配器
                                adBannerAdapter.setData(shopBeanList);
                                //更新数据源
                                //适配器的内容改变时需要强制刷新每个Item的内容,
                                //可以实现动态的刷新列表的功能
                                adBannerAdapter.notifyDataSetChanged();
                                //设置小圆点数目
                                viewPagerIndicator.setCount(shopBeanList.size());
                                //设置当前小圆点的位置为0
                                viewPagerIndicator.setCurrentPostion(0);
                            }
                        }
                    }
                    break;
                case MSG_AD_SLID://接收AdAutoSlidThread子线程发送过来的消息（每5秒钟发送一个消息）
                    if (adBannerAdapter.getItemCount() > 0) {//获取广告适配器的数据大小
                        //判断广告是否滑动到了最后一个
                        if(viewPager.getCurrentItem() +1 >= adBannerAdapter.getItemCount()){
                            //广告从第一个开始重新显示
                            viewPager.setCurrentItem(0);
                        }else{
                            //设置滑动到下一张广告图片
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    }
                    break;
            }

        }


    }
    /**
     * 计算控件大小
     */
    private void resetSize() {
        int sw = getScreenWidth();//获取屏幕宽度
        int adLheight = sw /3; //广告条高度
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);
    }
    /**
     * 获取屏幕宽度
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    protected long exitTime;//记录第一次点击时的时间
    //点击2次返回键的时间间隔小于2秒钟，退出APP
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(ShopActivity.this, "再按一次退出仿美团外卖应用",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ShopActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);


    }
}