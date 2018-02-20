package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class EditShoppingListAdapter extends RecyclerView.Adapter<EditShoppingListAdapter.ViewHolder> {

    private List<ShoppingItem> shoppingItems;

    public EditShoppingListAdapter(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
