package com.beltaief.reactivefbexample.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beltaief.reactivefacebook.example.R;
import com.beltaief.reactivefacebook.models.Photo;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created from template by Wassim Beltaief
 */
public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private List<Photo> mCollection;

    public AlbumsAdapter(List<Photo> collection, RecyclerViewClickListener listener) {
        this.mCollection = collection;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_album, parent, false);

        AlbumHolder viewHolder = new AlbumHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumHolder mHolder = (AlbumHolder) holder;
        Photo photo = mCollection.get(position);
        mHolder.title.setText(photo.getAlbum().getName());
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
        mCollection = data;
    }

    public void addItem(Photo photo) {
        mCollection.add(photo);
        notifyItemInserted(getItemCount() - 1);
    }


    class AlbumHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView title;
        RelativeLayout container;

        public AlbumHolder(View itemView) {
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

