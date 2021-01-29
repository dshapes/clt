package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.fragment.CategoriesFragment;
import com.techmate.woocommerce.model.CategoryListItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.gilo.woodroid.models.Category;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private static final String TAG = "CategoryItemAdapter";
    private List<Category> categoryListItemList;
    private List<CategoryListItem> categoryHomeItemList;
    private Context context;
    private CategoriesFragment categoriesFragment;

    public CategoryListAdapter(Context context, List<CategoryListItem> categoryListItemList) {
        this.context = context;
        this.categoryHomeItemList = categoryListItemList;
    }

    public CategoryListAdapter(CategoriesFragment categoriesFragment, Context context, List<Category> categoryListItemList) {
        this.categoriesFragment = categoriesFragment;
        this.context = context;
        this.categoryListItemList = categoryListItemList;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {

        if (categoryHomeItemList != null) {

            if (categoryHomeItemList.get(position).getImage() != null) {
                Glide.with(context).load(categoryHomeItemList.get(position).getImage()).centerInside().into(holder.civCategoryImage);
            }

            if (categoryHomeItemList.get(position).getName() != null) {
                holder.txtCategoryName.setSelected(true);
                holder.txtCategoryName.setText(categoryHomeItemList.get(position).getName());
            }


        } else if (categoryListItemList != null) {

            if (categoryListItemList.get(position).getImage() != null) {
                Glide.with(context).load(categoryListItemList.get(position).getImage()).centerInside().into(holder.civCategoryImage);
            }

            if (categoryListItemList.get(position).getName() != null) {
                holder.txtCategoryName.setSelected(true);
                holder.txtCategoryName.setText(categoryListItemList.get(position).getName());
            }

            holder.itemView.setOnClickListener(v -> {

                if (categoriesFragment == null) {
                    return;
                }

                categoriesFragment.showBottomSheetDialog(categoryListItemList.get(position).getParent(),position);

            });


        }


    }

    @Override
    public int getItemCount() {
        return categoriesFragment == null ? 5 : categoryListItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView civCategoryImage;
        private TextView txtCategoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            civCategoryImage = itemView.findViewById(R.id.civCategoryImage);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
        }
    }
}
