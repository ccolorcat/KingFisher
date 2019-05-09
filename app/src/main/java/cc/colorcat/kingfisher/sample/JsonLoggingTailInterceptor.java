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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.nio.charset.Charset;

import cc.colorcat.netbird.logging.LoggingTailInterceptor;

/**
 * Author: cxx
 * Date: 2018-10-09
 * GitHub: https://github.com/ccolorcat
 */
public class JsonLoggingTailInterceptor extends LoggingTailInterceptor {
    private Gson mGson = new GsonBuilder().setPrettyPrinting().create();
    private JsonParser mParser = new JsonParser();

    public JsonLoggingTailInterceptor() {
    }

    public JsonLoggingTailInterceptor(boolean deUnicode) {
        super(deUnicode);
    }

    public JsonLoggingTailInterceptor(Charset charsetIfAbsent, boolean deUnicode) {
        super(charsetIfAbsent, deUnicode);
    }

    @Override
    protected String formatResponse(String content, String contentType) {
        return '\n' + mGson.toJson(mParser.parse(content));
    }
}
