package com.elartix.OfficeViewer.datagrid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codegineer.datagrid.DataGrid;
import com.codegineer.datatable.DataTable;
import com.elartix.OfficeViewer.OfficeViewerMainActivity;
import com.elartix.OfficeViewer.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class OfficeViewerFragmentNavigationDrawerDataGridView extends Fragment {

    private static final String TAG = "OfficeViewerFragmentNavigationDrawerDataGridView";

    private DataGrid dg;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OfficeViewerFragmentNavigationDrawerDataGridView newInstance(int sectionNumber) {
        OfficeViewerFragmentNavigationDrawerDataGridView fragment = new OfficeViewerFragmentNavigationDrawerDataGridView();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public OfficeViewerFragmentNavigationDrawerDataGridView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.office_viewer_fragment_datagrid_view, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

        initDataGrid(container.getContext(), rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((OfficeViewerMainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void initDataGrid(Context context, View rootView) {

        DataTable dtDataSource = new DataTable();
        dtDataSource.addAllColumns(new String[]{"column_1", "column_2","column_3", "column_4"});

        DataTable.DataRow drRow;
        for(int i = 0; i < 100; i++){
            drRow = dtDataSource.newRow();
            for(int j = 0; j < dtDataSource.getColumnSize(); j++){
                drRow.set(j, String.valueOf(i)+" -  "+ String.valueOf(j) );
            }
            dtDataSource.add(drRow);
        }

        /**
         *  Prepare the DataGrid
         */
        dg = (DataGrid) rootView.findViewById(R.id.datagrid_test);
        if(dg != null) {
            dg.addColumnStyles(new DataGrid.ColumnStyle[]{
                    new DataGrid.ColumnStyle("Column 1", "column_1", 200),
                    new DataGrid.ColumnStyle("Column 2", "column_2", 200),
                    new DataGrid.ColumnStyle("Column 3", "column_3", 50),
                    new DataGrid.ColumnStyle("COlumn 4", "column_4", 150)
            });

            dg.setDataSource(dtDataSource);
            dg.refresh();
        }

    }

}
