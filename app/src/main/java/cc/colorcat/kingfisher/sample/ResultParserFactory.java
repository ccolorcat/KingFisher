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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import cc.colorcat.kingfisher.core.ParserFactory;
import cc.colorcat.netbird.NetworkData;
import cc.colorcat.netbird.Parser;
import cc.colorcat.netbird.Response;
import cc.colorcat.netbird.StateIOException;

/**
 * Author: cxx
 * Date: 2018-10-09
 * GitHub: https://github.com/ccolorcat
 */
public class ResultParserFactory<T> implements ParserFactory<T> {
    private Gson gson;

    public ResultParserFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Parser<? extends T> newParser(Type typeOfT) {
        return new ResultParser<>(gson, typeOfT);
    }

    static class ResultParser<T> implements Parser<T> {
        private Gson gson;
        private Type typeOfT;

        private ResultParser(Gson gson, Type typeOfT) {
            this.gson = gson;
            this.typeOfT = typeOfT;
        }

        @Override
        public NetworkData<? extends T> parse(Response response) throws IOException {
            try {
                // fastjson
//                String content = response.responseBody().string();
//                ParameterizedType pt = new ParameterizedTypeImpl(new Type[]{typeOfT}, null, Result.class);
//                Result<T> result = JSON.parseObject(content, pt);

                // jackson
//                Reader reader = response.responseBody().reader();
//                TypeFactory factory = TypeFactory.defaultInstance();
//                JavaType innerType = factory.constructType(typeOfT);
//                JavaType outerType = factory.constructParametricType(Result.class, innerType);
//                Result<T> result = MAPPER.readValue(reader, outerType);

                // gson
                Type type = TypeToken.getParameterized(Result.class, typeOfT).getType();
                Result<T> result = gson.fromJson(response.responseBody().reader(), type);
                if (result != null) {
                    T data = result.getData();
                    if (result.getStatus() == Result.STATUS_OK && data != null) {
                        return NetworkData.newSuccess(data);
                    } else {
                        return NetworkData.newFailure(result.getStatus(), result.getMsg());
                    }
                }
                return NetworkData.newFailure(response.responseCode(), response.responseMsg());
            } catch (Exception e) {
                throw new StateIOException(response.responseCode(), response.responseMsg(), e);
            }
        }
    }
}
