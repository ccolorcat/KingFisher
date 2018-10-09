package cc.colorcat.kingfisher.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: cxx
 * Date: 2018-10-09
 * GitHub: https://github.com/ccolorcat
 */
final class Utils {
    static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList<>(list));
    }

    static String checkedUrl(String url) {
        if (!url.toLowerCase().matches("^(http)(s)?://(\\S)+")) {
            throw new IllegalArgumentException("Bad url = " + url + ", the scheme must be http or https");
        }
        return url;
    }

    private Utils() {
        throw new AssertionError("no instance");
    }
}
