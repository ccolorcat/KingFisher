/*
 * Copyright 2018 cxx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.colorcat.kingfisher.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.List;

import cc.colorcat.kingfisher.core.Call;
import cc.colorcat.kingfisher.core.KingFisher;

public class MainActivity extends AppCompatActivity {
    KingFisher fisher = new KingFisher();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

//    private Call<List<String>> newCall(Type typeOfT) {
//        return fisher.<List<String>>newCall(typeOfT)
//                .addParameter("name", "value");
//    }
}
