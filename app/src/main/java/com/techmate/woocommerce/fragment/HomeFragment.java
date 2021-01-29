package com.techmate.woocommerce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.BannerItemAdapter;
import com.techmate.woocommerce.adapter.CategoryItemAdapter;
import com.techmate.woocommerce.adapter.FlashSaleAdapter;
import com.techmate.woocommerce.adapter.OccassionWearAdapter;
import com.techmate.woocommerce.adapter.ProductItemAdapter;
import com.techmate.woocommerce.adapter.SliderPagerAdapter;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.FragmentHomeBinding;
import com.techmate.woocommerce.model.Data;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ViewPresenter.MainView {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding homeBinding;
    private MainModel mainModel;
    private Context context;
    private ProductItemAdapter productItemAdapter;
    private FlashSaleAdapter flashSaleAdapter;
    private OccassionWearAdapter occassionWearAdapter;
    private CategoryItemAdapter categoryItemAdapter;
    private BannerItemAdapter bannerItemAdapter;
    private List<String> bannerList;
    private GridSpacingItemDecoration gridSpacingItemDecoration;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        homeBinding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        initViews();
        return homeBinding.getRoot();
    }

    private void initViews() {

        homeBinding.tabLayout.setupWithViewPager(homeBinding.viewPager);
        homeBinding.viewPager.startAutoScroll();
        homeBinding.viewPager.setInterval(3000);
        homeBinding.viewPager.setCycle(true);
        homeBinding.viewPager.setStopScrollWhenTouch(true);

        gridSpacingItemDecoration = new GridSpacingItemDecoration(2, 10, false);

        homeBinding.recyclerTrending.addItemDecoration(gridSpacingItemDecoration);
        homeBinding.recyclerBanner.addItemDecoration(gridSpacingItemDecoration);
        homeBinding.recyclerFlashSale.addItemDecoration(gridSpacingItemDecoration);
        homeBinding.recyclerCategory.addItemDecoration(gridSpacingItemDecoration);
        homeBinding.recyclerOccassion.addItemDecoration(gridSpacingItemDecoration);

        bannerList = new ArrayList<>();
        bannerItemAdapter = new BannerItemAdapter(context, bannerList);
        homeBinding.recyclerBanner.setAdapter(bannerItemAdapter);

        mainModel = new MainModel(this, context);
        mainModel.getLandingPageData(Constants.API_LANDING_PAGE);
    }

    @Override
    public boolean checkInternet() {
        return Utility.isConnectedToInternet(context);
    }

    @Override
    public void mainValidateError(String whichError) {
        Utility.printLog(TAG, "whicherror -- >> " + whichError);
    }

    @Override
    public void showProgressBar() {
        homeBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        homeBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mainSuccess(HomeResponse responseModel, String whichResponse) {

        Data data = responseModel.getData();

        if (data != null) {

            if (data.getSlider() != null) {
                homeBinding.viewPager.setAdapter(new SliderPagerAdapter(context, data.getSlider()));
            }

            if (data.getBanner() != null) {
                homeBinding.txtHeading.setText(getString(R.string.now_or_never));
                bannerList.addAll(data.getBanner());
                bannerItemAdapter.notifyDataSetChanged();
            }

            if (data.getTrendingProduct() != null) {
                homeBinding.relHeading.setVisibility(View.VISIBLE);
                homeBinding.txtHeadingTrending.setText(getString(R.string.trending));
                productItemAdapter = new ProductItemAdapter(context, data.getTrendingProduct(),false);
                homeBinding.recyclerTrending.setAdapter(productItemAdapter);
            }

            if (data.getFlashSale() != null) {
                homeBinding.relHeadingSale.setVisibility(View.VISIBLE);
                homeBinding.txtHeadingSale.setText(getString(R.string.flash_sale));
                flashSaleAdapter = new FlashSaleAdapter(context, data.getFlashSale());
                homeBinding.recyclerFlashSale.setAdapter(flashSaleAdapter);
            }

            if (data.getBottomBanner() != null) {
                Glide.with(context).load(data.getBottomBanner()).into(homeBinding.imgBottomBanner);
            }

            if (data.getCategoryList() != null) {
                homeBinding.relHeadingCategory.setVisibility(View.VISIBLE);
                homeBinding.txtHeadingCategory.setText(getString(R.string.category));
                homeBinding.recyclerCategory.setLayoutManager(new GridLayoutManager(context, 5));
                categoryItemAdapter = new CategoryItemAdapter(context, data.getCategoryList());
                homeBinding.recyclerCategory.setAdapter(categoryItemAdapter);
            }

            if (data.getOccasionWear() != null) {
                homeBinding.relHeadingOccassion.setVisibility(View.VISIBLE);
                homeBinding.txtHeadingOccassion.setText(getString(R.string.occassion_wear));
                occassionWearAdapter = new OccassionWearAdapter(context, data.getOccasionWear());
                homeBinding.recyclerOccassion.setAdapter(occassionWearAdapter);
            }
        }
    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG, "err -- >> " + err);
    }

}
