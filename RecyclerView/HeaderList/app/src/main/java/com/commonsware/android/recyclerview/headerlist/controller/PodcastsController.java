/***
 Copyright (c) 2015 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain	a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS,	WITHOUT	WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _The Busy Coder's Guide to Android Development_
 https://commonsware.com/Android
 */

package com.commonsware.android.recyclerview.headerlist.controller;

import android.content.Context;
import android.graphics.Color;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.commonsware.android.recyclerview.headerlist.R;

import java.util.ArrayList;
import java.util.List;

public class PodcastsController extends RecyclerView.ViewHolder {

    private HorizontalGridView mHorizontalGridView;
    private Context mContext;

    public PodcastsController(Context context, View row) {
        super(row);
        mContext = context;
        mHorizontalGridView = (HorizontalGridView) row.findViewById(R.id.gridview);
        HorizontalGridView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mHorizontalGridView.setLayoutManager(layoutManager);
        mHorizontalGridView.setHasFixedSize(true);
        mHorizontalGridView.setNumRows(1);
//        mHorizontalGridView.setFadingRightEdge(true);
//        mHorizontalGridView.setFadingLeftEdgeLength(50);
//        mHorizontalGridView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
//        mHorizontalGridView.setBackgroundColor(Color.GREEN);
//        mHorizontalGridView.setDrawingCacheEnabled(true);
        //mHorizontalGridView.setWindowAlignment(HorizontalGridView.);
       // mHorizontalGridView.setWindowAlignment(HorizontalGridView.WINDOW_ALIGN_NO_EDGE);
       // mHorizontalGridView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_YES);
    }

    public void bindModel(List<String> podcasts) {
        mHorizontalGridView.setAdapter(new ChannelHGridViewAdapter(mContext, podcasts));
    }

    class ChannelHGridViewAdapter extends RecyclerView.Adapter {

        public static final String MESSAGE_GRIDE_ITEM_ON_CLICK = "ChannelHGridViewAdapter.MESSAGE_GRIDE_ITEM_ON_CLICK";
        public static final String KEY_PODCAST_ID = "podcast_id";
        private static final String TAG = "ChannelHGridViewAdapter";

        private Context mContext;
        private List<String> mPodcasts;
        private LayoutInflater mInflator;

        public ChannelHGridViewAdapter(Context context, List<String> podcasts) {
            mPodcasts = podcasts;
            mContext = context;
            mInflator = LayoutInflater.from(context);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflator.inflate(R.layout.item_podcast_cover, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder baseHolder, int position) {
            ViewHolder holder = (ViewHolder) baseHolder;
            ImageView img = holder.imgCoverArt;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            Glide.with(mContext).load(mPodcasts.get(position)).into(img);
        }

        @Override
        public int getItemCount() {
            return mPodcasts.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imgCoverArt;

            public ViewHolder(View view) {
                super(view);
                imgCoverArt = (ImageView) view.findViewById(R.id.img_cover);
            }
        }
    }

}
