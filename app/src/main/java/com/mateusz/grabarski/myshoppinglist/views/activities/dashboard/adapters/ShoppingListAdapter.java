package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.adapters.listeners.ShoppingListClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mateusz Grabarski on 23.02.2018.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private List<ShoppingList> mShoppingLists;
    private ShoppingListClickListener mListener;

    public ShoppingListAdapter(List<ShoppingList> shoppingLists, ShoppingListClickListener listener) {
        this.mShoppingLists = shoppingLists;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(mShoppingLists.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mShoppingLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_shopping_item_root_cv)
        CardView rootCv;

        @BindView(R.id.item_shopping_item_name_tv)
        TextView nameTv;

        @BindView(R.id.item_shopping_item_edit_iv)
        ImageView editIv;

        @BindView(R.id.item_shopping_item_delete_iv)
        ImageView deleteIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(final ShoppingList list, final ShoppingListClickListener listener) {
            nameTv.setText(list.getListName() + " (" + list.getShoppingItems().size() + ")");

            rootCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onListSelected(list);
                }
            });

            editIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditListClick(list);
                }
            });

            deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteListClick(list);
                }
            });
        }
    }
}
