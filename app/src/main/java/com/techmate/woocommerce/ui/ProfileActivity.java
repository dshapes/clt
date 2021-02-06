package com.techmate.woocommerce.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.databinding.ActivityProfileBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.PrefManager;
import com.techmate.woocommerce.utils.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import me.gilo.woodroid.Woocommerce;
import me.gilo.woodroid.models.Customer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, ViewPresenter.ProfileView {

    private static final String TAG = "ProfileActivity";
    private static ActivityProfileBinding binding;
    private PrefManager prefManager;
    private Context context;
    private MainModel mainModel;
    private File fileToUpload;

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
        mainModel = new MainModel(this, context);
        binding.imgBack.setOnClickListener(this);
        binding.relImage.setOnClickListener(this);
        binding.btnEditProfile.setOnClickListener(this);
        binding.edtFullName.setText(prefManager.getString(PrefManager.KEY_FULL_NAME, ""));
        if (!TextUtils.isEmpty(prefManager.getString(PrefManager.KEY_EMAIL, "Enter Email"))) {
            binding.edtEmail.setText(prefManager.getString(PrefManager.KEY_EMAIL, "Enter Email"));
        } else {
            binding.edtEmail.setText("Enter Email");
        }
        if (!TextUtils.isEmpty(prefManager.getString(PrefManager.KEY_DOB, ""))) {
            binding.edtDob.setText(prefManager.getString(PrefManager.KEY_DOB, "Enter Date of Birth"));
        } else {
            binding.edtDob.setText("Enter Date of Birth");
        }
        if (!TextUtils.isEmpty(prefManager.getString(PrefManager.KEY_MOBILE_PHONE, ""))) {
            binding.edtPhone.setText(prefManager.getString(PrefManager.KEY_MOBILE_PHONE, ""));
        } else {
            binding.edtPhone.setText("Enter Mobile No");
        }

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

        binding.edtDob.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.edtDob.getRight() - binding.edtDob.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    openCalendar();
                    return true; // consume touch even
                }
            }
            return false;
        });

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

    private void openCalendar() {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
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
                if (fileToUpload != null) {
                    editProfileImageCall();
                } else {
                    editProfileCall();
                }
                break;
            case R.id.relImage:
                addProfilePic();
                break;
        }
    }

    private void addProfilePic() {

        ImagePicker.Companion.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();

    }

    private void editProfileImageCall() {

        int id = Integer.parseInt(prefManager.getString(PrefManager.KEY_USERID, ""));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileToUpload);
        MultipartBody.Part body = MultipartBody.Part.createFormData("profile_pic", fileToUpload.getName(), requestFile);

        RequestBody userId = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(id));
        mainModel.updateProfileImage(userId, body);

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

        if (whichResponse.equals(Constants.API_UPDATE_USER)) {
            Toast.makeText(getApplicationContext(), "Success " + homeResponse.getMessage(), Toast.LENGTH_SHORT).show();
            Utility.printLog(TAG, homeResponse.getMessage());
            Utility.printGson(TAG, homeResponse);
        } else if (whichResponse.equals(Constants.API_PROFILE)) {
            Toast.makeText(getApplicationContext(), "Profile picture updated succesfully", Toast.LENGTH_SHORT).show();
            //prefManager.setString(PrefManager.KEY_USER_IMAGE,homeResponse.getData().toString());
            Utility.printLog(TAG, homeResponse.getMessage());
            Utility.printGson(TAG, homeResponse);
        }
    }

    @Override
    public void mainError(String err) {
        Toast.makeText(getApplicationContext(), "Error " + err, Toast.LENGTH_SHORT).show();
        Utility.printLog(TAG, "err " + err);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri fileUri = data.getData();
            binding.image.setImageURI(fileUri);

            fileToUpload = ImagePicker.Companion.getFile(data);
            Log.e(TAG, "onActivityResult: " + fileToUpload.getPath());


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NotNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return dialog;
        }


        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {

            Calendar newDate = Calendar.getInstance();
            newDate.set(year, year, month + 1);
            binding.edtDob.setText(day + "/" + (month + 1) + "/" + year);

        }
    }
}