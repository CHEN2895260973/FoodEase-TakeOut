package com.example.foodease;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginButton, registerButton ;
    TextView tvMessage;
    static final int SPLASH_DELAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) this.findViewById(R.id.username);
        password = (EditText) this.findViewById(R.id.password);
        loginButton  = (Button) this.findViewById(R.id.login);
        registerButton  = (Button) this.findViewById(R.id.register);
        tvMessage =(TextView)this.findViewById(R.id.td);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().trim().equals("foodease")
                        && password.getText().toString().equals("123456")) {
//                    tvMessage.setText("登录成功！");
//                    Intent intent = new Intent(LoginActivity.this, ShopActivity.class);
//                    startActivity(intent);
//                    //关闭当前页面
//                    finish();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvMessage.setText("登录成功！");
                        }
                    }, SPLASH_DELAY_TIME);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //跳转到下一个页面
                            Intent intent = new Intent(LoginActivity.this, ShopActivity.class);
//                            优化：设置FLAG_ACTIVITY_CLEAR_TOP和FLAG_ACTIVITY_SINGLE_TOP标志位来避免重复创建Activity实例，从而提高性能和响应速度
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                            startActivity(intent);

                            //关闭当前页面
                            finish();
                        }
                    }, SPLASH_DELAY_TIME);
                }
                else{
                    tvMessage.setText("登录失败！");
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                            优化：设置FLAG_ACTIVITY_CLEAR_TOP和FLAG_ACTIVITY_SINGLE_TOP标志位来避免重复创建Activity实例，从而提高性能和响应速度
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
                //关闭当前页面
//                finish();
            }
        });

    }


}
