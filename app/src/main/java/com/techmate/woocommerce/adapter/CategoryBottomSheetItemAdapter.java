package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.control.BottomSheetCallback;

import java.util.List;
import me.gilo.woodroid.models.Category;

public class CategoryBottomSheetItemAdapter extends RecyclerView.Adapter<CategoryBottomSheetItemAdapter.ViewHolder> {

    private static final String TAG = "CategoryBottomSheetItem";
    private List<Category> categoryListItemList;
    private LayoutInflater inflater;
    private BottomSheetCallback bottomSheetCallback;
    private Context context;

    public CategoryBottomSheetItemAdapter(Context context, List<Category> categoryListItemList, BottomSheetCallback callback) {
        this.context = context;
        this.categoryListItemList = categoryListItemList;
        this.bottomSheetCallback = callback;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryBottomSheetItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.raw_category_bottomlist_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryBottomSheetItemAdapter.ViewHolder holder, int position) {

        Log.e(TAG, "onBindViewHolder: " +  categoryListItemList.get(position).getName());
        holder.txtSubCategory.setText(categoryListItemList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetCallback.onSelected(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryListItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSubCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSubCategory = itemView.findViewById(R.id.txtSubCategory);
        }
    }
}
