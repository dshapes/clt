package com.techmate.woocommerce.control;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CategoryBottomSheetItemAdapter;
import com.techmate.woocommerce.adapter.SizeColorAdapter;
import com.techmate.woocommerce.ui.CartActivity;
import com.techmate.woocommerce.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import me.gilo.woodroid.models.Category;
import me.gilo.woodroid.models.Option;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    int layoutId;
    private static final String TAG = "BottomSheetFragment";
    private BottomSheetCallback callback;
    private Context context;
    private List<Category> categoryList;
    private String cat_name = "";
    private int type = 6;
    View view;
    private String productDetails = "", productMaterial = "";
    private List<String> sizeList = new ArrayList<>();
    private List<String> colorList = new ArrayList<>();

    public BottomSheetFragment(int layoutId, int type, BottomSheetCallback callback) {
        this.layoutId = layoutId;
        this.callback = callback;
        this.type = type;
    }

    public BottomSheetFragment(int layoutId, List<Category> list, String cat_name, BottomSheetCallback callback) {
        this.layoutId = layoutId;
        this.callback = callback;
        this.categoryList = list;
        this.cat_name = cat_name;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(layoutId, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        if (type == 0) {
            TextView txtPromo = view.findViewById(R.id.txtPromo);
            txtPromo.setOnClickListener(v -> showPromoDialog());
        } else if (type == 1) {
            RecyclerView recyclerSizeColor = view.findViewById(R.id.recyclerSizeColor);
            TextView txtTitle = view.findViewById(R.id.txtTitle);
            txtTitle.setText("Available Size");
            recyclerSizeColor.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.set(10, 10, 10, 10);
                }
            });
            recyclerSizeColor.setAdapter(new SizeColorAdapter(context, sizeList, Constants.BOTTOM_SHEET_VIEW_TYPE.SIZE));
        } else if (type == 2) {
            TextView txtTitle = view.findViewById(R.id.txtTitle);
            txtTitle.setText("Available Color");
            RecyclerView recyclerSizeColor = view.  findViewById(R.id.recyclerSizeColor);
            recyclerSizeColor.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.set(10, 10, 10, 10);
                }
            });
            recyclerSizeColor.setAdapter(new SizeColorAdapter(context, colorList, Constants.BOTTOM_SHEET_VIEW_TYPE.COLOR));
        } else if (type == 3) {
            TextView txtHeading = view.findViewById(R.id.txtHeading);
            TextView txtContent = view.findViewById(R.id.txtContent);
            txtHeading.setText("Product Detail");
            txtContent.setText(Html.fromHtml(productDetails));
        } else if (type == 4) {
            TextView txtHeading = view.findViewById(R.id.txtHeading);
            TextView txtContent = view.findViewById(R.id.txtContent);
            txtHeading.setText("Product Material");
            txtContent.setText(Html.fromHtml(productMaterial));
        } else if (layoutId == R.layout.bottom_sheet_dialog_category) {
            TextView txtHeading = view.findViewById(R.id.txtHeading);
            txtHeading.setText(cat_name);
            RecyclerView recyclerCategory = view.findViewById(R.id.recyclerCategory);
            recyclerCategory.setLayoutManager(new LinearLayoutManager(context));
            recyclerCategory.setAdapter(new CategoryBottomSheetItemAdapter(context, categoryList, callback));
        }
    }

    public void setProductDetails(String productDetails){
        this.productDetails = productDetails;
    }

    public void setProductMaterial(String productMaterial){
        this.productMaterial = productMaterial;
    }

    public void setSizeList(List<String> optionList){
        this.sizeList = optionList;
    }

    private void showPromoDialog() {

        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_apply_promo);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        ImageView imgClose = dialog.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }
}