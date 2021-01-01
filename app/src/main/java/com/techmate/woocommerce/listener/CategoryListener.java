package com.techmate.woocommerce.listener;

import com.techmate.woocommerce.model.CategoryListItem;

import java.util.List;

public interface CategoryListener {
    void updateView(boolean success, List<CategoryListItem> categoryListItemList);
}