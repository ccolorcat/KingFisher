package cc.colorcat.kingfisher.core;

import java.lang.reflect.Type;

import cc.colorcat.netbird.Parser;

/**
 * Author: cxx
 * Date: 2018-09-30
 * GitHub: https://github.com/ccolorcat
 */
public interface ParserFactory {
    Parser<?> newParser(Type typeOfT);
}
