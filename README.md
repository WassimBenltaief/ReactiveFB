ReactiveFB
=================

ReactiveFB provides an API that bridges the reactive world of RxJava 2.0 with the callback-style of Facebook-SDK.
The aim of the library is to :

- make the use of the facebook api easy and less boilerplate.
- provide defined facebook utile classes like LoginResult, Profile, Album, Photo directly in the RxJava onSuccess or onNext Methods.
- expose the facebook api methods to the possibilities of the reactive world like transformations, filtering, composition.. 

### Download :

Add this dependency to your app ```build.gradle``` file :

```groovy
compile 'com.beltaief.reactivefacebook:reactivefb:0.1.0'
```

This lib depends on :
- facebook-android-sdk:4.15.0
- gson:2.4
- rxjava:2.0.0-RC2
- rxandroid:2.0.0-RC1


How to use it ?
===============

First initialize the lib :

```java
ReactiveFB.sdkInitialize(this);
```

then you can use one of the methods provided by ReactiveFB :


### Login :

```java
// Login with LoginManager
Maybe<LoginResult> loginResult = ReactiveLogin.login(activityInstance);

// Login with LoginButton from an activity
Observable<LoginResult> loginResult = ReactiveLogin.loginWithButton(loginButtonInstance);

// Login with LoginButton from a fragment
Observable<LoginResult> loginResult = ReactiveLogin.loginWithButton(loginButtonInstance, fragmentInstance);
```

Note that you still have to add onActivityResult to intercept the facebook callbacks :

```java
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    ReactiveLogin.onActivityResult(requestCode, resultCode, data);
}
```


### Login with additional permisisons

Check if permission is granted :

```java
boolean permissionIsGranted = ReactiveFB.checkPermission(Permission.USER_PHOTOS);
```

if a specific permission was not granted, ask for it with an additional permission call :

```java
MaybeObserver<LoginResult> loginResult = ReactiveLogin.requestAdditionalPermission(permissions, activityInstance);
```

Note you still have to pass a list of ```Permission``` to ask for additional permissions.
```Permission``` is an enum of facebook permissions.

Example :

```java
List<Permission> permissions = new ArrayList<>();
permissions.add(Permission.USER_PHOTOS);
permissions.add(Permission.USER_FRIENDS);
permissions.add(Permission.PUBLISH_ACTION);
permissions.add(Permission.RSVP_EVENT);

ReactiveLogin.requestAdditionalPermission(permissions, activityInstance).subscribe(...)
```


### Graph Api Requests


```java
// Get logged in user profile
Single<Profile> currentProfile = ReactiveRequest.getCurrentProfile(properties);

// get logged in user friends
Single<List<Profile>> friends = ReactiveRequest.getFriends(properties);

// get profile by facebook id
Single<Profile> profile = ReactiveRequest.getProfileById(properties, profileId);

// get logged in user albums
Single<List<Album>> albums = ReactiveRequest.getAlbums();

// get a photo by id
Single<Photo> photo = ReactiveRequest.getPhoto(photoId);
```

You can compose with rx operators to get to your goal faster and efficiently.
For example if you wants to get the logged in user albums and every photo of an album, in one block :

```java

ReactiveRequest
  .getAlbums()
  .toObservable()
  .flatMap(new Function<List<Album>, ObservableSource<Album>>() { // stream the album collection
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
  
private void addPhoto(Photo photo) {
  // add item
  mAdapter.addItem(photo);
  
  // notify inserted
  ...
}

```

### More examples :

Check the sample [project](https://github.com/WassimBenltaief/ReactiveFB/tree/master/reactivefacebook) for more examples.


### Credits
This library is based on a fork of [simple-facebook](https://github.com/sromku/android-simple-facebook) by sromku.
All credits of the graph api helper classes returns to the nice work of sromku.
