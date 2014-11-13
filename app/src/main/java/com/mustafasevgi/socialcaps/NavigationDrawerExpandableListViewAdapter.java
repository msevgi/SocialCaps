package com.mustafasevgi.socialcaps;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.mustafasevgi.socialcaps.event_model.LeftGroupImageClickModel;
import com.mustafasevgi.socialcaps.provider.BusProvider;

import java.util.ArrayList;


public class NavigationDrawerExpandableListViewAdapter extends BaseExpandableListAdapter {

    private LayoutInflater layoutInflater;

    private static ViewHolderGroup viewHolderGroup;
    private static ViewHolderChild viewHolderChild;

    private ArrayList<Category> categoryList;

    public NavigationDrawerExpandableListViewAdapter(Activity activity) {
        layoutInflater = (LayoutInflater.from(activity));
        //TODO fill the arraylist
        categoryList = new ArrayList<Category>();
        Category category = new Category();
        category.setName("Kitap");
        ArrayList<String> childList = new ArrayList<String>();
        childList.add("Roman");
        childList.add("Hikaye");
        category.setChildList(childList);
        categoryList.add(category);
        Category category1 = new Category();
        category1.setName("Elektronik");
        ArrayList<String> childList1 = new ArrayList<String>();
        childList1.add("Televizyon");
        childList1.add("Bilgisayar");
        childList1.add("Akilli Telefon");
        category1.setChildList(childList1);
        categoryList.add(category1);

    }

    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoryList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_left_group_row, parent, false);
            viewHolderGroup = new ViewHolderGroup();
            viewHolderGroup.groupNameTextView = (TextView) convertView.findViewById(R.id.textview_left_group_name);
            viewHolderGroup.groupImageview = (ImageView) convertView.findViewById(R.id.imageview_left_plus);
            convertView.setTag(viewHolderGroup);
        } else {
            viewHolderGroup = (ViewHolderGroup) convertView.getTag();
        }

        final Category item = categoryList.get(groupPosition);
        if (item != null) {
            if (!isExpanded) {
                viewHolderGroup.groupImageview.setBackgroundResource(R.drawable.left_menu_plus);
            } else {
                viewHolderGroup.groupImageview.setBackgroundResource(R.drawable.left_menu_minus);
            }
            viewHolderGroup.groupImageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BusProvider.getInstance().post(new LeftGroupImageClickModel(groupPosition));
                }
            });
            viewHolderGroup.groupNameTextView.setText(item.getName());
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_left_child_row, parent, false);
            viewHolderChild = new ViewHolderChild();
            viewHolderChild.childTextView = (TextView) convertView.findViewById(R.id.textview_child_row);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
        Category item = categoryList.get(groupPosition);
        if (item != null) {
            viewHolderChild.childTextView.setText(item.getChildList().get(childPosition));
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolderGroup {
        private ImageView groupImageview;
        private TextView groupNameTextView;
    }

    private class ViewHolderChild {
        private TextView childTextView;
    }
}
