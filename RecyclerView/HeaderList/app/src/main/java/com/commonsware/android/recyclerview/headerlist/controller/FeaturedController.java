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

//TODO: change simple array list to a more complex data structure like list of different types of lists,
// get data for a view pager from the new list

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.commonsware.android.recyclerview.headerlist.R;

import java.util.List;

public class FeaturedController extends RecyclerView.ViewHolder {


    ViewPager mPager;
    Context mContext;

    public FeaturedController(Context context, View row) {
        super(row);

        mContext = context;
        mPager = (ViewPager) row.findViewById(R.id.pager);

    }

    public void bindModel(List<String> list) {
        //label.setText(headerIndex);
        mPager.setAdapter(new MyAdapter(list));
    }


    private class MyAdapter extends PagerAdapter {

        private List<String> mList;

        public MyAdapter(List<String> list) {
            mList = list;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_my, container, false);
            Button button = (Button) view.findViewById(R.id.button);
            button.setText(mList.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
