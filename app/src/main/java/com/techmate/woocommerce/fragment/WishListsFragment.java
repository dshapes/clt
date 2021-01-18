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

import com.techmate.woocommerce.adapter.ProductItemAdapter;
import com.techmate.woocommerce.adapter.WishlistAdapter;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.FragmentWishlistBinding;
import com.techmate.woocommerce.model.ImagesItem;
import com.techmate.woocommerce.model.TrendingProductItem;

import java.util.ArrayList;
import java.util.List;

public class WishListsFragment extends Fragment {

    private static final String TAG = "WishListsFragment";
    private Context context;
    private FragmentWishlistBinding binding;
    private String from = "";
    private List<TrendingProductItem> trendingProductItemList;

    public static WishListsFragment getInstance(String from) {
        WishListsFragment wishListsFragment = new WishListsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        wishListsFragment.setArguments(bundle);
        return wishListsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWishlistBinding.inflate(getLayoutInflater(), container, false);
        Bundle bundle = getArguments();
        if (bundle.getString("from") != null) {
            from = bundle.getString("from");
        }
        initViews();
        return binding.getRoot();
    }

    private void initViews() {

        context = getActivity();
        trendingProductItemList = new ArrayList<>();
        binding.recyclerWishlist.setLayoutManager(new GridLayoutManager(context, 2));
        binding.recyclerWishlist.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));

        for (int i = 0; i < 4; i++) {
            TrendingProductItem trendingProductItem = new TrendingProductItem();
            trendingProductItem.setName("Printed A-Line Tunic");
            trendingProductItem.setPrice("Rs 1000");
            trendingProductItem.setSalePrice("Rs 600");
            ImagesItem imagesItem = new ImagesItem();
            imagesItem.setSrc("https://clothinaa.com/wp-content/uploads/2020/12/830003717-2_1.jpg");
            List<ImagesItem> imagesItemList = new ArrayList<>();
            imagesItemList.add(imagesItem);
            trendingProductItem.setImages(imagesItemList);
            trendingProductItemList.add(trendingProductItem);
        }

        if (from.equalsIgnoreCase("CategoryDetail")) {
            binding.recyclerWishlist.setAdapter(new ProductItemAdapter(context, trendingProductItemList));
        } else {
            binding.recyclerWishlist.setAdapter(new WishlistAdapter(context, trendingProductItemList));
        }

    }

}
