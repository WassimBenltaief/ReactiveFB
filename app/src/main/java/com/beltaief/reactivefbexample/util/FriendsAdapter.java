package com.beltaief.reactivefbexample.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beltaief.reactivefb.models.Profile;
import com.beltaief.reactivefbexample.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by wassim on 9/28/16.
 */

public class FriendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private List<Profile> mCollection;

    public FriendsAdapter(List<Profile> collection, RecyclerViewClickListener listener) {
        this.mCollection = collection;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);

        FriendHolder viewHolder = new FriendHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendHolder mHolder = (FriendHolder) holder;
        Profile profile = mCollection.get(position);
        mHolder.title.setText(profile.getName());
        Glide.with(mHolder.title.getContext())
                .load(profile.getPicture())
                .centerCrop()
                .into(mHolder.image);
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public void setData(List<Profile> data) {
        mCollection.clear();
        mCollection.addAll(data);
    }

    public void addItem(Profile profile) {
        mCollection.add(profile);
        notifyItemInserted(getItemCount() - 1);
    }


    class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView title;
        RelativeLayout container;

        public FriendHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            container = (RelativeLayout) itemView.findViewById(R.id.container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.recyclerViewListClicked(view, getLayoutPosition(), view.getId());
        }
    }
}

