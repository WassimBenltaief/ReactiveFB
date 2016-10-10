package com.beltaief.reactivefbexample.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefbexample.R;
import com.beltaief.reactivefbexample.util.Example;
import com.beltaief.reactivefbexample.util.ExampleAdapter;
import com.beltaief.reactivefbexample.util.RecyclerViewClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private ArrayList<Example> mExamples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("reactivefb examples");

        mExamples = new ArrayList<>();
        mExamples.add(new Example("Login ", LoginActivity.class, false));
        mExamples.add(new Example("Login with button", LoginButtonActivity.class, false));
        mExamples.add(new Example("Current Profile", ProfileActivity.class, true));
        mExamples.add(new Example("Albums Profile", AlbumsActivity.class, true));
        mExamples.add(new Example("My Pictures", MyPhotosActivity.class, true));
        mExamples.add(new Example("Friends", FriendsActivity.class, true));

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExampleAdapter mExamplesAdapter = new ExampleAdapter(mExamples, this);
        mRecyclerView.setAdapter(mExamplesAdapter);

    }

    @Override
    public void recyclerViewListClicked(View v, int position, int id) {
        if(!ReactiveFB.getSessionManager().isLoggedIn() && mExamples.get(position).isRequireLogin()){
            Toast.makeText(this, "Login required", Toast.LENGTH_LONG).show();
            return;
        }
        Class<? extends Activity> activity = mExamples.get(position).getActivity();
        if (activity != null) {
            Intent intent = new Intent(this, activity);
            startActivity(intent);
        }
    }
}
