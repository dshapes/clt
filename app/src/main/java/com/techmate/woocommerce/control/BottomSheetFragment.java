package com.techmate.woocommerce.control;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
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
import com.techmate.woocommerce.adapter.SizeColorAdapter;
import com.techmate.woocommerce.ui.CartActivity;
import com.techmate.woocommerce.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private int layoutId;
    private View view;
    private BottomSheetCallback callback;
    private Context context;
    private List<String> list;
    private int type;

    public BottomSheetFragment(int layoutId, int type, BottomSheetCallback callback) {
        this.layoutId = layoutId;
        this.callback = callback;
        this.type = type;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(layoutId, container, false);
        context = getActivity();
        init(view);
        return view;
    }

    private void init(View view) {

        if (type == 0) {
            TextView txtPromo = view.findViewById(R.id.txtPromo);
            txtPromo.setOnClickListener(v -> showPromoDialog());
        } else if (type == 1) {
            list = new ArrayList<>();
            list.add("S");
            list.add("M");
            list.add("L");
            list.add("XL");
            list.add("XXL");
            RecyclerView recyclerSizeColor = view.findViewById(R.id.recyclerSizeColor);
            TextView txtTitle = view.findViewById(R.id.txtTitle);
            txtTitle.setText("Available Size");
            recyclerSizeColor.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.set(10, 10, 10, 10);
                }
            });
            recyclerSizeColor.setAdapter(new SizeColorAdapter(context, list, 1));
        } else if (type == 2) {
            list = new ArrayList<>();
            list.add("orange");
            list.add("green");
            list.add("blue");
            list.add("pink");
            TextView txtTitle = view.findViewById(R.id.txtTitle);
            txtTitle.setText("Available Color");
            RecyclerView recyclerSizeColor = view.findViewById(R.id.recyclerSizeColor);
            recyclerSizeColor.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.set(10, 10, 10, 10);
                }
            });
            recyclerSizeColor.setAdapter(new SizeColorAdapter(context, list, 2));
        } else if (type == 3) {
            TextView txtHeading = view.findViewById(R.id.txtHeading);
            TextView txtContent = view.findViewById(R.id.txtContent);
            txtHeading.setText("Product Detail");
            txtContent.setText("Orange and golden printed empire A-line kurti, has a tie-up neck, short sleeves, flared hem");

        } else if (type == 4) {
            TextView txtHeading = view.findViewById(R.id.txtHeading);
            TextView txtContent = view.findViewById(R.id.txtContent);
            txtHeading.setText("Product Material");
            txtContent.setText("\u2022 100% cotton");
        }
        else if (type == 5){
            LinearLayout llMain = view.findViewById(R.id.llMain);
            llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    callback.onSelected(0);
                }
            });

        }
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
}