package com.example.exercise2;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class activity_Lannister extends Fragment {


    public activity_Lannister() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.lanniserNames);
        // Setup layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
