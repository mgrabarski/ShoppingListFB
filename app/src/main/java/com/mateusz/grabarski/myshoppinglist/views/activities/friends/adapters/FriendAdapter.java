package com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters.listeners.FriendListListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mateusz Grabarski on 18.05.2018.
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private List<User> users;
    private FriendListListener mListener;

    public FriendAdapter(List<User> users, FriendListListener mListener) {
        this.users = users;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frient_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_friend_root_cv)
        CardView rootCv;

        @BindView(R.id.item_friend_avatar_iv)
        ImageView avatarIv;

        @BindView(R.id.item_friend_name_tv)
        TextView nameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(final User user) {
            nameTv.setText(user.getName());

            Picasso
                    .with(avatarIv.getContext())
                    .load(user.getPictureUrl())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.ic_empty_avatar)
                    .into(avatarIv, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            avatarIv.setImageResource(R.drawable.ic_empty_avatar);
                        }
                    });

            rootCv.setOnClickListener(view -> mListener.onUserSelected(user));
        }
    }
}
