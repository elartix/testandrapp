package com.elartix.OfficeViewer.navigationdrawer.model;

/**
 * Created by Lestat on 23.02.14.
 */
public class NavigationDrawerItem {

    private String title;
    private int icon;
    private String countString = "0";

    private boolean isCountStringVisible = false;

    public NavigationDrawerItem(){}

    public NavigationDrawerItem(String title, int icon, String countString, boolean isCountStringVisible) {
        this.title = title;
        this.icon = icon;
        this.countString = countString;
        this.isCountStringVisible = isCountStringVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCountString() {
        return countString;
    }

    public void setCountString(String countString) {
        this.countString = countString;
    }

    public boolean getCountStringVisible() {
        return isCountStringVisible;
    }

    public void setCountStringVisible(boolean isCountStringVisible) {
        this.isCountStringVisible = isCountStringVisible;
    }
}
