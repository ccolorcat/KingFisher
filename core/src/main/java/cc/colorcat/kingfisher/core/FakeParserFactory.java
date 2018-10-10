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
import java.lang.reflect.Type;

import cc.colorcat.netbird.NetworkData;
import cc.colorcat.netbird.Parser;
import cc.colorcat.netbird.Response;

/**
 * Author: cxx
 * Date: 2018-10-10
 * GitHub: https://github.com/ccolorcat
 */
public class FakeParserFactory implements ParserFactory<File> {
    private Parser<File> parser = new FakeFileParser();

    @Override
    public Parser<? extends File> newParser(Type typeOfT) {
        if (typeOfT instanceof Class) {
            Class<?> clazz = (Class<?>) typeOfT;
            if (clazz.isAssignableFrom(File.class)) {
                return parser;
            }
        }
        return null;
    }

    static class FakeFileParser implements Parser<File> {
        @Override
        public NetworkData<? extends File> parse(Response response) throws IOException {
            return NetworkData.newFailure(response.responseCode(), response.responseMsg());
        }
    }
}
