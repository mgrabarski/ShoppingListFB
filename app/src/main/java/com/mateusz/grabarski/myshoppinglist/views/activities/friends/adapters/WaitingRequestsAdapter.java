package com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters.listeners.FriendRequestListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public class WaitingRequestsAdapter extends RecyclerView.Adapter<WaitingRequestsAdapter.ViewHolder> {

    private List<FriendRequest> mRequests;
    private FriendRequestListener mListener;

    public WaitingRequestsAdapter(List<FriendRequest> requests, FriendRequestListener listener) {
        this.mRequests = requests;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(mRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_friend_request_root_cv)
        CardView rootCv;

        @BindView(R.id.item_friend_request_avatar_iv)
        ImageView avatarIv;

        @BindView(R.id.item_friend_request_name_tv)
        TextView nameTv;

        @BindView(R.id.item_friend_request_accept_iv)
        ImageView acceptIv;

        @BindView(R.id.item_friend_request_refuse_iv)
        ImageView refuseIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(FriendRequest friendRequest) {

            // TODO: 22.05.2018

            acceptIv.setOnClickListener(v -> mListener.onAccept(friendRequest));
            refuseIv.setOnClickListener(v -> mListener.onRefused(friendRequest));
        }
    }
}
