package com.techmate.woocommerce.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.techmate.woocommerce.fragment.CategoriesByParentFragment;
import com.techmate.woocommerce.fragment.WishListsFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titleList = new ArrayList<>();
    private List<Integer> subCategoriesList = new ArrayList<>();

    public CategoryPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        return CategoriesByParentFragment.getInstance(subCategoriesList.get(position));
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    public void addFragments(String title){
        this.titleList.add(title);
    }

    public void setSubCategoryId(int subCategoryId){
        this.subCategoriesList.add(subCategoryId);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}