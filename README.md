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
compile 'com.beltaief.reactivefacebook:reactivefb:0.1.0-alpha.1'
```

This lib depends on :
- facebook-android-sdk:4.15.0
- rxjava:2.0.0-RC3
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

Alternatively, you can configure the permissions when initializing the lib. Provide a configuration and a list of permissions and set it just after initialization :

```java
// define list of permissions
Permission[] permissions = new Permission[]{
        Permission.USER_ABOUT_ME,
        Permission.EMAIL,
        Permission.USER_PHOTOS,
        Permission.USER_EVENTS,
        Permission.USER_ACTIONS_MUSIC,
        Permission.USER_FRIENDS,
        Permission.USER_GAMES_ACTIVITY,
        Permission.USER_BIRTHDAY,
        Permission.USER_TAGGED_PLACES,
        Permission.USER_MANAGED_GROUPS,
        Permission.PUBLISH_ACTION};

// add permission to a configuration
SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
        .setAppId(String.valueOf(R.string.facebook_app_id))
        .setPermissions(permissions)
        .setDefaultAudience(DefaultAudience.FRIENDS)
        .setAskForAllPermissionsAtOnce(false)
        .build();

// init lib
ReactiveFB.sdkInitialize(this);
ReactiveFB.setConfiguration(configuration);
```

### Graph Api Requests


```java
// Get the profile of the logged in user
Single<GraphResponse> me = ReactiveRequest.getMe();
Single<GraphResponse> me = ReactiveRequest.getMe(fields);

// Get the list of the friends of the logged in user
Single<GraphResponse> friends = ReactiveRequest.getFriends();
Single<GraphResponse> friends = ReactiveRequest.getFriends(fields);
Single<GraphResponse> friends = ReactiveRequest.getFriends(fields, limit);

// Get a user profile by providing his facebookId
Single<GraphResponse> profile = ReactiveRequest.getProfile(profileId);
Single<GraphResponse> profile = ReactiveRequest.getProfile(profileId, fields);
Single<GraphResponse> profile = ReactiveRequest.getProfile(profileId, fields, limits);

// Get a list of albums of the logged in user
Single<GraphResponse> albums = ReactiveRequest.getMyAlbums();
Single<GraphResponse> albums = ReactiveRequest.getMyAlbums(fields);
Single<GraphResponse> albums = ReactiveRequest.getMyAlbums(fields, limit);

// Get a list of albums of a user
Single<GraphResponse> albums = ReactiveRequest.getAlbums(userId);
Single<GraphResponse> albums = ReactiveRequest.getAlbums(userId, fields);
Single<GraphResponse> albums = ReactiveRequest.getAlbums(userId, fields, limit);

// Get a photo of a user, album, page, event ..
Single<GraphResponse> photo = ReactiveRequest.getPhoto(photoId);
Single<GraphResponse> photo = ReactiveRequest.getPhoto(photoId, fields);

// Get list of photos of the logged in user
Single<GraphResponse> photos = ReactiveRequest.getMyPhotos();
Single<GraphResponse> photos = ReactiveRequest.getMyPhotos(fields);
Single<GraphResponse> photos = ReactiveRequest.getMyPhotos(fields, limit);

// Get list of photos of a user
Single<GraphResponse> photos = ReactiveRequest.getPhotos();
Single<GraphResponse> photos = ReactiveRequest.getPhotos(fields);
Single<GraphResponse> photos = ReactiveRequest.getPhotos(fields, limit);

```

You can compose with rx operators to get to your goal faster and efficiently.
For example if you wants to get the logged in user albums and every photo of an album, in one block :

```java

ReactiveRequest
    .getMyAlbums(albumFields) // get albums
    .map(this::parseAlbums) // parse json to list of Album
    .flatMapObservable(Observable::fromIterable) // iterate throw collection
    .flatMap(album -> ReactiveRequest.getPhoto(album.getCover().getId(), photoFields).toObservable()) // get one alb. photo
    .doOnError(throwable -> Observable.empty()) // return Observable.empty if error occured
    .map(this::parsePhoto)// transform json to Photo
    .subscribe(
            photo -> {
                Log.d(TAG, "onNext");
                addPhoto(photo);
            },
            throwable -> Log.d(TAG, "onError " + throwable.getMessage()),
            () -> Log.d(TAG, "onComplete")
    );
  
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
