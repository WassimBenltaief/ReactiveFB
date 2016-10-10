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
 * Created from template by Wassim Beltaief
 */
public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> mCollection;

    public PhotosAdapter(List<Photo> photos) {
        this.mCollection = photos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_album, parent, false);

        return new AlbumHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumHolder mHolder = (AlbumHolder) holder;
        Photo photo = mCollection.get(position);
        mHolder.title.setText(photo.getName());
        Glide.with(mHolder.title.getContext())
                .load(photo.getImages().get(photo.getImages().size() - 1).getSource())
                .crossFade()
                .into(mHolder.image);
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public void setData(List<Photo> data) {
        mCollection.clear();
        mCollection = data;
        notifyDataSetChanged();
    }

    public void addItem(Photo photo) {
        mCollection.add(photo);
        notifyItemInserted(getItemCount() - 1);
    }

    public void clear() {
        mCollection.clear();
        notifyDataSetChanged();
    }


    private class AlbumHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        AlbumHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

