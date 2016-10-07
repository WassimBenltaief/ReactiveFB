package com.beltaief.reactivefb.util;

public final class JsonUtils {

    private JsonUtils() {
    }

    /*// TODO finish the mapper with the rest of the fields
    public static Profile parseProfile(String jsonProfile) throws JSONException {
        Profile profile = new Profile();
        JSONObject jProfile = new JSONObject(jsonProfile);

        if (jProfile.has(Properties.ID)) {
            profile.setId(jProfile.getString(IdName.ID));
        }

        if (jProfile.has(Properties.NAME)) {
            profile.setName(jProfile.getString(IdName.NAME));
        }

        if (jProfile.has(Properties.FIRST_NAME)) {
            profile.setFirstName(jProfile.getString(Properties.FIRST_NAME));
        }

        if (jProfile.has(Properties.MIDDLE_NAME)) {
            profile.setMiddleName(jProfile.getString(Properties.MIDDLE_NAME));
        }

        if (jProfile.has(Properties.LAST_NAME)) {
            profile.setLastName(jProfile.getString(Properties.LAST_NAME));
        }

        if (jProfile.has(Properties.GENDER)) {
            profile.setGender(jProfile.getString(Properties.GENDER));
        }

        if (jProfile.has(Properties.LOCALE)) {
            profile.setLocale(jProfile.getString(Properties.LOCALE));
        }

        if (jProfile.has(Properties.LANGUAGE)) {
            profile.setLanguages(parseLanguages(jProfile.getJSONArray(Properties.LANGUAGE)));
        }

        profile.setPicture(parsePicture(jProfile.getJSONObject(Properties.PICTURE)));

        return profile;
    }

    private static Utils.SingleDataResult<Image> parsePicture(JSONObject jsonObject) throws JSONException {
        Utils.SingleDataResult<Image> dataResult = new Utils.SingleDataResult<>();
        dataResult.data = new Image();

        JSONObject jsonImage = new JSONObject(String.valueOf(jsonObject.get("data")));

        if (jsonImage.has(Image.HEIGHT)) {
            dataResult.data.setHeight(jsonImage.getInt(Image.HEIGHT));
        }

        if (jsonImage.has(Image.WIDTH)) {
            dataResult.data.setWidth(jsonImage.getInt(Image.WIDTH));
        }

        if (jsonImage.has(Image.IS_SILHOUETTE)) {
            dataResult.data.setSilhouette(jsonImage.getBoolean(Image.IS_SILHOUETTE));
        }

        if (jsonImage.has(Image.URL)) {
            dataResult.data.setUrl(jsonImage.getString(Image.URL));
        }

        return dataResult;
    }

    private static List<Language> parseLanguages(JSONArray jLanguagesArray) throws JSONException {
        return null;
    }

    public static List<Album> parseAlbums(String rawResponse) throws JSONException {
        List<Album> albumList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(rawResponse);

        JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
        for (int i = 0; i < jsonArray.length(); i++) {
            Album album = parseAlbum(jsonArray.getJSONObject(i));
            albumList.add(album);
        }
        return albumList;
    }

    private static Album parseAlbum(JSONObject jsonAlbum) throws JSONException {
        Album album = new Album();

        if (jsonAlbum.has(Album.ID)) {
            album.setId(jsonAlbum.getString(Album.ID));
        }

        if (jsonAlbum.has(Album.NAME)) {
            album.setName(jsonAlbum.getString(Album.NAME));
        }


        if (jsonAlbum.has(Album.COUNT)) {
            album.setCount(jsonAlbum.getInt(Album.COUNT));
        }

        if (jsonAlbum.has(Album.CREATED_TIME)) {
            String stringDate = jsonAlbum.getString(Album.CREATED_TIME);
            album.setCreatedTime(Utils.formatDate(stringDate));
        }

        if (jsonAlbum.has(Album.DESCRIPTION)) {
            album.setDescription(jsonAlbum.getString(Album.DESCRIPTION));
        }

        if (jsonAlbum.has(Album.COVER_PHOTO)) {
            album.setCover(parseCoverPhoto(jsonAlbum.getJSONObject(Album.COVER_PHOTO)));
        }

        return album;
    }

    private static Cover parseCoverPhoto(JSONObject jsonCover) throws JSONException {
        Cover cover = new Cover();

        if (jsonCover.has(Cover.ID)) {
            cover.setId(jsonCover.getString(Cover.ID));
        }

        if (jsonCover.has(Cover.CREATED_TIME)) {
            String stringDate = jsonCover.getString(Cover.CREATED_TIME);
            cover.setCreatedTime(Utils.formatDate(stringDate));
        }

        return cover;
    }

    public static Photo parsePhoto(String rawResponse) throws JSONException {
        JSONObject jsonPhoto = new JSONObject(rawResponse);
        Photo photo = new Photo();

        if (jsonPhoto.has(Photo.ALBUM)) {
            Album album = parseAlbum(jsonPhoto.getJSONObject(Photo.ALBUM));
            photo.setAlbum(album);
        }

        if (jsonPhoto.has(Photo.IMAGES)) {
            List<Image> images = parseImages(jsonPhoto.getJSONArray(Photo.IMAGES));
            photo.setImages(images);
        }

        if (jsonPhoto.has(Photo.ID)) {
            photo.setId(jsonPhoto.getString(Photo.ID));
        }

        return photo;
    }

    private static List<Image> parseImages(JSONArray jsonArray) throws JSONException {
        List<Image> images = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Image image = parseImage(jsonArray.getJSONObject(i));
            images.add(image);
        }

        return images;
    }

    private static Image parseImage(JSONObject jsonImage) throws JSONException {
        Image image = new Image();

        if (jsonImage.has(Image.HEIGHT)) {
            image.setHeight(jsonImage.getInt(Image.HEIGHT));
        }

        if (jsonImage.has(Image.WIDTH)) {
            image.setWidth(jsonImage.getInt(Image.WIDTH));
        }

        if (jsonImage.has(Image.SOURCE)) {
            image.setSource(jsonImage.getString(Image.SOURCE));
        }

        return image;
    }

    public static List<Profile> parseFriends(String rawResponse) throws JSONException {
        JSONArray jsonArray = new JSONObject(rawResponse).getJSONArray("data");
        List<Profile> profiles = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Profile profile = parseProfile(jsonArray.getString(0));
            profiles.add(profile);
        }

        return profiles;
    }*/
}
