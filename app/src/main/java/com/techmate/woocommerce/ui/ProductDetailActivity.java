package com.techmate.woocommerce.ui;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.MoreOffersAdapter;
import com.techmate.woocommerce.adapter.YouMayLikeAdapter;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.ActivityProductDetailBinding;
import com.techmate.woocommerce.model.ImagesItem;
import com.techmate.woocommerce.model.TrendingProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityProductDetailBinding binding;
    private YouMayLikeAdapter adapter;
    private MoreOffersAdapter offersAdapter;
    private List<TrendingProductItem> productItems;
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
        binding.recyclerYouMayLike.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.img_wishlist:
                break;
        }
    }
}