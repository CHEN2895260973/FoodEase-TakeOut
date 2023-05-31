package com.example.foodease;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PersonActivity extends AppCompatActivity {

    private RadioGroup radioGroup;          // 底部导航栏
    Button exit;
//    ImageButton user, sex, birth, phone, password;
    ImageView avatar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();

//        菜单栏的点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        startActivity(new Intent(PersonActivity.this, ShopActivity.class));
//                        finish();
                        break;
                    case R.id.rb_order:
                        // 切换到发现Fragment或Activity
//                        startActivity(new Intent(PersonActivity.this, ShopActivity.class));
//                        finish();
                        break;
                    case R.id.rb_me:
                        // 切换到个人中心Fragment或Activity
                        startActivity(new Intent(PersonActivity.this, PersonActivity.class));
                        finish();
                        break;
                }
            }
        });

//        退出登录的点击事件
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭当前页面
                finish();
//
//                Intent intent = new Intent(PersonActivity.this, MainActivity.class);
//                startActivity(intent);
                moveTaskToBack(true);
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建选择图片的对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(PersonActivity.this);
                builder.setTitle("选择图片");
                builder.setItems(new String[]{"拍照", "从相册选择"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // 拍照
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
                            dialog.cancel();

                        } else if (which == 1) {
                            // 从相册选择
//                            Intent intent = new Intent(Intent.ACTION_PICK,
//                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                            startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO);
                            dialog.cancel();

                        }
                    }
                });
                builder.show();
            }
        });

    }



    private void init() {

//        user = (ImageButton)findViewById(R.id.btn_user);
//        sex = findViewById(R.id.btn_sex);
//        birth = findViewById(R.id.btn_birth);
//        phone = findViewById(R.id.btn_phone);
//        password = findViewById(R.id.btn_password);
        avatar = findViewById(R.id.iv_avatar);
        exit = findViewById(R.id.btn_exit);
        radioGroup = findViewById(R.id.rg_tab);
//        选中
        radioGroup.check(R.id.rb_me);

    }
//
//
    public void showInputDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改信息");

        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInput = input.getText().toString();
                dialog.cancel();
                // do something with user input
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
//
//

}