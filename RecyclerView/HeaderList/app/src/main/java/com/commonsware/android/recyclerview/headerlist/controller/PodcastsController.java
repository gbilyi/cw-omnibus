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

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.commonsware.android.recyclerview.headerlist.R;

import java.util.List;

public class PodcastsController extends RecyclerView.ViewHolder {
    TextView label = null;
    String template = null;

    public PodcastsController(View row) {
        super(row);

        label = (TextView) row.findViewById(R.id.label);

        template = label.getContext().getString(R.string.header_template);
    }

    public void bindModel(List<String> list) {
        String result = "";
        for (String i : list) {
            result += i + " ";
        }
        label.setText(result);
    }
}
