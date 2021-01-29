package com.techmate.woocommerce.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.techmate.woocommerce.BuildConfig;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.MoreOffersAdapter;
import com.techmate.woocommerce.adapter.SliderAdapter;
import com.techmate.woocommerce.adapter.YouMayLikeAdapter;
import com.techmate.woocommerce.control.BottomSheetCallback;
import com.techmate.woocommerce.control.BottomSheetFragment;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.ActivityProductDetailBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.model.ImagesItem;
import com.techmate.woocommerce.model.ProductDetailResponse;
import com.techmate.woocommerce.model.TrendingProductItem;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import me.gilo.woodroid.Woocommerce;
import me.gilo.woodroid.models.Attribute;
import me.gilo.woodroid.models.Option;
import me.gilo.woodroid.models.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, BottomSheetCallback {

    private static final String TAG = "ProductDetailActivity";
    private ActivityProductDetailBinding binding;
    private YouMayLikeAdapter adapter;
    private MoreOffersAdapter offersAdapter;
    private List<TrendingProductItem> productItems;
    private Context context;
    private int productId = 0;
    private SliderAdapter sliderAdapter;
    private String productDetails = "", productMaterial = "";
    private List<String> sizeList, colorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        context = this;
        initViews();
        apiCall();
        setContentView(binding.getRoot());
    }

    private void apiCall() {

        binding.frameProgress.setVisibility(View.VISIBLE);

        Woocommerce woocommerce = Woocommerce.Builder()
                .setSiteUrl(Constants.BASE_URL)
                .setApiVersion(Woocommerce.API_V3)
                .setConsumerKey(Constants.CONSUMER_KEY)
                .setConsumerSecret(Constants.CONSUMER_SECRET)
                .build();

        woocommerce.ProductRepository().product(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product product = response.body();

                    if (product == null) {
                        return;
                    }

                    if (product.getImages() != null && product.getImages().size() > 0) {
                        sliderAdapter.addAllItems(product.getImages());
                        sliderAdapter.notifyDataSetChanged();
                        binding.viewPager2.setCurrentItem(0);
                    }

                    if (!TextUtils.isEmpty(product.getName())) {
                        binding.txtProductName.setText(product.getName());
                    }

                    if (!TextUtils.isEmpty(product.getPrice())) {
                        binding.txtPrice.setText(product.getPrice());
                    }

                    if (!TextUtils.isEmpty(product.getPrice_html())) {
                        binding.txtOriginalPrice.setText(Html.fromHtml(product.getPrice_html()));
                    }

                    if (!TextUtils.isEmpty(product.getShort_description())) {
                        productDetails = product.getShort_description();
                    }

                    if (!TextUtils.isEmpty(product.getShort_description())) {
                        productMaterial = product.getShort_description();
                    }

                    if (product.getAttributes() != null && product.getAttributes().size() > 0) {

                        for (Attribute attribute : product.getAttributes()) {

                            if (attribute.getName().equals("Size")) {
                                sizeList.addAll(Arrays.asList(attribute.getOptions()));
                            }

                            if (attribute.getName().equals("Color")) {
                                colorList.addAll(Arrays.asList(attribute.getOptions()));
                            }

                        }
                    }
                }

                binding.frameProgress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Utility.printLog(TAG, t.getMessage());
                binding.frameProgress.setVisibility(View.GONE);
            }
        });


    }

    private void initViews() {

        binding.txtOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        productItems = new ArrayList<>();
        binding.imgBack.setOnClickListener(this);
        binding.llSize.setOnClickListener(this);
        binding.llColor.setOnClickListener(this);
        binding.llShare.setOnClickListener(this);
        binding.llProductDetails.setOnClickListener(this);
        binding.llProductMaterials.setOnClickListener(this);
        binding.recyclerOffers.setNestedScrollingEnabled(false);
        binding.recyclerYouMayLike.setNestedScrollingEnabled(false);
        binding.recyclerYouMayLike.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
        binding.btnAddtoCart.setOnClickListener(this);
        binding.btnBuyNow.setOnClickListener(this);

        sizeList = new ArrayList<>();
        colorList = new ArrayList<>();

        sliderAdapter = new SliderAdapter(context);
        binding.viewPager2.setAdapter(sliderAdapter);

        if (getIntent().getExtras() != null) {
            productId = getIntent().getExtras().getInt(Constants.INTENT_PRODUCT_ID);
        }

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
            binding.viewPager2.setCurrentItem(0);
        });
        tabLayoutMediator.attach();

/*
        for (int i = 0; i < 2; i++) {
            TrendingProductItem trendingProductItem = new TrendingProductItem();
            trendingProductItem.setName("Printed A-Line Tunic");
            trendingProductItem.setPrice("Rs 1000");
            trendingProductItem.setSalePrice("Rs 600");
            ImagesItem imagesItem = new ImagesItem();
            imagesItem.setSrc("https://clothinaa.com/wp-content/uploads/2020/12/830003717-2_1.jpg");
            List<ImagesItem> imagesItemList = new ArrayList<>();
            imagesItemList.add(imagesItem);
            trendingProductItem.setImages(imagesItemList);
            productItems.add(trendingProductItem);
        }

        offersAdapter = new MoreOffersAdapter(context);
        binding.recyclerOffers.setAdapter(offersAdapter);

        adapter = new YouMayLikeAdapter(context, productItems);
        binding.recyclerYouMayLike.setAdapter(adapter);
*/

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.img_wishlist:
                break;
            case R.id.btnAddtoCart:
                Utility.startActivity((Activity) context, CartActivity.class);
                break;
            case R.id.llSize:
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_recycler, 1, this);
                bottomSheetFragment.setSizeList(sizeList);
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                break;
            case R.id.llColor:
                BottomSheetFragment bottomFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_recycler, 2, this);
                bottomFragment.setColorList(colorList);
                bottomFragment.show(getSupportFragmentManager(), bottomFragment.getTag());
                break;
            case R.id.llProductDetails:
                if (!TextUtils.isEmpty(productDetails)) {
                    BottomSheetFragment bottomDetailFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_product_detail, 3, this);
                    bottomDetailFragment.setProductDetails(productDetails);
                    bottomDetailFragment.show(getSupportFragmentManager(), bottomDetailFragment.getTag());
                }
                break;
            case R.id.llProductMaterials:
                if (!TextUtils.isEmpty(productMaterial)) {
                    BottomSheetFragment bottomMaterialFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_product_detail, 4, this);
                    bottomMaterialFragment.setProductMaterial(productMaterial);
                    bottomMaterialFragment.show(getSupportFragmentManager(), bottomMaterialFragment.getTag());
                }
                break;
            case R.id.btnBuyNow:
                Utility.startActivity((Activity) context, ShipToActivity.class);
                break;
            case R.id.llShare:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

        }
    }

    @Override
    public void onSelected(int position) {

    }

}