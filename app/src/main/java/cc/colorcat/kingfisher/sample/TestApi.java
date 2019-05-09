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

import java.io.File;
import java.util.List;

import cc.colorcat.kingfisher.annotation.Api;
import cc.colorcat.kingfisher.annotation.BaseUrl;
import cc.colorcat.kingfisher.annotation.Client;
import cc.colorcat.kingfisher.annotation.Down;
import cc.colorcat.kingfisher.annotation.GET;
import cc.colorcat.kingfisher.annotation.Param;
import cc.colorcat.kingfisher.annotation.Path;
import cc.colorcat.kingfisher.annotation.Url;
import cc.colorcat.kingfisher.core.Call;
import cc.colorcat.kingfisher.core.DownPack;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
@Api
public interface TestApi {
    @Client("github")
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("api/teacher")
    Call<List<Course>> listCourses(@Param("type") int type, @Param("num") int num);

    @BaseUrl("https://dldir1.qq.com/weixin/android/weixin673android1360.apk")
    @GET
    Call<File> downloadWeChat(@Down DownPack pack);

    @GET
    Call<File> download(@Url String url, @Down File savePath);
}
