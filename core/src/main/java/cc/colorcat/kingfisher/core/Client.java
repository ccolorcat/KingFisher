package cc.colorcat.kingfisher.core;

import java.util.ArrayList;
import java.util.List;

import cc.colorcat.netbird.NetBird;

/**
 * Author: cxx
 * Date: 2019-05-30
 * GitHub: https://github.com/ccolorcat
 */
final class Client {
    final NetBird bird;
    final List<ParserFactory<?>> factories;

    Client(NetBird bird) {
        this.bird = bird;
        this.factories = new ArrayList<>();
    }

    void addParserFactory(ParserFactory<?> factory) {
        if (!this.factories.contains(factory)) {
            this.factories.add(factory);
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "bird=" + bird +
                ", factories=" + factories +
                '}';
    }
}
