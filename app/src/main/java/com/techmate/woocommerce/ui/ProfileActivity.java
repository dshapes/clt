package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.databinding.ActivityProfileBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.PrefManager;
import com.techmate.woocommerce.utils.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.gilo.woodroid.Woocommerce;
import me.gilo.woodroid.models.Customer;
import me.gilo.woodroid.models.LineItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, ViewPresenter.ProfileView {

    private static final String TAG = "ProfileActivity";
    private ActivityProfileBinding binding;
    private PrefManager prefManager;
    private Context context;
    private MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        context = this;
        initViews();
        setContentView(binding.getRoot());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {

        prefManager = new PrefManager(context);
        binding.imgBack.setOnClickListener(this);
        binding.btnEditProfile.setOnClickListener(this);
        binding.edtFullName.setText(prefManager.getString(PrefManager.KEY_FULL_NAME, "Enter Full Name"));
        binding.edtEmail.setText(prefManager.getString(PrefManager.KEY_EMAIL, "Enter Email"));
        binding.edtDob.setText(prefManager.getString(PrefManager.KEY_DOB, "Enter Date of Birth"));
        binding.edtPhone.setText(prefManager.getString(PrefManager.KEY_MOBILE_PHONE, "Enter Mobile No"));

        binding.edtFullName.setFocusable(false);
        binding.edtEmail.setFocusable(false);
        binding.edtDob.setFocusable(false);
        binding.edtPhone.setFocusable(false);
        binding.edtPassword.setFocusable(false);

        binding.edtFullName.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.edtFullName.getRight() - binding.edtFullName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    toggleFocus(binding.edtFullName);
                    return true; // consume touch even
                }
            }
            return false;
        });

        binding.edtEmail.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.edtEmail.getRight() - binding.edtEmail.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    toggleFocus(binding.edtEmail);
                    return true; // consume touch even
                }
            }
            return false;
        });

        /*binding.edtDob.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.edtDob.getRight() - binding.edtDob.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    toggleFocus(binding.edtDob);
                    return true; // consume touch even
                }
            }
            return false;
        });*/

        binding.edtPhone.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.edtPhone.getRight() - binding.edtPhone.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    toggleFocus(binding.edtPhone);
                    return true; // consume touch even
                }
            }
            return false;
        });

        binding.edtPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.edtPassword.getRight() - binding.edtPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    toggleFocus(binding.edtPassword);
                    return true; // consume touch even
                }
            }
            return false;
        });
    }

    private void toggleFocus(AppCompatEditText editText) {
        if (editText.hasFocus()) {
            editText.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        } else {
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.btnEditProfile:
                editProfileCall();
                break;
        }
    }

    private void editProfileCall() {

        //Log.e(TAG, "editProfileCall: " + prefManager.getString(PrefManager.KEY_USERID,""));
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.PARAM_ID, prefManager.getString(PrefManager.KEY_USERID, ""));
        hashMap.put(Constants.PARAM_NAME, binding.edtFullName.getText().toString().trim());
        hashMap.put(Constants.PARAM_EMAIL, binding.edtEmail.getText().toString().trim());
        hashMap.put(Constants.PARAM_MOBILE_NO, binding.edtPhone.getText().toString().trim());
        hashMap.put(Constants.PARAM_DOB, binding.edtDob.getText().toString().trim());
        hashMap.put(Constants.PARAM_PASSWORD, binding.edtPassword.getText().toString().trim());
        hashMap.put(Constants.PARAM_GENDER, "Male");

        mainModel = new MainModel(this, context);
        mainModel.updateProfile(Constants.API_UPDATE_USER, hashMap);

    }

    @Override
    public boolean checkInternet() {
        return Utility.isConnectedToInternet(context);
    }

    @Override
    public void mainValidateError(String whichError) {
        Toast.makeText(getApplicationContext(), "Net Error " + whichError, Toast.LENGTH_SHORT).show();
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
    public void mainSuccess(HomeResponse homeResponse, String whichResponse) {

        Toast.makeText(getApplicationContext(), "Success " + homeResponse.getMessage(), Toast.LENGTH_SHORT).show();
        Utility.printLog(TAG, homeResponse.getMessage());
        Utility.printGson(TAG, homeResponse);

    }

    @Override
    public void mainError(String err) {
        Toast.makeText(getApplicationContext(), "Error " + err, Toast.LENGTH_SHORT).show();
        Utility.printLog(TAG, "err " + err);
    }

    public void profileCall() {

        Woocommerce woocommerce = Woocommerce.Builder()
                .setSiteUrl(Constants.BASE_URL)
                .setApiVersion(Woocommerce.API_V3)
                .setConsumerKey(Constants.CONSUMER_KEY)
                .setConsumerSecret(Constants.CONSUMER_SECRET)
                .build();

        Customer customer = new Customer();
        customer.id = Integer.parseInt(prefManager.getString(PrefManager.KEY_USERID, ""));
        customer.firstName = binding.edtFullName.getText().toString().trim();
        customer.email = binding.edtEmail.getText().toString().trim();
        customer.password = binding.edtPassword.getText().toString().trim();


        woocommerce.CustomerRepository().update(Integer.parseInt(prefManager.getString(PrefManager.KEY_USERID, "")), customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Log.e(TAG, "onResponse: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.getMessage());
            }
        });
    }
}