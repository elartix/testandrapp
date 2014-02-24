package com.elartix.OfficeViewer.tableview;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elartix.OfficeViewer.OfficeViewerMainActivity;
import com.elartix.OfficeViewer.R;
import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayGridView;

/**
 * A placeholder fragment containing a simple view.
 */
public class OfficeViewerFragmentNavigationDrawerTableView extends Fragment {

    private static final String TAG = "OfficeViewerFragmentNavigationDrawerTableView";

    private Cursor mImageCursor;
    private ImageThumbnailAdapter mAdapter;
    private TwoWayGridView mImageGrid;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OfficeViewerFragmentNavigationDrawerTableView newInstance(int sectionNumber) {
        OfficeViewerFragmentNavigationDrawerTableView fragment = new OfficeViewerFragmentNavigationDrawerTableView();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public OfficeViewerFragmentNavigationDrawerTableView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.office_viewer_fragment_table_view, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

        initGrid(container.getContext(), rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((OfficeViewerMainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void initGrid(Context context, View rootView) {
        /*mImageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ImageThumbnailAdapter.IMAGE_PROJECTION, null, null,
                MediaStore.Images.ImageColumns.DISPLAY_NAME);*/

        mImageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ImageThumbnailAdapter.IMAGE_PROJECTION, null, null,
                MediaStore.Images.ImageColumns.DISPLAY_NAME);

        mImageGrid = (TwoWayGridView) rootView.findViewById(R.id.gridview);
        mAdapter = new ImageThumbnailAdapter(context, mImageCursor);
        mImageGrid.setAdapter(mAdapter);

        mImageGrid.setOnItemClickListener(new TwoWayAdapterView.OnItemClickListener() {
            public void onItemClick(TwoWayAdapterView parent, View v, int position, long id) {
                Log.i(TAG, "showing image: " + mImageCursor.getString(ImageThumbnailAdapter.IMAGE_NAME_COLUMN));
                Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}
