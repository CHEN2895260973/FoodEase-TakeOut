package com.example.foodease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.order.bean.OrderBean;
import com.example.shop.adapter.OrderFileAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_order;

    private OrderFileAdapter adapter;
    private List<OrderBean> carFoodList = new ArrayList<>();
    private TextView tv_title, tv_back;
    private Button tv_detail1;
    private RelativeLayout rl_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        initView();
        setData();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("我的订单");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.
                blue_color));
        tv_back = findViewById(R.id.tv_back);
        lv_order = findViewById(R.id.lv_order);
        tv_detail1=findViewById(R.id.tv_detail1);
        // 返回键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OrderListActivity.this,ShopActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        tv_detail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(OrderListActivity.this,OrderFileActivity.class);
                startActivity(intent2);
            }
        });
    }

    /**
     * 设置界面数据
     */
    private void setData() {

        adapter = new OrderFileAdapter(this, getLayoutInflater(), carFoodList);
        lv_order.setAdapter(adapter);

    }

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:               //返回按钮的点击事件
                Intent intent = new Intent(OrderListActivity.this,ShopActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_detail1: //去结算按钮的点击事件
                //跳转到订单界面
                    Intent intent3 = new Intent(OrderListActivity.this,OrderFileActivity.class);
                    startActivity(intent3);
                    finish();
                break;
        }
    }

}