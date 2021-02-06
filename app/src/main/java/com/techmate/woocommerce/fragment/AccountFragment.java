package com.techmate.woocommerce.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.facebook.login.Login;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.databinding.FragmentAccountBinding;
import com.techmate.woocommerce.databinding.FragmentHomeBinding;
import com.techmate.woocommerce.ui.AddShippingAddressActivity;
import com.techmate.woocommerce.ui.LoginActivity;
import com.techmate.woocommerce.ui.NotificationActivity;
import com.techmate.woocommerce.ui.OrderListActivity;
import com.techmate.woocommerce.ui.ProfileActivity;
import com.techmate.woocommerce.ui.ShipToActivity;
import com.techmate.woocommerce.utils.PrefManager;
import com.techmate.woocommerce.utils.Utility;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private FragmentAccountBinding binding;
    private Context context;
    private PrefManager prefManager;

    public static AccountFragment getInstance() {
        return new AccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        binding = FragmentAccountBinding.inflate(getLayoutInflater(), container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        prefManager = new PrefManager(context);
        binding.llMyOrders.setOnClickListener(this);
        binding.llShippingAddress.setOnClickListener(this);
        binding.llNotifications.setOnClickListener(this);
        binding.llSetting.setOnClickListener(this);
        binding.llLogout.setOnClickListener(this);
        binding.llMyProfile.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llMyOrders:
                Utility.startActivity((Activity) context, OrderListActivity.class);
                break;
            case R.id.llShippingAddress:
                Intent intent = new Intent(context, ShipToActivity.class);
                intent.putExtra("mode", "display");
                startActivity(intent);
                break;
            case R.id.llSetting:
                break;
            case R.id.llMyProfile:
                Utility.startActivity((Activity) context, ProfileActivity.class);
                break;
            case R.id.llNotifications:
                Utility.startActivity((Activity) context, NotificationActivity.class);
                break;
            case R.id.llLogout:
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme);
                materialAlertDialogBuilder.setIcon(R.drawable.ic_logout);
                materialAlertDialogBuilder.setTitle("Logout");
                materialAlertDialogBuilder.setMessage("Are you sure you want to logout from this app?");
                materialAlertDialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
                    if (getActivity() == null) {
                        return;
                    }
                    binding.progressBar.setVisibility(View.VISIBLE);
                    prefManager.deleteUserPrefData();
                    Utility.startActivity((Activity) context, LoginActivity.class,true);
                });
                materialAlertDialogBuilder.setNegativeButton("No", null);
                materialAlertDialogBuilder.show();
                break;
        }
    }
}
