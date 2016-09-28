package com.beltaief.reactivefbexample.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beltaief.reactivefacebook.example.R;
import com.beltaief.reactivefacebook.models.Profile;
import com.beltaief.reactivefacebook.requests.ReactiveRequest;
import com.beltaief.reactivefacebook.util.Attributes;
import com.beltaief.reactivefacebook.util.PictureAttributes;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProfile();
            }
        });
    }


    private void getProfile() {

        Profile.Properties properties = getProperties();

        ReactiveRequest
                .getCurrentProfile(properties)
                .subscribe(new SingleObserver<Profile>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                        result.append("onSubscribe");
                        result.append("\n");
                    }

                    @Override
                    public void onSuccess(Profile value) {
                        Log.d(TAG, "onSuccess");
                        result.append("onSuccess");
                        result.append("\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                        result.append("onError "+ e.getMessage());
                        result.append("\n");
                    }
                });
    }


    private Profile.Properties getProperties() {
        PictureAttributes pictureAttributes = Attributes.createPictureAttributes();
        pictureAttributes.setHeight(500);
        pictureAttributes.setWidth(500);
        pictureAttributes.setType(PictureAttributes.PictureType.SQUARE);

        return new Profile.Properties.Builder()
                .add(Profile.Properties.BIRTHDAY)
                .add(Profile.Properties.PICTURE, pictureAttributes)
                .add(Profile.Properties.FIRST_NAME)
                .add(Profile.Properties.LAST_NAME)
                .add(Profile.Properties.EMAIL)
                .add(Profile.Properties.BIO)
                .build();
    }


}
