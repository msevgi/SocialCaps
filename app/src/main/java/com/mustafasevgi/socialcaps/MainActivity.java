package com.mustafasevgi.socialcaps;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.mustafasevgi.socialcaps.event_model.LeftGroupImageClickModel;
import com.squareup.otto.Subscribe;


public class MainActivity extends BaseActionBarActivity implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {
    // left menudeki itemlarin tutuldugu liste
    // left menudeki itemlari gosteren listview
    private ExpandableListView drawerListView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Fragment fragment = null;
    private LinearLayout linearLayout;
    private static int previousPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get list items from strings.xml
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        // get ListView defined in activity_main.xml
        drawerListView = (ExpandableListView) findViewById(R.id.left_drawer_list);
        drawerListView.setGroupIndicator(null);
        // App Icon
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationDrawerExpandableListViewAdapter adapter = new NavigationDrawerExpandableListViewAdapter(this);
//        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(this, R.layout.navigation_drawer_item, drawerListViewItems);
        // Set the adapter for the list view
        drawerListView.setAdapter(adapter);
        drawerListView.setOnGroupClickListener(this);
        drawerListView.setOnChildClickListener(this);
        fragment = new FragmentTimeLine();
        setDrawerLayout(0);

//        // left menudeki itemlarin click yakalar.
//        drawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//                // TODO Auto-generated method stub
//
//                switch (position) {
//                    case 0:
//                        fragment = new FragmentAktuel();
//                        break;
////                    case 1:
////                        fragment = new FragmentEskiAktuel();
////                        break;
//
//                    default:
//                        break;
//                }
//                setDrawerLayout(position);
//            }
//
//        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
                drawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.navigation_drawer_open, /* "open drawer" description */
                R.string.navigation_drawer_close /* "close drawer" description */
        );

        // Set actionBarDrawerToggle as the DrawerListener
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // just styling option add shadow the right edge of the drawer
        // drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setDrawerLayout(int position) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            // update selected item and title, then close the drawer
            drawerListView.setItemChecked(position, true);
            drawerListView.setSelection(position);
            setTitle("Kitap");
        }
        drawerLayout.closeDrawer(linearLayout);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // call ActionBarDrawerToggle.onOptionsItemSelected(), if it returns true
        // then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // swipe edilince app icon yanndaki simgenin degismesini sagliyor
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//        selectItem(groupPosition);
        switch (groupPosition) {
            case 0:
                fragment = new FragmentTimeLine();
                break;
            default:
                break;
        }
        setDrawerLayout(groupPosition);
        return true;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        return false;
    }

    @Subscribe
    public void onGroupIconClick(LeftGroupImageClickModel leftGroupImageClickModel) {
        int position = leftGroupImageClickModel.getGroupPosition();
        if (!drawerListView.isGroupExpanded(position)) {
            drawerListView.collapseGroup(previousPosition);
            drawerListView.expandGroup(position, true);
        } else
            drawerListView.collapseGroup(position);
        previousPosition = position;
    }
}