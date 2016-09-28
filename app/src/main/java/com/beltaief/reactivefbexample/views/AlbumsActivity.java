package com.beltaief.reactivefbexample.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beltaief.reactivefacebook.ReactiveFB;
import com.beltaief.reactivefacebook.actions.login.ReactiveLogin;
import com.beltaief.reactivefacebook.example.R;
import com.beltaief.reactivefacebook.example.util.AlbumsAdapter;
import com.beltaief.reactivefacebook.example.util.RecyclerViewClickListener;
import com.beltaief.reactivefacebook.models.Album;
import com.beltaief.reactivefacebook.models.Permission;
import com.beltaief.reactivefacebook.models.Photo;
import com.beltaief.reactivefacebook.requests.ReactiveRequest;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

public class AlbumsActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private static final String TAG = AlbumsActivity.class.getSimpleName();
    private TextView result;
    private RecyclerView recycler;
    private AlbumsAdapter mAdapter;
    private List<Photo> photos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        result = (TextView) findViewById(R.id.result);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setNestedScrollingEnabled(false);
        mAdapter = new AlbumsAdapter(photos, this);
        recycler.setAdapter(mAdapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean permissionGranted = ReactiveFB.checkPermission(Permission.USER_PHOTOS);
                if (permissionGranted) {
                    getAlbums();
                } else {
                    List<Permission> permissions = new ArrayList<>();
                    permissions.add(Permission.USER_PHOTOS);
                    requestAdditionalPermission(permissions);
                }

            }
        });
    }

    private void requestAdditionalPermission(List<Permission> permissions) {
        ReactiveLogin.requestAdditionalPermission(permissions, this)
                .subscribe(new MaybeObserver<LoginResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "onSuccess");
                        // verify if permission was granted
                        if (loginResult.getRecentlyDeniedPermissions()
                                .contains(Permission.USER_PHOTOS.getValue())) {
                            // permission was refused, show a toast :
                            Toast.makeText(getApplicationContext(), "We cannot get your photos " +
                                    "without your permissions", Toast.LENGTH_LONG).show();
                        } else {
                            // permission was granted, get albums
                            getAlbums();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    public void getAlbums() {

        ReactiveRequest
                .getAlbums()  // get albums
                .toObservable()
                .flatMap(new Function<List<Album>, ObservableSource<Album>>() {
                    @Override
                    public ObservableSource<Album> apply(List<Album> alba) throws Exception {
                        return Observable.fromIterable(alba);
                    }
                })
                .flatMap(new Function<Album, ObservableSource<Photo>>() { // get cover_photo data for every album
                    @Override
                    public ObservableSource<Photo> apply(Album album) throws Exception {
                        return ReactiveRequest.getPhoto(album.getCover().getId()).toObservable();
                    }
                })
                .subscribe(new DisposableObserver<Photo>() {
                    @Override
                    public void onNext(Photo photo) {
                        Log.d(TAG, "onNext");
                        addPhoto(photo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    private void addPhoto(Photo photo) {
        mAdapter.addItem(photo);
    }


    @Override
    public void recyclerViewListClicked(View v, int position, int id) {
        // TODO open one album activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ReactiveLogin.onActivityResult(requestCode, resultCode, data);
    }
}
