package com.techmate.woocommerce.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techmate.woocommerce.databinding.ActivityRegisterBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements ViewPresenter.MainView {

    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding binding;
    private Context context;
    private MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void registerUser(View view) {

        if (!validatedUser()) {
            return;
        }

        signUp();
    }

    private void signUp() {

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.PARAM_NAME, binding.edtFullName.getText().toString().trim());
        hashMap.put(Constants.PARAM_EMAIL, binding.edtEmail.getText().toString().trim());
        hashMap.put(Constants.PARAM_PASSWORD, binding.edtPassword.getText().toString().trim());

        mainModel = new MainModel(RegisterActivity.this,context);
        mainModel.getData(Constants.API_REGISTER,hashMap);

    }


    public boolean validatedUser() {

        if (TextUtils.isEmpty(binding.edtFullName.getText().toString().trim())) {
            binding.edtFullName.setError("Please enter valid name");
            return false;
        } else if (TextUtils.isEmpty(binding.edtDob.getText().toString().trim())) {
            binding.edtDob.setError("Please enter valid DOB");
            return false;
        } else if (!Utility.isValidEmail(binding.edtEmail.getText().toString().trim())) {
            binding.edtEmail.setError("Please enter valid email");
            return false;
        } else if (TextUtils.isEmpty(binding.edtPassword.getText().toString().trim())) {
            binding.edtPassword.setError("Please enter valid password");
            return false;
        } else if (TextUtils.isEmpty(binding.edtReEnterPassword.getText().toString().trim())) {
            binding.edtReEnterPassword.setError("Please re-enter valid password");
            return false;
        } else
            return binding.edtPassword.getText().toString().equals(binding.edtReEnterPassword.getText().toString());

    }

    public void redirectToLogin(View view) {
        onBackPressed();
    }

    @Override
    public boolean checkInternet() {
        return Utility.isConnectedToInternet(context);
    }

    @Override
    public void mainValidateError(String whichError) {
        Toast.makeText(getApplicationContext(),"Net Error " + whichError,Toast.LENGTH_SHORT).show();
        Utility.printLog(TAG, whichError);
    }

    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mainSuccess(HomeResponse responseModel, String whichResponse) {
        Toast.makeText(getApplicationContext(),"Success " + responseModel.getMessage(),Toast.LENGTH_SHORT).show();
        Utility.printLog(TAG,responseModel.getMessage());
        Utility.printGson(TAG,responseModel);
        Utility.startActivity(RegisterActivity.this, HomeActivity.class,true);

    }

    @Override
    public void mainError(String err) {
        Toast.makeText(getApplicationContext(),"Error " + err,Toast.LENGTH_SHORT).show();
        Utility.printLog(TAG,"err " + err);
    }
}