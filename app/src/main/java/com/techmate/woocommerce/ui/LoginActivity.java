package com.techmate.woocommerce.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.databinding.ActivityLoginBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.PrefManager;
import com.techmate.woocommerce.utils.Utility;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements ViewPresenter.MainView, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding loginBinding;
    private MainModel mainModel;
    private Context context;
    private PrefManager prefManager;

    //Google sign in
    public GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 79;
    private CallbackManager mFacebookCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        context = this;
        prefManager = new PrefManager(context);
        setContentView(loginBinding.getRoot());
        loginBinding.llGoogle.setOnClickListener(this);
        loginBinding.llFb.setOnClickListener(this);
        googleControlsInit();
    }

    public void loginUser(View view) {

        if (!validatedUser()) {
            return;
        }

        Utility.hideKeyboard(this);
        if (loginBinding.progressBar.getVisibility() != View.VISIBLE) {
            signIn();
        }
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

        if (responseModel.getData() != null) {
            if (responseModel.getData().getData_() != null) {
                if (!TextUtils.isEmpty(responseModel.getData().getData_().getID())) {
                    prefManager.setString(PrefManager.KEY_USERID, responseModel.getData().getData_().getID());
                    prefManager.setString(PrefManager.KEY_EMAIL, responseModel.getData().getData_().getUserEmail());
                    prefManager.setString(PrefManager.KEY_FULL_NAME, responseModel.getData().getData_().getDisplayName());
                    prefManager.setString(PrefManager.KEY_USER_IMAGE, responseModel.getData().getData_().getUserUrl());
                    Utility.startActivity(LoginActivity.this, HomeActivity.class, true);
                }
            }
        }
    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG, "err " + err);
        Toast.makeText(context, "Error " + err, Toast.LENGTH_SHORT).show();
    }

    public void redirectToForgotPassword(View view) {
        Utility.startActivity(LoginActivity.this, ForgotPasswordActivity.class);
    }

    public void googleControlsInit() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public void connectWithGoogle() {
        googleSignOut();
        googleSignIn();
    }

    private void googleSignOut() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    status -> {
                        // ...
                    });
        }
    }

    private void googleSignIn() {
        showProgressBar();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            String email = "";
            String avatar_g_url = "";
            String last_name = "";
            String g_id = "";
            String first_name = "";

            if (acct != null) {
                email = acct.getEmail() + "";
                avatar_g_url = acct.getPhotoUrl() + "";
                g_id = acct.getId() + "";
                first_name = acct.getGivenName() + "";
                last_name = acct.getFamilyName() + "";
            }

            prefManager.setString(PrefManager.KEY_EMAIL, email);
            prefManager.setString(PrefManager.KEY_USER_IMAGE, avatar_g_url);
            prefManager.setString(PrefManager.KEY_LAST_NAME, last_name);
            prefManager.setString(PrefManager.KEY_FIRST_NAME, first_name);
            prefManager.setString(PrefManager.KEY_GOOGLE_ID, g_id);

        } else {
            Utility.printLog(TAG, "handleSignInResult:isSuccess: " + result.isSuccess());
            Utility.printLog(TAG, "handleSignInResult:isSuccess: " + result.getStatus());
            Utility.printLog(TAG, "handleSignInResult:isSuccess: " + result.getStatus().getStatusMessage());
        }

        hideProgressBar();

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_google:
                if (loginBinding.progressBar.getVisibility() != View.VISIBLE) {
                    connectWithGoogle();
                }
                break;
            case R.id.ll_fb:
                if (loginBinding.progressBar.getVisibility() != View.VISIBLE) {
                    connectWithFacebook();
                }
                break;
        }
    }

    public void connectWithFacebook() {
        showProgressBar();
        disconnectFromFacebook(new ConfirmationCallback() {
            @Override
            public void onSuccess(boolean value) {
                //facebook Api login

                setupFacebookStuff();

            }

            @Override
            public void onError() {
                //DialogUtil.safeDismiss(progressDialog);
            }
        });
    }

    private void setupFacebookStuff() {
        // This should normally be on your application class
        FacebookSdk.sdkInitialize(getApplicationContext());
        AccessTokenTracker mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                //updateFacebookButtonUI();
            }
        };
        LoginManager mLoginManager = LoginManager.getInstance();
        mFacebookCallbackManager = CallbackManager.Factory.create();
        mAccessTokenTracker.startTracking();
        //mLoginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends", "email", "user_birthday","user_location"));
        mLoginManager.logInWithReadPermissions(LoginActivity.this, Collections.singletonList("public_profile"));
        LoginManager.getInstance().registerCallback(mFacebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            if (object != null) {
                                String email = "", gender = "", link = "", first_name = "", last_name = "", fb_id = "", avatar_fb_url = "";
                                boolean verified = false;
                                if (object.has("email")) {
                                    email = object.getString("email");
                                }

                                if (object.has("gender")) {
                                    gender = object.getString("gender");
                                }

                                if (object.has("verified")) {
                                    verified = object.getBoolean("verified");
                                }

                                if (object.has("link")) {
                                    link = object.getString("link");
                                }

                                if (object.has("first_name")) {
                                    first_name = object.getString("first_name");
                                }

                                if (object.has("last_name")) {
                                    last_name = object.getString("last_name");
                                }

                                if (object.has("id")) {
                                    fb_id = object.getString("id");
                                    avatar_fb_url = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=large";
                                }

                                prefManager.setString(PrefManager.KEY_EMAIL, email);
                                prefManager.setString(PrefManager.KEY_USER_IMAGE, avatar_fb_url);
                                prefManager.setString(PrefManager.KEY_LAST_NAME, last_name);
                                prefManager.setString(PrefManager.KEY_FIRST_NAME, first_name);
                                prefManager.setString(PrefManager.KEY_FB_ID, fb_id);
                                prefManager.setString(PrefManager.KEY_GENDER, gender);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        hideProgressBar();
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,picture,name,email,verified,cover,link,gender");
                parameters.putString("locale", "en_US");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                hideProgressBar();
            }

            @Override
            public void onError(FacebookException error) {
                Utility.printLog(TAG, "error is -- >> " + error);
                hideProgressBar();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        try {
            if (data != null) {
                if (requestCode == RC_SIGN_IN) {
                    GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                    handleSignInResult(result);

                } else if (resultCode == RESULT_OK) {
                    mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
                } else {
                    hideProgressBar();
                    Utility.printLog(TAG, "" + requestCode + "&& requestCode is " + resultCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utility.printLog(TAG, "Social Login failed " + e.getMessage());
        }
    }

    public void disconnectFromFacebook(final ConfirmationCallback confirmationCallback) {
        if (AccessToken.getCurrentAccessToken() == null) {
            confirmationCallback.onSuccess(true);
            return; // already logged out
        }
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
                confirmationCallback.onSuccess(true);
            }
        }).executeAsync();
    }

    public interface ConfirmationCallback {

        void onSuccess(boolean isCall);

        void onError();
    }
}