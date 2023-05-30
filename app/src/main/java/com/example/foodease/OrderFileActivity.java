package com.example.foodease;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.bean.OrderBean;
import com.example.shop.adapter.OrderFileAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderFileActivity extends AppCompatActivity {

    private ListView lv_order;

    private OrderFileAdapter adapter;
    private List<OrderBean> carFoodList=new ArrayList<>();
    private TextView tv_title, tv_back,tv_distribution_cost,tv_total_cost,
            tv_cost,tv_payment,tv_food_name, tv_count, tv_money;

    private ImageView iv_food_pic;
    private RelativeLayout rl_title_bar;
    private int money;
    private int distributionCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderfile);
        //获取购物车中的数据
        initedata();

        //获取购物车中的数据
        carFoodList= (List<OrderBean>) getIntent().getSerializableExtra(
                "carFoodList");
        //获取购物车中菜的总价格
        money=17;
        //获取店铺的配送费
        distributionCost=2;
        initView();
        setData();
    }
    /**
     * 初始化界面控件
     */
    private void initView(){
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("订单详情");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.
                blue_color));
        tv_back =  findViewById(R.id.tv_back);
        lv_order= findViewById(R.id.lv_order);
        tv_distribution_cost = findViewById(R.id.tv_distribution_cost);
        tv_total_cost =  findViewById(R.id.tv_total_cost);
        tv_cost =  findViewById(R.id.tv_cost);
        tv_payment =  findViewById(R.id.tv_payment);

        tv_food_name =findViewById(R.id.tv_food_name);
        tv_count = findViewById(R.id.tv_count);
        tv_money =findViewById(R.id.tv_money);
        iv_food_pic = findViewById(R.id.iv_food_pic);
        // 返回键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    /**
     * 设置界面数据
     */
    private void setData() {

        adapter=new OrderFileAdapter(this,getLayoutInflater(),carFoodList);
        lv_order.setAdapter(adapter);
        tv_food_name.setText("大满贯珍珠奶茶（大杯）");
        tv_count.setText("x 1");
        tv_money.setText("￥17");
        iv_food_pic.setImageResource(R.drawable.tea2);
        tv_cost.setText("￥"+money);
        tv_distribution_cost.setText("￥"+distributionCost);
        tv_total_cost.setText("￥"+19);
    }

    private void initedata(){
        OrderBean orderBean1=new OrderBean(1,"珍珠奶茶","门店月售第1名","336",14,2,R.drawable.tea1);
        OrderBean orderBean2=new OrderBean(2,"大满贯珍珠奶茶（大杯）","门店月售第2名","259",17,1,R.drawable.tea2);
        carFoodList.add(orderBean1);
        carFoodList.add(orderBean2);

    }
}