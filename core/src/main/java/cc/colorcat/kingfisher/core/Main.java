package cc.colorcat.kingfisher.core;

import java.lang.reflect.Type;
import java.util.List;

import cc.colorcat.netbird.Method;

/**
 * Author: cxx
 * Date: 2018-09-30
 * GitHub: https://github.com/ccolorcat
 */
public class Main {

    public static void main(String[] args) {

    }

    static Call<List<String>> list() {
        String user = "";
        Type type = new TypeToken<List<String>>() {}.generateType();
        BaseCall<List<String>> call = KingFisher.call(type);
        call.url("https://www.baidu.com");
        call.path("sdfsdf".replace("{user}", user));
        call.method(Method.GET);
        call.parameter("id", String.valueOf(123));
        call.header("token", String.valueOf("test"));
        return call;
    }
}
