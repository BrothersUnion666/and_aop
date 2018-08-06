package com.aop.myaoptest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.aop.myaoptest.R;
import com.aop.myaoptest.annotation.ValidateLogin;

@ValidateLogin
public class SecondActivity extends BaseActivity {

    private View inflate;

    @Override
    protected View getLayout() {
        inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_second, mLLBase, false);
        return inflate;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


}
