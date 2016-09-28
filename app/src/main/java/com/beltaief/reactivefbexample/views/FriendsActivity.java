package com.beltaief.reactivefbexample.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beltaief.reactivefacebook.example.R;
import com.beltaief.reactivefacebook.models.Profile;
import com.beltaief.reactivefacebook.models.Profile.Properties;
import com.beltaief.reactivefacebook.requests.ReactiveRequest;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.beltaief.reactivefacebook.models.Profile.Properties.AGE_RANGE;
import static com.beltaief.reactivefacebook.models.Profile.Properties.BIRTHDAY;
import static com.beltaief.reactivefacebook.models.Profile.Properties.Builder;
import static com.beltaief.reactivefacebook.models.Profile.Properties.EMAIL;
import static com.beltaief.reactivefacebook.models.Profile.Properties.FIRST_NAME;
import static com.beltaief.reactivefacebook.models.Profile.Properties.GENDER;
import static com.beltaief.reactivefacebook.models.Profile.Properties.ID;
import static com.beltaief.reactivefacebook.models.Profile.Properties.INSTALLED;
import static com.beltaief.reactivefacebook.models.Profile.Properties.LAST_NAME;
import static com.beltaief.reactivefacebook.models.Profile.Properties.NAME;

public class FriendsActivity extends AppCompatActivity {

    private static final String TAG = FriendsActivity.class.getSimpleName();
    private TextView emptyView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        emptyView = (TextView) findViewById(R.id.empty);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        //recyclerView.

        getFriends();

    }

    /**
     * get the user list of firends who also use the app
     */
    public void getFriends() {
        final Properties properties = getProperties();
        showProgress();

        ReactiveRequest.getFriends(properties)
                .subscribe(new SingleObserver<List<Profile>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<Profile> value) {
                        Log.d(TAG, "onNext");
                        hideProgress();
                        fillList(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError :" + e.getMessage());
                        hideProgress();
                    }

                });
    }

    private void fillList(List<Profile> profileList) {

    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminate(false);
    }


    public Properties getProperties() {
        return new Builder()
                .add(ID)
                .add(FIRST_NAME)
                .add(LAST_NAME)
                .add(NAME)
                .add(BIRTHDAY)
                .add(AGE_RANGE)
                .add(EMAIL)
                .add(GENDER)
                .add(INSTALLED)
                .build();
    }
}