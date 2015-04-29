package image;

import android.os.Parcel;

public class ImageMetaData extends ImageSize {

    protected String mUri;

    public ImageMetaData(int width, int height, AspectRatio aspectRatio) {
        super(width, height, aspectRatio);
    }

    public ImageMetaData(ImageSize size) {
        super(size);
    }

    public void setUri(String url) {
        mUri = url;
    }

    public String getUri() {
        return mUri;
    }

    public static final Creator<ImageMetaData> CREATOR = new Creator<ImageMetaData>() {

        @Override
        public ImageMetaData createFromParcel(Parcel in) {
            return new ImageMetaData(in);
        }

        @Override
        public ImageMetaData[] newArray(int size) {
            return new ImageMetaData[size];
        }
    };

    protected ImageMetaData(Parcel in) {
        super(in);
        mUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mUri);
    }
}
