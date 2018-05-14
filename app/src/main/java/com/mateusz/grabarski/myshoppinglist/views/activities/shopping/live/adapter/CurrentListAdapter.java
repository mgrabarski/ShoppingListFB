package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mateusz Grabarski on 27.02.2018.
 */

public class CurrentListAdapter extends RecyclerView.Adapter<CurrentListAdapter.ViewHolder> {

    private List<ShoppingItem> mItems;
    private CurrentListAdapterListener mListener;

    public CurrentListAdapter(List<ShoppingItem> items, CurrentListAdapterListener listener) {
        this.mItems = items;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_shopping_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(mItems.get(position), mListener, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_current_shopping_list_name_cb)
        CheckBox nameCb;

        @BindView(R.id.item_current_shopping_list_edit_iv)
        ImageView editIv;

        @BindView(R.id.item_current_shopping_list_delete_iv)
        ImageView deleteIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(final ShoppingItem item, final CurrentListAdapterListener listener, final int position) {
            nameCb.setChecked(item.isInCart());
            nameCb.setText(item.getDisplayValue());

            nameCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = ((CheckBox) v);
                    item.setInCart(cb.isChecked());
                    mListener.onItemCheck(item, position);
                }
            });

            editIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemEditClick(item, position);
                }
            });

            deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemDeleteClick(item, position);
                }
            });
        }
    }
}
