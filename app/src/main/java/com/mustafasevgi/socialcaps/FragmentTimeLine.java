package com.mustafasevgi.socialcaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

public class FragmentTimeLine extends BaseFragment implements PullRefreshLayout.OnRefreshListener, View.OnClickListener {

    private PullRefreshLayout pullRefreshLayout;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        pullRefreshLayout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        floatingActionButton.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> array = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            array.add("string " + i);
        }
        recyclerView.setAdapter(new TimeLineAdapter(array));


        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);

        pullRefreshLayout.setOnRefreshListener(this);
        floatingActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onRefresh() {
        pullRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullRefreshLayout.setRefreshing(false);
            }
        }, 4000);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), CreateCapsActivity.class);
        startActivityForResult(intent, 1);
    }
}
