package fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

import adapters.ListViewAdapter;
import me.siegenthaler.spotify.webapi.android.example.R;
import widgets.ListViewItem;

public class AutoloadingListFragment extends ListFragment implements OnScrollListener {

    private final int AUTOLOAD_THRESHOLD = 4;
    private final int MAXIMUM_ITEMS = 60;
    private ListViewAdapter mAdapter;
    private View mFooterView;
    private Handler mHandler;
    private boolean mIsLoading = false;
    private boolean mMoreDataAvailable = true;
    private boolean mWasLoading = false;

    private Runnable mAddItemsRunnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.addMoreItems(10);
            mIsLoading = false;
        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Context context = getActivity();
        mHandler = new Handler();

        List<ListViewItem> mItems; // ListView items list

        // initialize the items list
        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();

        for (int  i=0; i< MAXIMUM_ITEMS/2; i++) {
            mItems.add(new ListViewItem(resources.getDrawable(R.drawable.bebo_icon), (i*2) +" "+ getString(R.string.bebo), getString(R.string.bebo_description)));
            mItems.add(new ListViewItem(resources.getDrawable(R.drawable.youtube_icon), (i*2+1) +" "+ getString(R.string.youtube), getString(R.string.youtube_description)));
        }

        // initialize the list adapter
        mAdapter = new ListViewAdapter(getActivity(), mItems);

        mFooterView = LayoutInflater.from(context).inflate(R.layout.loading_view, null);
        getListView().addFooterView(mFooterView, null, false);

        setListAdapter(mAdapter);
        getListView().setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!mIsLoading && mMoreDataAvailable) {
            if (totalItemCount >= MAXIMUM_ITEMS) {
                mMoreDataAvailable = false;
                getListView().removeFooterView(mFooterView);
            } else if (totalItemCount - AUTOLOAD_THRESHOLD <= firstVisibleItem + visibleItemCount) {
                mIsLoading = true;
                mHandler.postDelayed(mAddItemsRunnable, 1000);
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Ignore
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mWasLoading) {
            mWasLoading = false;
            mIsLoading = true;
            mHandler.postDelayed(mAddItemsRunnable, 1000);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mAddItemsRunnable);
        mWasLoading = mIsLoading;
        mIsLoading = false;
    }
}