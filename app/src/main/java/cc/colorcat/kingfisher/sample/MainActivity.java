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

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import cc.colorcat.kingfisher.core.SimpleCallback;
import cc.colorcat.netbird.StateIOException;

public class MainActivity extends Activity {
    private final TestApi mService = new TestApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test_github).setOnClickListener(mClick);
        findViewById(R.id.btn_test_mooc).setOnClickListener(mClick);
    }

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_test_github:
                    testGithub();
                    break;
                case R.id.btn_test_mooc:
                    testMooc();
                    break;
                default:
                    break;
            }
        }
    };

    private void testMooc() {
        mService.listCourses(4, 30).enqueue(new SimpleCallback<List<Course>>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.v("Mooc", "onStart");
            }

            @Override
            public void onSuccess(List<Course> result) {
                super.onSuccess(result);
                Log.d("Mooc", "onSuccess, result=" + result);
            }


            @Override
            public void onFailure(StateIOException cause) {
                super.onFailure(cause);
                Log.e("Mooc", "onFailure " + cause);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.v("Mooc", "onFinish");
            }
        });
    }

    private void testGithub() {
        mService.listRepos("ccolorcat").enqueue(new SimpleCallback<List<Repo>>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.v("Github", "onStart");
            }

            @Override
            public void onSuccess(List<Repo> result) {
                super.onSuccess(result);
                Log.d("Github", "onSuccess, result:\n" + result);
            }

            @Override
            public void onFailure(StateIOException cause) {
                super.onFailure(cause);
                Log.e("Github", "onFailure " + cause);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.v("Github", "onFinish");
            }
        });
    }
}
