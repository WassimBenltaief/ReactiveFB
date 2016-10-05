package com.beltaief.reactivefb.models;



public class Image {

    public static final String HEIGHT = "height";
    public static final String IS_SILHOUETTE = "is_silhouette";
    public static final String NULL = "null";
    public static final String SOURCE = "source";
    public static final String SRC = "src";
    public static final String URL = "url";
    public static final String WIDTH = "width";


    private Integer mHeight;


    private String mSource;


    private Integer mWidth;

    public boolean isSilhouette() {
        return isSilhouette;
    }

    public void setSilhouette(boolean silhouette) {
        isSilhouette = silhouette;
    }

    private boolean isSilhouette;

    private String mUrl;

    public Integer getHeight() {
        return mHeight;
    }

    public void setHeight(Integer height) {
        mHeight = height;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public Integer getWidth() {
        return mWidth;
    }

    public void setWidth(Integer width) {
        mWidth = width;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
