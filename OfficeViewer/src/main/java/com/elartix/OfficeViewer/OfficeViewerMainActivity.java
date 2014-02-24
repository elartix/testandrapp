package com.elartix.OfficeViewer;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.codegineer.datagrid.DataGrid;
import com.codegineer.datatable.DataTable;
import com.elartix.OfficeViewer.datagrid.OfficeViewerFragmentNavigationDrawerDataGridView;
import com.elartix.OfficeViewer.navigationdrawer.OfficeViewerFragmentNavigationDrawer;
import com.elartix.OfficeViewer.tableview.OfficeViewerFragmentNavigationDrawerTableView;

public class OfficeViewerMainActivity extends ActionBarActivity implements OfficeViewerFragmentNavigationDrawer.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private OfficeViewerFragmentNavigationDrawer mOfficeViewerFragmentNavigationDrawer;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.office_viewer_main_activity);

        mOfficeViewerFragmentNavigationDrawer = (OfficeViewerFragmentNavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mOfficeViewerFragmentNavigationDrawer.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }



    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position)
        {
            case 0:
            case 1:
            case 2:
            case 3:
                fragmentManager.beginTransaction().replace(R.id.container, OfficeViewerFragmentNavigationDrawerHome.newInstance(position)).commit();
                break;
            case 4:
                fragmentManager.beginTransaction().replace(R.id.container, OfficeViewerFragmentNavigationDrawerTableView.newInstance(position)).commit();
                break;
            case 5:
                fragmentManager.beginTransaction().replace(R.id.container, OfficeViewerFragmentNavigationDrawerDataGridView.newInstance(position)).commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        String[] navigationDrawerMenuTitles = getResources().getStringArray(R.array.navigation_drawer_items);
        if (navigationDrawerMenuTitles.length > 0) {
            mTitle = navigationDrawerMenuTitles[number];
        }

        /*switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_table_view);
        }*/
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mOfficeViewerFragmentNavigationDrawer.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.office_viewer_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
