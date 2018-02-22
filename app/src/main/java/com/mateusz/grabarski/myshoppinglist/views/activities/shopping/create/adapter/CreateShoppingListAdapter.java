package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public class CreateShoppingListAdapter extends RecyclerView.Adapter<CreateShoppingListAdapter.ViewHolder> {

    private List<ShoppingItem> shoppingItems;
    private CreateShoppingListListener listener;

    public CreateShoppingListAdapter(List<ShoppingItem> shoppingItems, CreateShoppingListListener listener) {
        this.shoppingItems = shoppingItems;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_shopping_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(shoppingItems.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_create_shopping_item_name_tv)
        TextView nameTv;

        @BindView(R.id.item_create_shopping_item_edit_iv)
        ImageView editIv;

        @BindView(R.id.item_create_shopping_item_delete_iv)
        ImageView deleteIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(final ShoppingItem shoppingItem,
                             final CreateShoppingListListener listener) {

            nameTv.setText(shoppingItem.getDisplayValue());

            editIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditClick(shoppingItem);
                }
            });

            deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(shoppingItem);
                }
            });
        }
    }
}
