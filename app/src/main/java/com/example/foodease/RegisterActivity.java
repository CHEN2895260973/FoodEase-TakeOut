package com.example.foodease;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username1, password1, password2, phoneNum;
    Button confirm, returnButton;
    TextView mes;

    static final int SPLASH_DELAY_TIME = 3000;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        phoneNum =findViewById(R.id.phone);
        mes = findViewById(R.id.register_meg);
        confirm = findViewById(R.id.confirm);
        returnButton = findViewById(R.id.back);

        returnButton.setVisibility(View.GONE);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean success =true;
                String message="";

                if( username1.getText().toString().trim() ==null
                        || password1.getText().toString().trim()==null
                        || password2.getText().toString().trim()==null
                        || phoneNum.getText().toString().trim()==null){
                    message = "以上文本框皆为必填！";
                }else if (password1.equals(password2)){
                    message = "两次输入密码不相同！";
                }

                if (success) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mes.setText("注册成功！将返回登录界面");
                        }
                    }, SPLASH_DELAY_TIME);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //跳转到下一个页面
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            //关闭当前页面
                            finish();
                        }
                    }, SPLASH_DELAY_TIME);
                }
                else{
                    mes.setText(message);
                }
            }
        });

    }
}
