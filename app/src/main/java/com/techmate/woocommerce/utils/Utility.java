package com.techmate.woocommerce.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.intuit.sdp.BuildConfig;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.model.HomeResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static void printLog(String tag, String msg) {
        Log.e(tag, "printLog: " + msg);
    }

    public static void startActivity(Activity activity, Class<?> clas) {
        Intent intent = new Intent(activity, clas);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, Class<?> clas, boolean isFinish) {
        Intent intent = new Intent(activity, clas);
        activity.startActivity(intent);
        if (isFinish)
            activity.finish();
    }

    public static void printGson(String tag, HomeResponse response) {
        Log.e(tag, new Gson().toJson(response) + "");
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    public static boolean isConnectedToInternet(Context context) {

        if (context != null) {

            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                for (NetworkInfo networkInfo : info)
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

            }
        }
        return false;
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static boolean isValidDOB(int selectedYear, int selectedMonth, int selectedDay){
        Calendar selectedCal = Calendar.getInstance();
        selectedCal.set(selectedYear, selectedMonth, selectedDay);
        Date selectedDate = selectedCal.getTime();
        Date currentDate = Calendar.getInstance().getTime();
        int diff1 = selectedDate.compareTo(currentDate);
        if(diff1>0){
            return false;
        }
        return true;
    }

    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }
        return isInBackground;
    }

    public static void setLayoutManager(Context context, RecyclerView recyclerView, boolean isHorizontal) {

        if (isHorizontal) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    public static void showMessageDialog(Activity activity, String message, boolean finishActivity) {

        AlertDialog.Builder dialogBuilder;
        AlertDialog alertDialog;
        dialogBuilder = new AlertDialog.Builder(activity);
        View layoutView = activity.getLayoutInflater().inflate(R.layout.dialog_show_message, null);
        TextView txtOkay = layoutView.findViewById(R.id.txtOkay);
        TextView txtMsg = layoutView.findViewById(R.id.txtMessage);
        txtMsg.setText(message);
        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        txtOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if (finishActivity){
                    activity.finish();
                }
            }
        });

        if (!activity.isFinishing()) {
            alertDialog.show();
        }
    }


    // TODO: 17/12/20 Remove unncessary functions

    private static Animator mCurrentAnimator;
    public static RelativeLayout zoomImageFromThumb(Activity activity, Context context, final View thumbView, final View viewToExpand, boolean hideThumb) {

        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Retrieve and cache the system's default "short" animation time.
        int mShortAnimationDuration = context.getResources().getInteger(android.R.integer.config_shortAnimTime);
        // Load the high-resolution "zoomed-in" image.
        //ImageUtils.picasoImageLoadWithPlaceHolder(context, ImageUtils.convertGoogleURL(context, ImageUtils.CIRCULARPROFILEPICTURE, QrUrl, "profile"), loadQrImage, R.drawable.qr_placeholder);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        activity.findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        if (hideThumb)
            thumbView.setAlpha(0f);
        viewToExpand.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        viewToExpand.setPivotX(0f);
        viewToExpand.setPivotY(0f);

        ValueAnimator.AnimatorUpdateListener dimTransparencyListner = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                viewToExpand.setBackgroundColor((int) animation.getAnimatedValue());
            }
        };

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).

        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(viewToExpand, View.X, startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(viewToExpand, View.Y, startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(viewToExpand, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(viewToExpand, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();

        mCurrentAnimator = set;
        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.

        final float startScaleFinal = startScale;
        viewToExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator.ofFloat(viewToExpand, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(viewToExpand, View.Y, startBounds.top))
                        .with(ObjectAnimator.ofFloat(viewToExpand, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator.ofFloat(viewToExpand, View.SCALE_Y, startScaleFinal));


                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        viewToExpand.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (hideThumb)
                            thumbView.setAlpha(1f);
                        viewToExpand.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        if (hideThumb)
                            thumbView.setAlpha(1f);
                        viewToExpand.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
        return (RelativeLayout) viewToExpand;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
