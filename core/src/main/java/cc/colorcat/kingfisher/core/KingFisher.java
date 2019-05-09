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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.Parser;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
public final class KingFisher {
    private static final String DEFAULT_CLIENT = KingFisher.class.getSimpleName() + ".default.client";
    private static KingFisher instance;

    public static <T> BaseCall<T> newCall(Type typeOfT) {
        return newCall(DEFAULT_CLIENT, typeOfT);
    }

    public static <T> BaseCall<T> newCall(String clientName, Type typeOfT) {
        if (instance == null) {
            throw new IllegalStateException("KingFisher uninitialized");
        }
        return instance.createCall(clientName, typeOfT);
    }

    private Map<String, NetBird> clients;
    private List<ParserFactory<?>> factories;

    private KingFisher(Builder builder) {
        this.clients = Utils.immutableMap(builder.clients);
        this.factories = Utils.immutableList(builder.factories);
    }

    private <T> BaseCall<T> createCall(String clientName, Type typeOfT) {
        NetBird client = clients.get(clientName);
        if (client == null) {
            throw new NullPointerException("unregister Client for name: " + clientName);
        }
        Parser<? extends T> parser = newParser(typeOfT);
        return new BaseCall<>(client, parser);
    }

    @SuppressWarnings("unchecked")
    private <T> Parser<? extends T> newParser(Type typeOfT) {
        for (int i = 0, size = factories.size(); i < size; ++i) {
            Parser<?> parser = factories.get(i).newParser(typeOfT);
            if (parser != null) {
                return (Parser<? extends T>) parser;
            }
        }
        throw new UnsupportedOperationException("no ParserFactory supported for " + typeOfT);
    }

    public static class Builder {
        private String baseUrl;
        private Map<String, NetBird> clients;
        private List<ParserFactory<?>> factories;
        private ParserFactory<String> stringFactory;

        public Builder() {
            clients = new HashMap<>(4);
            factories = new ArrayList<>(6);
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(NetBird client) {
            Utils.checkNotNull(client, "client == null");
            clients.put(KingFisher.DEFAULT_CLIENT, client);
            return this;
        }

        public Builder registerClient(String name, NetBird client) {
            Utils.checkNotNull(name, "name == null");
            Utils.checkNotNull(client, "client == null");
            clients.put(name, client);
            return this;
        }

        public Builder unregisterClient(String name) {
            clients.remove(name);
            return this;
        }

        public Builder addParserFactory(ParserFactory<?> factory) {
            if (!this.factories.contains(factory)) {
                this.factories.add(factory);
            }
            return this;
        }

        public Builder override(ParserFactory<String> factory) {
            this.stringFactory = factory;
            return this;
        }

        public synchronized void initialize() {
            if (!clients.containsKey(KingFisher.DEFAULT_CLIENT)) {
                clients.put(KingFisher.DEFAULT_CLIENT, new NetBird.Builder(baseUrl).build());
            }
            factories.add(0, stringFactory != null ? stringFactory : new StringParserFactory());
            factories.add(0, new FakeFileParserFactory());
            KingFisher.instance = new KingFisher(this);
        }
    }
}
