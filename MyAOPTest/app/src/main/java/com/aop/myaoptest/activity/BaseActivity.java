package com.aop.myaoptest.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.aop.myaoptest.R;
import com.aop.myaoptest.annotation.ValidateLogin;
import com.aop.myaoptest.utils.CheckTokenUtils;

public abstract class BaseActivity extends AppCompatActivity{

    public LinearLayout mLLBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        mLLBase = findViewById(R.id.ll_base);
        mLLBase.addView(getLayout());
        initView();
        initData();
        validateIsLogin();
    }

    protected abstract void initView();

    protected abstract void initData();


    protected abstract View getLayout();

    private void validateIsLogin() {
        ValidateLogin notValLoginer = findValidateLoginer();
        if(notValLoginer != null){
            //----------------下面的这部分可替换为实际接口验证token---------------------//
            CheckTokenUtils instance = CheckTokenUtils.getInstance(getApplicationContext());
            instance.setToken("123");
            SharedPreferences sp = instance.getSp();
            String token = sp.getString("token","");
            boolean isOutdate = instance.checkTokenIsOutdate(token);
            //-----------------------------end----------------------------------------//
            if(!isOutdate){
                //过期则跳到登录页面
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                Log.e("tag","token过期了");
            }else {
                Log.e("tag","token没有过期");
            }
        }

    }

    private ValidateLogin findValidateLoginer() {
        ValidateLogin validateLogin = getClass().getAnnotation(ValidateLogin.class);
        if(validateLogin == null){
            validateLogin = getClass().getSuperclass().getAnnotation(ValidateLogin.class);
        }
        return validateLogin;
    }

}
