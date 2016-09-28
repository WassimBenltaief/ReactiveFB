package com.beltaief.reactivefbexample.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.beltaief.reactivefbexample.R;

import java.util.List;

/**
 * Created from template by Wassim Beltaief
 */
public class ExampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private List<Example> mCollection;

    public ExampleAdapter(List<Example> collection, RecyclerViewClickListener listener) {
        this.mCollection = collection;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_example, parent, false);

        ExampleHolder viewHolder = new ExampleHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExampleHolder mHolder = (ExampleHolder) holder;
        mHolder.button.setText(mCollection.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public void setData(List<Example> data) {
        mCollection = data;
    }


    class ExampleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button button;

        public ExampleHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.recyclerViewListClicked(view, getLayoutPosition(), view.getId());
        }
    }
}

