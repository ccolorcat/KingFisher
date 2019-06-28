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
    private static final String DEFAULT_CLIENT = KingFisher.class.getSimpleName() + ".default.defaultClient";
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

    private Map<String, Client> clients;

    private KingFisher(Builder builder) {
        this.clients = Utils.immutableMap(builder.clients);
    }

    private <T> BaseCall<T> createCall(String clientName, Type typeOfT) {
        Client client = clients.get(clientName);
        if (client == null) {
            throw new NullPointerException("No client registered with " + clientName);
        }
        Parser<? extends T> parser = newParser(client, typeOfT);
        return new BaseCall<>(client.bird, parser);
    }

    @SuppressWarnings("unchecked")
    private <T> Parser<? extends T> newParser(Client client, Type typeOfT) {
        List<ParserFactory<?>> factories = client.factories;
        for (int i = 0, size = factories.size(); i < size; ++i) {
            Parser<?> parser = factories.get(i).newParser(typeOfT);
            if (parser != null) {
                return (Parser<? extends T>) parser;
            }
        }
        throw new UnsupportedOperationException("no ParserFactory support " + typeOfT);
    }

    public static class Builder {
        private String baseUrl;
        private Map<String, Client> clients;
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

        public Builder defaultClient(NetBird bird) {
            return registerClient(KingFisher.DEFAULT_CLIENT, bird);
        }

        public Builder addParserFactory(ParserFactory<?> factory) {
            Utils.checkNotNull(factory, "factory == null");
            if (!factories.contains(factory)) {
                factories.add(factory);
            }
            return this;
        }

        public Builder override(ParserFactory<String> factory) {
            this.stringFactory = factory;
            return this;
        }

        public Builder registerClient(String clientName, NetBird bird) {
            Utils.checkNotNull(clientName, "clientName == null");
            Utils.checkNotNull(bird, "bird == null");
            clients.put(clientName, new Client(bird));
            return this;
        }

        public Builder registerParserFactory(String clientName, ParserFactory<?> factory) {
            Utils.checkNotNull(clientName, "clientName == null");
            Utils.checkNotNull(factory, "factory == null");
            Client client = this.clients.get(clientName);
            if (client == null) {
                throw new NullPointerException("No client registered with " + clientName);
            }
            client.addParserFactory(factory);
            return this;
        }

        public synchronized void initialize() {
            if (KingFisher.instance != null) {
                throw new IllegalStateException("KingFisher has already been initialized.");
            }
            if (!clients.containsKey(KingFisher.DEFAULT_CLIENT)) {
                defaultClient(new NetBird.Builder(baseUrl).build());
            }
            ParserFactory<File> fileFactory = new FakeFileParserFactory();
            if (stringFactory == null) {
                stringFactory = new StringParserFactory();
            }
            for (Map.Entry<String, Client> entry : clients.entrySet()) {
                Client client = entry.getValue();
                client.factories.add(0, stringFactory);
                client.factories.add(0, fileFactory);
                client.factories.addAll(factories);
            }
            KingFisher.instance = new KingFisher(this);
        }
    }
}
