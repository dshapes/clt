package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.utils.Utility;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utility.printHashKey(this);
        new Handler().postDelayed(() -> Utility.startActivity(SplashActivity.this, LoginActivity.class,true), 1500);
    }
}