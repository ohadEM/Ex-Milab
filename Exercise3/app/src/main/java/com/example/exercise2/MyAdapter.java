package com.example.exercise2;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ohad on 11/14/2017.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private String[] mDataSet;

    public MyAdapter(String[] data) {
        mDataSet = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public RecyclerView.ViewHolder(TextView view) {
            super(view);
            mTextView = view;
        }
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = new TextView(parent.getContext());
        ViewHolder viewHolder =new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBlindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).mTextView.setText(mDataSet[position]);
    }

    @Override
    public  int getItemCount() {
        return mDataSet.length;
    }
}
