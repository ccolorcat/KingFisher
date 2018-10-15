package cc.colorcat.kingfisher.core;

import java.lang.reflect.Type;

import cc.colorcat.netbird.Parser;
import cc.colorcat.netbird.StringParser;

/**
 * Author: cxx
 * Date: 2018-09-30
 * GitHub: https://github.com/ccolorcat
 */
final class StringParserFactory implements ParserFactory<String> {
    @Override
    public Parser<? extends String> newParser(Type typeOfT) {
        if (typeOfT instanceof Class) {
            Class<?> clazz = (Class) typeOfT;
            if (clazz.isAssignableFrom(String.class)) {
                return StringParser.getUtf8();
            }
        }
        return null;
    }
}
