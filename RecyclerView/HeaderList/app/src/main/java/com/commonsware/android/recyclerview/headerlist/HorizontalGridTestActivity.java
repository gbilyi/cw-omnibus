package com.commonsware.android.recyclerview.headerlist;

/**
 * Created by gordbilyi on 3/16/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.OnChildSelectedListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class HorizontalGridTestActivity extends Activity {
    private static final String TAG = "zzz";
    private static final int NUM_ITEMS = 5;
    private static final boolean STAGGERED = true;
    private HorizontalGridView mHorizontalGridView;
    private LayoutInflater mInflator;

    private View createView() {
        mInflator = LayoutInflater.from(this);
        View view = getLayoutInflater().inflate(R.layout.podcasts, null, false);
        mHorizontalGridView = (HorizontalGridView) view.findViewById(R.id.gridview);
        mHorizontalGridView.setWindowAlignment(HorizontalGridView.WINDOW_ALIGN_BOTH_EDGE);
        mHorizontalGridView.setWindowAlignmentOffsetPercent(35);
        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = createView();
        mHorizontalGridView.setAdapter(new MyAdapter());
        setContentView(view);
    }

    private OnFocusChangeListener mItemFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                v.setBackgroundColor(Color.YELLOW);
            } else {
                v.setBackgroundColor(Color.LTGRAY);
            }
        }
    };
    private OnClickListener mItemClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            mHorizontalGridView.getAdapter().notifyDataSetChanged();
        }
    };

    class MyAdapter extends RecyclerView.Adapter {

        MyAdapter() {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflator.inflate(R.layout.item_podcast_cover, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder baseHolder, int position) {
            ViewHolder holder = (ViewHolder) baseHolder;
            ImageView img = holder.imgCoverArt;
            Glide.with(holder.itemView.getContext())
                    .load(String.format("http://lorempixel.com/400/200/sports/%d/", position + 5))
                    .into(img);

        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgCoverArt;

        public ViewHolder(View v) {
            super(v);
            imgCoverArt = (ImageView) v.findViewById(R.id.img_cover);
        }
    }
}
