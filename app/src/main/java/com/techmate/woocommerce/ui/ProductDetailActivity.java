package com.techmate.woocommerce.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.MoreOffersAdapter;
import com.techmate.woocommerce.adapter.YouMayLikeAdapter;
import com.techmate.woocommerce.control.BottomSheetCallback;
import com.techmate.woocommerce.control.BottomSheetFragment;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.ActivityProductDetailBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.model.ImagesItem;
import com.techmate.woocommerce.model.TrendingProductItem;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, BottomSheetCallback, ViewPresenter.MainView {

    private static final String TAG = "ProductDetailActivity";
    private ActivityProductDetailBinding binding;
    private YouMayLikeAdapter adapter;
    private MoreOffersAdapter offersAdapter;
    private List<TrendingProductItem> productItems;
    private MainModel mainModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        context = this;
        initViews();
        setContentView(binding.getRoot());
    }

    private void initViews() {

        binding.txtOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        productItems = new ArrayList<>();
        binding.imgBack.setOnClickListener(this);
        binding.llSize.setOnClickListener(this);
        binding.llColor.setOnClickListener(this);
        binding.llProductDetails.setOnClickListener(this);
        binding.llProductMaterials.setOnClickListener(this);
        binding.recyclerOffers.setNestedScrollingEnabled(false);
        binding.recyclerYouMayLike.setNestedScrollingEnabled(false);
        binding.recyclerYouMayLike.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
        binding.btnAddtoCart.setOnClickListener(this);
        binding.btnBuyNow.setOnClickListener(this);

        String productId = "";
        if (getIntent().getExtras() != null) {
            productId = getIntent().getExtras().getString(Constants.INTENT_PRODUCT_ID);
        }

        if (TextUtils.isEmpty(productId)) {
            return;
        }

        mainModel = new MainModel(this, context);
        mainModel.getDataByGet(Constants.API_PRODUCTS + "/" + productId);

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
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                break;
            case R.id.llColor:
                BottomSheetFragment bottomFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_recycler, 2, this);
                bottomFragment.show(getSupportFragmentManager(), bottomFragment.getTag());
                break;
            case R.id.llProductDetails:
                BottomSheetFragment bottomDetailFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_product_detail, 3, this);
                bottomDetailFragment.show(getSupportFragmentManager(), bottomDetailFragment.getTag());
                break;
            case R.id.llProductMaterials:
                BottomSheetFragment bottomMaterialFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_product_detail, 4, this);
                bottomMaterialFragment.show(getSupportFragmentManager(), bottomMaterialFragment.getTag());
                break;
            case R.id.btnBuyNow:
                Utility.startActivity((Activity) context, ShipToActivity.class);
                break;

        }
    }

    @Override
    public void onSelected(int position) {

    }

    @Override
    public boolean checkInternet() {
        return Utility.isConnectedToInternet(context);
    }

    @Override
    public void mainValidateError(String whichError) {
        Utility.printLog(TAG, "whicherror is " + whichError);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void mainSuccess(HomeResponse responseModel, String whichResponse) {
        Utility.printGson(TAG, responseModel);
    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG, "err is " + err);
        Toast.makeText(ProductDetailActivity.this, err,Toast.LENGTH_SHORT).show();
    }
}