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

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

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
}
