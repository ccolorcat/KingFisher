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

package cc.colorcat.kingfisher.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import cc.colorcat.netbird.NetworkData;
import cc.colorcat.netbird.Parser;
import cc.colorcat.netbird.Response;
import cc.colorcat.netbird.StateIOException;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
class KingParser<T> implements Parser<T> {
    private static final Gson GSON = new GsonBuilder().create();

    static <T> KingParser<T> create(Type typeOfT) {
        if (typeOfT == null) {
            throw new RuntimeException("typeOfT == null");
        }
        return new KingParser<>(typeOfT);
    }

    private Type typeOfT;

    private KingParser(Type typeOfT) {
        this.typeOfT = typeOfT;
    }

    @Override
    public NetworkData<? extends T> parse(Response response) throws IOException {
        try {
            Reader reader = response.responseBody().reader();
            T data = GSON.fromJson(reader, typeOfT);
            if (data != null) {
                return NetworkData.newSuccess(data);
            }
            return NetworkData.newFailure(response.responseCode(), response.responseMsg());
        } catch (Exception e) {
            throw new StateIOException(response.responseCode(), response.responseMsg(), e);
        }
    }
}
