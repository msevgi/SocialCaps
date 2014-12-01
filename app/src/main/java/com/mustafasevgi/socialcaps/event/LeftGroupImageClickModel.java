package com.mustafasevgi.socialcaps.event;

/**
 * Created by mustafasevgi on 09/11/14.
 */
public class LeftGroupImageClickModel {
    private int groupPosition;

    public LeftGroupImageClickModel(int groupPosition) {
        this.groupPosition = groupPosition;
    }

    public int getGroupPosition() {
        return groupPosition;
    }


    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }


}
