package com.elartix.OfficeViewer.navigationdrawer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elartix.OfficeViewer.R;
import com.elartix.OfficeViewer.navigationdrawer.model.NavigationDrawerItem;

import java.util.ArrayList;

/**
 * Created by Lestat on 23.02.14.
 */
public class NavigationDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavigationDrawerItem> navigationDrawerItems;

    public NavigationDrawerListAdapter(Context context, ArrayList<NavigationDrawerItem> navigationDrawerItems) {
        this.context = context;
        this.navigationDrawerItems = navigationDrawerItems;
    }

    @Override
    public int getCount() {
        return navigationDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navigationDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.office_viewer_fragment_navigation_drawer_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

        imgIcon.setImageResource(navigationDrawerItems.get(position).getIcon());
        txtTitle.setText(navigationDrawerItems.get(position).getTitle());

        // displaying count
        // check whether it set visible or not
        if (navigationDrawerItems.get(position).getCountStringVisible()) {
            txtCount.setText(navigationDrawerItems.get(position).getCountString());
        } else {
            // hide the counter view
            txtCount.setVisibility(View.GONE);
        }

        return convertView;
    }
}
