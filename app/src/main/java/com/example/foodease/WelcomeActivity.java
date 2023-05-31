package com.example.foodease;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    //设置延迟时间（单位：毫秒）
    static final int SPLASH_DELAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //创建一个延迟跳转的Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转到下一个页面
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);

                //关闭当前页面
                finish();
            }
        }, SPLASH_DELAY_TIME);
    }
}
