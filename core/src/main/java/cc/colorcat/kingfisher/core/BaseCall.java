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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import cc.colorcat.netbird.FileParser;
import cc.colorcat.netbird.Method;
import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.Parser;
import cc.colorcat.netbird.Request;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
public final class BaseCall<T> implements Call<T> {
    private NetBird netBird;
    private Request.Builder builder;
    private Parser<? extends T> parser;
    private Object tag;

    BaseCall(NetBird netBird, Parser<? extends T> parser) {
        this.netBird = netBird;
        this.builder = new Request.Builder();
        this.parser = parser;
    }

    public void url(String url) {
        builder.url(url);
    }

    public void path(String path) {
        builder.path(path);
    }

    public void method(Method method) {
        builder.method(method);
    }

    public void method(String method) {
        builder.method(Method.valueOf(method));
    }

    public void parameter(String name, String value) {
        if (value != null) {
            builder.add(name, value);
        }
    }

    public void parameter(String name, int value) {
        builder.add(name, Integer.toString(value));
    }

    public void parameter(String name, long value) {
        builder.add(name, Long.toString(value));
    }

    public void parameter(String name, short value) {
        builder.add(name, Short.toString(value));
    }

    public void parameter(String name, double value) {
        builder.add(name, Double.toString(value));
    }

    public void parameter(String name, float value) {
        builder.add(name, Float.toString(value));
    }

    public void parameter(String name, byte value) {
        builder.add(name, Byte.toString(value));
    }

    public void parameter(String name, boolean value) {
        builder.add(name, Boolean.toString(value));
    }

    public void parameter(String name, char value) {
        builder.add(name, Character.toString(value));
    }

    public void parameter(String name, Object value) {
        if (value != null) {
            builder.add(name, value.toString());
        }
    }

    public void parameters(Map<String, String> parameters) {
        String name, value;
        for (Map.Entry<String, String> nameAndValue : parameters.entrySet()) {
            name = nameAndValue.getKey();
            value = nameAndValue.getValue();
            if (name != null && value != null) {
                builder.add(name, value);
            }
        }
    }

    public void header(String name, String value) {
        if (value != null) {
            builder.addHeader(name, value);
        }
    }

    public void headers(Map<String, String> headers) {
        String name, value;
        for (Map.Entry<String, String> nameAndValue : headers.entrySet()) {
            name = nameAndValue.getKey();
            value = nameAndValue.getValue();
            if (name != null && value != null) {
                builder.addHeader(name, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void download(DownPack pack) {
        parser = (Parser<? extends T>) FileParser.create(pack.savePath);
        builder.downloadListener(pack.listener);
    }

    @SuppressWarnings("unchecked")
    public void download(File savePath) {
        parser = (Parser<? extends T>) FileParser.create(savePath);
    }

    public void upload(String name, String contentType, File file) {
        builder.addFile(name, contentType, file);
    }

    public void upload(UpPack pack) {
        builder.addFile(pack.name, pack.contentType, pack.file, pack.listener);
    }

    public void batchUpload(List<UpPack> packs) {
        for (UpPack pack : packs) {
            builder.addFile(pack.name, pack.contentType, pack.file, pack.listener);
        }
    }

    @Override
    public Call<T> parser(Parser<? extends T> parser) {
        this.parser = parser;
        return this;
    }

    @Override
    public T execute() throws IOException {
        return netBird.newCall(request()).execute(parser);
    }

    @Override
    public void enqueue(Callback<T> callback) {
        netBird.newCall(request()).enqueue(parser, callback);
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

    private Request request() {
        Request request = builder.build();
        tag = request.tag();
        return request;
    }
}
