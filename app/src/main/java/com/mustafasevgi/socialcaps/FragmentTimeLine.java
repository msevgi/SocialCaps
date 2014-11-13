package com.mustafasevgi.socialcaps;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by mustafasevgi on 10/11/14.
 */
public class FragmentTimeLine extends BaseFragment {
    PullRefreshLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);
//        ListView listView=(ListView)view.findViewById(R.id.listview_time_line);
//        ArrayList<String> list=new ArrayList<String>();
//        for (int i = 0; i <10 ; i++) {
//            list.add("a");
//        }
////        TimeLineAdapter adapter=new TimeLineAdapter(getActivity(),0,list);
////        listView.setAdapter(adapter);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String[] array = new String[50];
        for (int i = 0; i < array.length; i++) {
            array[i] = "string " + i;
        }
        recyclerView.setAdapter(new ArrayAdapter(getActivity().getApplicationContext(), array));

        layout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        layout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 4000);
            }
        });
        return view;
    }

    static class ArrayAdapter extends RecyclerView.Adapter<ViewHolder> {

        private String[] mArray;
        private Context mContext;

        public ArrayAdapter(Context context, String[] array) {
            mContext = context;
            mArray = array;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.mTextView.setText(mArray[i]);
        }

        @Override
        public int getItemCount() {
            return mArray.length;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }

}
