package image;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageSize implements Parcelable {

    public enum AspectRatio {
        TwoByThree("2:3"),
        SixteenByNine("16:9");

        private final String name;

        private AspectRatio(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        @Override
        public String toString() {
            return name;
        }

        public static AspectRatio find(String name) {
            for (AspectRatio ratio : AspectRatio.values()) {
                if (ratio.toString().contains(name)) {
                    return ratio;
                }
            }

            return null;
        }
    }

    // Mandatory
    protected final int mWidth;
    protected final int mHeight;
    protected final AspectRatio mAspectRatio;

    public ImageSize(int width, int height, AspectRatio aspectRatio) {
        mWidth = width;
        mHeight = height;
        mAspectRatio = aspectRatio;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public AspectRatio getAspectRatio() {
        return mAspectRatio;
    }

    /**
     * Works out what width pixel needs to be set based on the aspect ratio of the image and the height available
     *
     * @param height available
     * @return width required, given height to maintain aspect ratio
     */
    public int getWidthAndMaintainAspectRatio(int height) {
        return Math.round(((float)height / (float)mHeight) * mWidth);
    }

    /**
     * Works out what height pixel size needs to be set based on the aspect ratio of the image and the desired width
     *
     * @param width available
     * @return height required, given width to maintain aspect ratio
     */
    public int getHeightAndMaintainAspectRatio(int width) {
        return Math.round(((float)width / (float)mWidth) * mHeight);
    }

    public static final Creator<ImageSize> CREATOR = new Creator<ImageSize>() {

        @Override
        public ImageSize createFromParcel(Parcel in) {
            return new ImageSize(in);
        }

        @Override
        public ImageSize[] newArray(int size) {
            return new ImageSize[size];
        }
    };

    protected ImageSize(Parcel in) {
        mWidth = in.readInt();
        mHeight = in.readInt();
        mAspectRatio = AspectRatio.valueOf(in.readString());
    }

    public ImageSize(ImageSize size) {
        mWidth = size.mWidth;
        mHeight = size.mHeight;
        mAspectRatio = size.mAspectRatio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mWidth);
        dest.writeInt(mHeight);
        dest.writeString(mAspectRatio.name());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mAspectRatio == null) ? 0 : mAspectRatio.hashCode());
        result = prime * result + mHeight;
        result = prime * result + mWidth;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ImageSize)) {
            return false;
        }
        ImageSize other = (ImageSize)obj;
        if (mAspectRatio != other.mAspectRatio) {
            return false;
        }
        if (mHeight != other.mHeight) {
            return false;
        }
        if (mWidth != other.mWidth) {
            return false;
        }
        return true;
    }

}
