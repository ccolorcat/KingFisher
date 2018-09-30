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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.Parser;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
public class KingFisher {
    private static KingFisher instance = new KingFisher();

    public static <T> BaseCall<T> call(Type typeOfT) {
        return instance.newCall(typeOfT);
    }

    private NetBird netBird = new NetBird.Builder("https://www.baidu.com/").build();

    private List<ParserFactory> parserFactories = new ArrayList<>();

    {
        parserFactories.add(new StringParserFactory());
    }

    private <T> BaseCall<T> newCall(Type typeOfT) {
        Parser<? extends T> parser = newParser(typeOfT);
        return new BaseCall<>(netBird, parser);
    }

    @SuppressWarnings("unchecked")
    private <T> Parser<? extends T> newParser(Type typeOfT) {
        for (int i = 0, size = parserFactories.size(); i < size; ++i) {
            Parser<?> parser = parserFactories.get(i).newParser(typeOfT);
            if (parser != null) {
                return (Parser<? extends T>) parser;
            }
        }
        throw new UnsupportedOperationException("no parser supported");
    }
}
