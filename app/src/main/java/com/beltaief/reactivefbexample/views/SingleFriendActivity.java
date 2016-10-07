package com.beltaief.reactivefbexample.views;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.beltaief.reactivefb.requests.ReactiveRequest;
import com.beltaief.reactivefbexample.R;
import com.beltaief.reactivefbexample.models.Profile;
import com.beltaief.reactivefbexample.util.JsonTransformer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class SingleFriendActivity extends AppCompatActivity {

    private static final String TAG = SingleFriendActivity.class.getSimpleName();
    private TextView result;
    private String userId;
    private String fields;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fields = "picture.width(147).height(147),name,first_name";

        userId = savedInstanceState != null ?
                savedInstanceState.getString("id") : getIntent().getStringExtra("id");

        Button button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.result);
        imageView = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProfile(fields, userId);
            }
        });
    }

    private void getProfile(String fields, String userId) {
        ReactiveRequest.getProfile(fields, userId)
                .compose(new JsonTransformer<Profile>(Profile.class))
                .subscribe(new SingleObserver<Profile>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(Profile value) {
                        Log.d(TAG, "onSuccess");
                        fillProfile(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError "+e.getMessage());
                    }
                });
    }

    private void fillProfile(Profile value) {
        Glide.with(this)
                .load(value.getPicture())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

        result.setText(value.getName());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("id", userId);
        super.onSaveInstanceState(outState);
    }
}
