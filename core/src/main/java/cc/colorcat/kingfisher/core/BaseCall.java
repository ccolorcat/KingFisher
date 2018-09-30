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

package cc.colorcat.kingfisher.core;

import java.io.IOException;
import java.util.Map;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.Parser;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
public final class BaseCall<T> implements Call<T> {
    private NetBird netBird;
    private MRequest.Builder<T> builder;
    private Object tag;

    BaseCall(NetBird netBird, Parser<? extends T> parser) {
        this.netBird = netBird;
        this.builder = new MRequest.Builder<>(parser);
    }

    public BaseCall<T> addParameter(String name, String value) {
        builder.add(name, value);
        return this;
    }

    public BaseCall<T> addParameters(Map<String, String> parameters) {
        for (Map.Entry<String, String> nameAndValue : parameters.entrySet()) {
            builder.add(nameAndValue.getKey(), nameAndValue.getValue());
        }
        return this;
    }

    public BaseCall<T> addHeader(String name, String value) {
        builder.addHeader(name, value);
        return this;
    }

    public BaseCall<T> addHeaders(Map<String, String> headers) {
        for (Map.Entry<String, String> nameAndValue : headers.entrySet()) {
            builder.add(nameAndValue.getKey(), nameAndValue.getValue());
        }
        return this;
    }

    @Override
    public T execute() throws IOException {
        return netBird.execute(request());
    }

    @Override
    public void enqueue(Callback<T> callback) {
        builder.listener(callback);
        netBird.send(request());
    }

    @Override
    public void cancelIfWaiting() {
        if (tag != null) {
            netBird.cancelWaiting(tag);
        }
    }

    @Override
    public void forceCancel() {
        if (tag != null) {
            netBird.cancelAll(tag);
        }
    }

    private MRequest<T> request() {
        MRequest<T> request = builder.build();
        tag = request.tag();
        return request;
    }
}
