package fragments;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.ListViewAdapter;
import me.siegenthaler.spotify.webapi.android.example.R;
import widgets.ListViewItem;

public class ListViewFragment extends ListFragment {
    private List<ListViewItem> mItems;        // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();

//        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.aim), getString(R.string.aim), getString(R.string.aim_description)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.bebo_icon), getString(R.string.bebo), getString(R.string.bebo_description)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.youtube_icon), getString(R.string.youtube), getString(R.string.youtube_description)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.bebo_icon), getString(R.string.bebo), getString(R.string.bebo_description)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.youtube_icon), getString(R.string.youtube), getString(R.string.youtube_description)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.bebo_icon), getString(R.string.bebo), getString(R.string.bebo_description)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.youtube_icon), getString(R.string.youtube), getString(R.string.youtube_description)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.bebo_icon), getString(R.string.bebo), getString(R.string.bebo_description)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.youtube_icon), getString(R.string.youtube), getString(R.string.youtube_description)));

        // initialize and set the list adapter
        setListAdapter(new ListViewAdapter(getActivity(), mItems));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        ListViewItem item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();
    }
}