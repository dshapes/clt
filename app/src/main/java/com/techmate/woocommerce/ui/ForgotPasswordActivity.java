package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.databinding.ActivityForgotPasswordBinding;
import com.techmate.woocommerce.databinding.ActivityRegisterBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.HashMap;
import java.util.List;

public class ForgotPasswordActivity extends AppCompatActivity implements ViewPresenter.MainView {

    private static final String TAG = "ForgotPasswordActivity";
    private ActivityForgotPasswordBinding binding;
    private Context context;
    private MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        context = this;
        setContentView(binding.getRoot());
    }

    public void resetPassword(View view) {

        if (!Utility.isValidEmail(binding.edtEmail.getText().toString().trim())) {
            binding.edtEmail.setError("Please enter valid email");
        } else {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(Constants.PARAM_EMAIL, binding.edtEmail.getText().toString().trim());
            mainModel = new MainModel(ForgotPasswordActivity.this, context);
            mainModel.getData(Constants.API_FORGOT_PASSWORD, hashMap);
        }
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
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mainSuccess(HomeResponse responseModel, String whichResponse) {
        Toast.makeText(getApplicationContext(), "Success " + responseModel.getMessage(), Toast.LENGTH_SHORT).show();
        Utility.printLog(TAG, responseModel.getMessage());
        Utility.printGson(TAG, responseModel);
        Utility.showMessageDialog(this, responseModel.getMessage(),true);
    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG, "err " + err);
    }

}