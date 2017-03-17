/***
 Copyright (c) 2008-2015 CommonsWare, LLC
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

package com.commonsware.android.recyclerview.headerlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.commonsware.android.recyclerview.headerlist.controller.PodcastsController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends RecyclerViewActivity {

    private IconicAdapter mIconicAdapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        setLayoutManager(new LinearLayoutManager(this));
        mIconicAdapter = new IconicAdapter(getBaseContext());
        setAdapter(mIconicAdapter);

//        List<String> mPodcats = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            mPodcats.add(String.format("http://lorempixel.com/400/200/sports/%d/", i));
//        }
//
//        HorizontalGridView horizontalGridView = (HorizontalGridView) findViewById(R.id.gridview);
//        horizontalGridView.setAdapter(new ChannelHGridViewAdapter(this, mPodcats));
    }

    public void onClick(View view) {
        List<List<String>> data = mIconicAdapter.getData();
        List<String> featured = data.get(0);
        featured.add(0, "blablah!");

        String[] newArr = new String[]{"bla", "blah", "yo!"};
        List<String> newItem = new ArrayList<>(Arrays.asList(newArr));
        data.add(0, newItem);

        mIconicAdapter.notifyDataSetChanged();
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
        public ChannelHGridViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflator.inflate(R.layout.item_podcast_cover, parent, false);
            return new ChannelHGridViewAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder baseHolder, int position) {
            ChannelHGridViewAdapter.ViewHolder holder = (ChannelHGridViewAdapter.ViewHolder) baseHolder;
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
