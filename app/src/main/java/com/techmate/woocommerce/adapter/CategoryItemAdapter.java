package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.model.CategoryListItem;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {

    private static final String TAG = "CategoryItemAdapter";
    private List<CategoryListItem> categoryListItemList;
    private Context context;
    private int size = 0;

    public CategoryItemAdapter(Context context, List<CategoryListItem> categoryListItemList, int size) {
        this.context = context;
        this.size = size;
        this.categoryListItemList = categoryListItemList;
    }

    @NonNull
    @Override
    public CategoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemAdapter.ViewHolder holder, int position) {
//        Glide.with(context).load("https://clothinaa.com/wp-content/uploads/2020/11/KR720KSL.jpg").centerInside().into(holder.civCategoryImage);
        Glide.with(context).load(categoryListItemList.get(position).getImage()).centerInside().into(holder.civCategoryImage);
        holder.txtCategoryName.setText(categoryListItemList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void setListSize(int size) {
        this.size = size;
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
