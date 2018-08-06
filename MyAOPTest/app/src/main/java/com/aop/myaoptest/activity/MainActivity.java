package com.aop.myaoptest.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.aop.myaoptest.R;
import com.aop.myaoptest.annotation.ValidateLogin;

@ValidateLogin
public class MainActivity extends BaseActivity {

    private Button btn;
    private View inflate;


    @Override
    protected void initView() {
        btn = inflate.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showFloatingDialog();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected View getLayout() {
        inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main, mLLBase, false);
        return inflate;
    }


    private void showFloatingDialog() {
        DisplayMetrics d = getResources().getDisplayMetrics();
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
       View view = LayoutInflater.from(getApplication()).inflate(R.layout.floating_dialog, null, false);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.width = (int) (d.widthPixels * 0.6);
//        layoutParams.height = (int) (d.heightPixels * 0.7);
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
        //layoutParams.alpha = 0.2f;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        windowManager.addView(view, layoutParams);

        //view.setBackgroundResource(R.mipmap.float_dialog);
    }
}
