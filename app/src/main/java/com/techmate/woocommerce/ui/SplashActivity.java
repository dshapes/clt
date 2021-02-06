package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.utils.PrefManager;
import com.techmate.woocommerce.utils.Utility;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {

    private PrefManager prefManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        prefManager = new PrefManager(context);
        Utility.printHashKey(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(prefManager.getString(PrefManager.KEY_USERID,""))){
                    Utility.startActivity(SplashActivity.this, LoginActivity.class,true);
                }
                else {
                    Utility.startActivity(SplashActivity.this, HomeActivity.class,true);
                }
            }
        },1500);


    }
}