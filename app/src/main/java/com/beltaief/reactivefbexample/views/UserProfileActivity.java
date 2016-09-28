package com.beltaief.reactivefbexample.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beltaief.reactivefacebook.example.R;
import com.beltaief.reactivefacebook.models.Profile;
import com.beltaief.reactivefacebook.models.Profile.Properties;
import com.beltaief.reactivefacebook.requests.ReactiveRequest;
import com.beltaief.reactivefacebook.util.Attributes;
import com.beltaief.reactivefacebook.util.PictureAttributes;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = UserProfileActivity.class.getSimpleName();
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final Properties properties = getProperties();
        final String userId = "10152997291386201"; // TODO : to be changed by argument

        Button button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProfile(properties, userId);
            }
        });
    }

    private void getProfile(Properties properties, String userId) {
        ReactiveRequest.getProfileById(properties, userId)
                .subscribe(new SingleObserver<Profile>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(Profile value) {
                        Log.d(TAG, "onSuccess");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError "+e.getMessage());
                    }
                });
    }

    private Properties getProperties() {
        PictureAttributes pictureAttributes = Attributes.createPictureAttributes();
        pictureAttributes.setHeight(500);
        pictureAttributes.setWidth(500);
        pictureAttributes.setType(PictureAttributes.PictureType.SQUARE);

        return new Properties.Builder()
                .add(Properties.BIRTHDAY)
                .add(Properties.PICTURE, pictureAttributes)
                .add(Properties.FIRST_NAME)
                .add(Properties.LAST_NAME)
                .add(Properties.EMAIL)
                .add(Properties.BIO)
                .build();
    }


}
