package com.commonsware.android.recyclerview.headerlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by gordbilyi on 3/14/17.
 */

public class IconicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String[] items = {"lorem", "ipsum", "dolor", "sit", "yo", "lorem", "ipsum",
            "dolor", "sit", "yo", "lorem", "ipsum", "dolor", "sit", "yo"};

    private Context mContext;

    public IconicAdapter(Context context) {
        this.mContext = context;
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
            ((FeaturedController) holder).bindModel((String) getItem(position));
        } else if (holder instanceof PodcastsController) {
            ((PodcastsController) holder).bindModel((String) getItem(position));
        } else {
            ((EpisodesController) holder).bindModel((String) getItem(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.length;
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
        return items[position];
    }
}
