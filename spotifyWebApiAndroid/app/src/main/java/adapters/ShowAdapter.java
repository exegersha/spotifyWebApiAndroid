package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.ui.NetworkImageView;
import com.google.android.gms.internal.id;
//import com.android.volley.toolbox.NetworkImageView;
//import com.bskyb.nowtv.beta.R;
//import com.bskyb.nowtv.data.Show;
//import com.bskyb.nowtv.data.image.ChannelLogo.ImageType;
//import com.bskyb.nowtv.data.image.ImageMetaData;
//import com.bskyb.nowtv.util.Constants;
//import com.bskyb.nowtv.widgets.Placeholder;


import java.util.List;

import image.ImageMetaData;
//import me.siegenthaler.spotify.webapi.android.R;


import me.siegenthaler.spotify.webapi.android.example.R;
import me.siegenthaler.spotify.webapi.android.model.Image;
import me.siegenthaler.spotify.webapi.android.model.Track;
import util.Constants;
import widgets.Placeholder;

/**
 * Generic Adapter for Shows (Entertainment). Specific itemLayout is passed in via the constructor.
 */

public class ShowAdapter extends BaseAdapter {

    private final String TAG = ShowAdapter.class.getSimpleName();

    private List<Track> mTrackList;
    private final ImageLoader mImageLoader;
    private int mItemWidth = 0;
    private final int mItemLayoutId;

    private class ViewHolder {

        NetworkImageView trackImage;
        TextView title;

        public ViewHolder(View view) {
            trackImage = (NetworkImageView)view.findViewById(R.id.image);
//            title = (TextView)view.findViewById(id.title);
        }
    }

    public ShowAdapter(Context context, ImageLoader imageLoader, List<Track> collectionList, int itemLayoutId) {

        mTrackList = collectionList;
        mImageLoader = imageLoader;
        mItemLayoutId = itemLayoutId;
    }

    public void setCollectionList(List<Track> recList) {
        Log.d(TAG, "setCollectionList,count:" + recList.size());
        mTrackList = recList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTrackList.size();
    }

    @Override
    public Track getItem(int position) {
        return mTrackList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Track track = mTrackList.get(position);

        ViewHolder vh;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mItemLayoutId, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder)convertView.getTag();
        }

        if ((position == 0) && (mItemWidth == 0)) {
            mItemWidth = convertView.getMeasuredWidth();
        }

        int widthSpec = MeasureSpec.makeMeasureSpec(mItemWidth, MeasureSpec.EXACTLY);
        int heightSpec = MeasureSpec.makeMeasureSpec(convertView.getMeasuredHeight(), MeasureSpec.UNSPECIFIED);

        convertView.measure(widthSpec, heightSpec);

        vh.title.setText(track.getName());

        // main image
        if (vh.trackImage.getMeasuredWidth() > 0) {
            setImage(vh.trackImage, track);
        }
        Log.d(TAG, "getView:" + position + " , w:" + vh.trackImage.getMeasuredWidth());
        return convertView;
    }

    private void setImage(final NetworkImageView imageView, final Track track) {

        final int measuredWidth = imageView.getMeasuredWidth();
        final Image imageMetaData = track.getAlbum().getImage(0); //getImageForWidth(measuredWidth);

        final LayoutParams lp = imageView.getLayoutParams();
        lp.width = measuredWidth;

        if (imageMetaData != null) {
            lp.height = imageMetaData.getHeight();
        } else {
            lp.height = Math.round(measuredWidth * Constants.IMAGE_16X9_RATIO_INVERSE);
        }

        imageView.setLayoutParams(lp);

        // The NetworkImageView needs to have the measured height and width set
        final int widthMeasureSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY);
        final int heightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
        imageView.measure(widthMeasureSpec, heightMeasureSpec);

//        Placeholder.set(imageView, R.drawable.placeholder_small);
        if (imageMetaData != null) {
            imageView.setImageUrl(imageMetaData.getLink(), mImageLoader);

            Log.d(TAG, "Image width:" + measuredWidth + "  , url:" + imageMetaData.getLink());
        } else {
            imageView.setImageUrl(null, mImageLoader);
        }
    }

}
