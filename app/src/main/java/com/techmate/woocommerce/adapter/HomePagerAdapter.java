package com.techmate.woocommerce.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.techmate.woocommerce.fragment.AccountFragment;
import com.techmate.woocommerce.fragment.CategoriesFragment;
import com.techmate.woocommerce.fragment.HomeFragment;
import com.techmate.woocommerce.fragment.OffersFragment;
import com.techmate.woocommerce.fragment.WishListsFragment;

public class HomePagerAdapter extends FragmentStateAdapter {

    private static final String TAG = "HomePagerAdapter";

    public HomePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return HomeFragment.getInstance();
        } else if (position == 1) {
            return CategoriesFragment.getInstance();
        } else if (position == 2) {
            return OffersFragment.getInstance();
        } else if (position == 3) {
            return WishListsFragment.getInstance(WishListsFragment.class.getSimpleName());
        } else {
            return AccountFragment.getInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
