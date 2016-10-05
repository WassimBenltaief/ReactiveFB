package com.beltaief.reactivefb.models;

import java.util.List;

/**
 * Media content associated with a story or comment.
 *
 * // @see https://developers.facebook.com/docs/graph-api/reference/story_attachment
 */
public class Attachment {

    private static final String DATA = "data";
    private static final String DESCRIPTION = "description";
    private static final String DESCRIPTION_TAGS = "description_tags";
    private static final String MEDIA = "media";
    private static final String SUBATTACHMENTS = "subattachments";
    private static final String TARGET = "target";
    private static final String TITLE = "title";
    private static final String TYPE = "type";
    private static final String URL = "url";

    String mDescription;

    List<Profile> mDescriptionTags;

    StoryAttachmentMedia mMedia;

    List<Attachment> mSubAttachments;

    StoryAttachmentTarget mTarget;

    String mTitle;

    String mType;

    String mUrl;

    public String getDescription() {
        return mDescription;
    }

    public StoryAttachmentMedia getMedia() {
        return mMedia;
    }

    public List<Attachment> getSubAttachments() {
        return mSubAttachments;
    }

    public StoryAttachmentTarget getTarget() {
        return mTarget;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType;
    }

    public String getUrl() {
        return mUrl;
    }

    public static class StoryAttachmentMedia {

        private static final String IMAGE = "image";

        private Image mImage;

        public Image getImage() {
            return mImage;
        }

        public void setImage(Image mImage) {
            this.mImage = mImage;
        }
    }
    public static class StoryAttachmentTarget {

        private static final String ID = "id";
        private static final String URL = "url";

        String mId;

        String mUrl;

        public String getId() {
            return mId;
        }

        public String getUrl() {
            return mUrl;
        }
    }
}
