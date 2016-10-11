package com.beltaief.reactivefbexample.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beltaief.reactivefbexample.R;
import com.beltaief.reactivefbexample.models.Photo;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by wassim on 9/28/16.
 */

public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> mCollection;

    public PhotosAdapter(List<Photo> collection) {
        this.mCollection = collection;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);

        return new PhotosHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PhotosHolder mHolder = (PhotosHolder) holder;
        Photo photo = mCollection.get(position);
        String title = " - ";
        if (photo.getName() != null) {
            int possibleLength = photo.getName().length() > 20 ? 20 : photo.getName().length() - 1;
            title = photo.getName().subSequence(0, possibleLength).toString();
        }
        mHolder.title.setText(title);
        Glide.with(mHolder.title.getContext())
                .load(photo.getImages().get(photo.getImages().size() - 1).getSource())
                .centerCrop()
                .into(mHolder.image);
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public void setData(List<Photo> data) {
        mCollection.clear();
        mCollection.addAll(data);
        notifyDataSetChanged();
    }


    private class PhotosHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        PhotosHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

