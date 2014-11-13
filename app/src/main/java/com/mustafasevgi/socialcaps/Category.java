package com.mustafasevgi.socialcaps;

import java.util.ArrayList;


public class Category {
    public Category() {
    }

    public Category(String name, ArrayList<String> childList) {
        this.name = name;
        this.childList = childList;
    }

    private String name;


    private ArrayList<String> childList;

    public ArrayList<String> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<String> childList) {
        this.childList = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", childList=" + childList +
                '}';
    }
}
