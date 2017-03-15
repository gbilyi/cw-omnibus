package com.commonsware.android.recyclerview.headerlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.commonsware.android.recyclerview.headerlist.controller.EpisodesController;
import com.commonsware.android.recyclerview.headerlist.controller.FeaturedController;
import com.commonsware.android.recyclerview.headerlist.controller.PodcastsController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gordbilyi on 3/14/17.
 */

public class IconicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String[] featured = {"lorem", "ipsum", "dolor", "sit", "yo", "lorem", "ipsum"};
    private static final String[] podcasts = {"lorem", "ipsum", "dolor", "sit", "yo", "lorem", "ipsum"};
    private static final String[] episodes = {"lorem", "ipsum", "dolor", "sit", "yo", "lorem", "ipsum"};

    private List<String> mFeatured = new ArrayList<>(Arrays.asList(featured));
    private List<String> mPodcats = new ArrayList<>(Arrays.asList(podcasts));
    private List<String> mEpisodes = new ArrayList<>(Arrays.asList(episodes));

    private List<List<String>> mData = new ArrayList<>();

    private Context mContext;

    public IconicAdapter(Context context) {
        this.mContext = context;

        mData.add(mFeatured);
        mData.add(mPodcats);
        for (int i = 0; i < 20; i++) {
            mData.add(mEpisodes);
        }

    }

    //TODO make immutable ?
    public List<List<String>> getData() {
        return mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case R.id.featured: {
                return (new FeaturedController(mContext, LayoutInflater.from(mContext)
                        .inflate(R.layout.featured, parent, false)));
            }
            case R.id.podcasts: {
                return (new PodcastsController(LayoutInflater.from(mContext)
                        .inflate(R.layout.podcasts, parent, false)));
            }
            case R.id.episodes: {
                return (new EpisodesController(LayoutInflater.from(mContext)
                        .inflate(R.layout.episodes, parent, false)));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FeaturedController) {
            ((FeaturedController) holder).bindModel((List<String>) getItem(position));
        } else if (holder instanceof PodcastsController) {
            ((PodcastsController) holder).bindModel((List<String>) getItem(position));
        } else {
            ((EpisodesController) holder).bindModel((List<String>) getItem(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.id.featured;
        } else if (position < 3) {
            return R.id.podcasts;
        } else if (position >= 3) {
            return R.id.episodes;
        }
        return -1;
    }

    private Object getItem(int position) {
        return mData.get(position);
    }
}
