package com.techmate.woocommerce.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techmate.woocommerce.databinding.ActivityLoginBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements ViewPresenter.MainView {

    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding loginBinding;
    private MainModel mainModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        context = this;
        setContentView(loginBinding.getRoot());
    }

    public void loginUser(View view) {

        if (!validatedUser()) {
            return;
        }

        Utility.hideKeyboard(this);
        signIn();

    }

    private void signIn() {

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.PARAM_EMAIL, loginBinding.edtEmail.getText().toString().trim());
        hashMap.put(Constants.PARAM_PASSWORD, loginBinding.edtPassword.getText().toString().trim());

        mainModel = new MainModel(LoginActivity.this, context);
        mainModel.getData(Constants.API_LOGIN, hashMap);

    }

    public boolean validatedUser() {

        if (!Utility.isValidEmail(loginBinding.edtEmail.getText().toString().trim())) {
            loginBinding.edtEmail.setError("Please enter valid email");
            return false;
        } else if (TextUtils.isEmpty(loginBinding.edtPassword.getText().toString().trim())) {
            loginBinding.edtPassword.setError("Please enter valid password");
            return false;
        }

        return true;
    }

    public void redirectToRegister(View view) {
        Utility.startActivity(this, RegisterActivity.class);
    }

    @Override
    public boolean checkInternet() {
        return Utility.isConnectedToInternet(context);
    }

    @Override
    public void mainValidateError(String whichError) {
        Utility.printLog(TAG, whichError);
    }

    @Override
    public void showProgressBar() {
        loginBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        loginBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mainSuccess(HomeResponse responseModel, String whichResponse) {
        Toast.makeText(getApplicationContext(), "Success " + responseModel.getMessage(), Toast.LENGTH_SHORT).show();
        Utility.printLog(TAG, responseModel.getMessage());
        Utility.printGson(TAG, responseModel);
        Utility.startActivity(LoginActivity.this, HomeActivity.class,true);
    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG, "err " + err);
        Toast.makeText(context,"Error " + err,Toast.LENGTH_SHORT).show();
    }

    public void redirectToForgotPassword(View view) {
        Utility.startActivity(LoginActivity.this, ForgotPasswordActivity.class);
    }
}