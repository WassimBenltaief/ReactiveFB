package com.beltaief.reactivefbexample.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.actions.ReactiveLogin;
import com.beltaief.reactivefb.requests.ReactiveRequest;
import com.beltaief.reactivefb.util.PermissionHelper;
import com.beltaief.reactivefbexample.R;
import com.beltaief.reactivefbexample.models.Photo;
import com.beltaief.reactivefbexample.util.GsonDateTypeAdapter;
import com.beltaief.reactivefbexample.util.PhotosAdapter;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MyPhotosActivity extends AppCompatActivity {

    private static final String TAG = MyPhotosActivity.class.getSimpleName();
    private PhotosAdapter mAdapter;
    private List<Photo> photos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_photos);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);

        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        recycler.setNestedScrollingEnabled(true);
        mAdapter = new PhotosAdapter(photos);
        recycler.setAdapter(mAdapter);

        // check permissions
        boolean permissionGranted = ReactiveFB.checkPermission(PermissionHelper.USER_PHOTOS);
        if (permissionGranted) {
            getPhotos();
        } else {
            List<PermissionHelper> permissions = new ArrayList<>();
            permissions.add(PermissionHelper.USER_PHOTOS);
            requestAdditionalPermission(permissions);
        }
    }

    private void requestAdditionalPermission(List<PermissionHelper> permissions) {
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
                                .contains(PermissionHelper.USER_PHOTOS.getValue())) {
                            // permission was refused, show a toast :
                            Toast.makeText(getApplicationContext(), "We cannot get your photos " +
                                    "without your permissions", Toast.LENGTH_LONG).show();
                        } else {
                            // permission was granted, get albums
                            getPhotos();
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

    public void getPhotos() {

        final String photoFields = "album,images,name"; // fields passed to GraphAPI like "?fields=x,x"

        ReactiveRequest
                .getMyPhotos(photoFields, 20) // get albums
                .map(this::transform) // parse json to list of Album
                .subscribe(new SingleObserver<List<Photo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<Photo> value) {
                        Log.d(TAG, "onNext");
                        appendPhotos(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError " + e.getMessage());
                    }
                });
    }

    private void appendPhotos(List<Photo> photoList) {
        mAdapter.setData(photoList);
    }

    private List<Photo> transform(GraphResponse response) {
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
            throw new JsonParseException(e);
        }
        Type listType = new TypeToken<List<Photo>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }
}
