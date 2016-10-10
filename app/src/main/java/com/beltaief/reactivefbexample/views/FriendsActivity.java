package com.beltaief.reactivefbexample.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.beltaief.reactivefb.requests.ReactiveRequest;
import com.beltaief.reactivefbexample.R;
import com.beltaief.reactivefbexample.models.Profile;
import com.beltaief.reactivefbexample.util.FriendsAdapter;
import com.beltaief.reactivefbexample.util.GsonDateTypeAdapter;
import com.beltaief.reactivefbexample.util.RecyclerViewClickListener;
import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


public class FriendsActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private static final String TAG = FriendsActivity.class.getSimpleName();
    private TextView emptyView;

    private RecyclerView recycler;
    private FriendsAdapter mAdapter;
    private List<Profile> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        emptyView = (TextView) findViewById(R.id.empty);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        recycler.setNestedScrollingEnabled(false);
        mAdapter = new FriendsAdapter(friends, this);
        recycler.setAdapter(mAdapter);

        getFriends();

    }

    /**
     * get the user list of firends who also use the app
     */
    public void getFriends() {
        final String bundleAsString = "picture.width(147).height(147),name,first_name";

        ReactiveRequest.getFriends(bundleAsString)
                .map(this::transform)
                .subscribe(new SingleObserver<List<Profile>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<Profile> profiles) {
                        Log.d(TAG, "onSuccess");
                        fillList(profiles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }
                });
    }

    private void fillList(List<Profile> profileList) {
        if(profileList.isEmpty()){
            setViewVisibility(recycler, View.GONE);
            setViewVisibility(emptyView, View.VISIBLE);
        } else {
            setViewVisibility(recycler, View.VISIBLE);
            setViewVisibility(emptyView, View.GONE);
        }
        friends = profileList;
        mAdapter.setData(friends);
        mAdapter.notifyDataSetChanged();
    }

    private void setViewVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }


    @Override
    public void recyclerViewListClicked(View v, int position, int id) {
        Intent intent = new Intent(this, SingleFriendActivity.class);
        intent.putExtra("id", friends.get(position).getId());
        startActivity(intent);
    }

    private List<Profile> transform(GraphResponse response) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonDateTypeAdapter())
                .create();
        String data = null;
        try {
            data = response.getJSONObject().has("data") ?
                    response.getJSONObject().get("data").toString() :
                    response.getJSONObject().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Type listType = new TypeToken<List<Profile>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }
}