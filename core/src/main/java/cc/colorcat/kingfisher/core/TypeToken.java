package cc.colorcat.kingfisher.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Author: cxx
 * Date: 2018-09-30
 * GitHub: https://github.com/ccolorcat
 */
public abstract class TypeToken<T> {
    public final Type actualType() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superClass;
        return parameterizedType.getActualTypeArguments()[0];
    }
}
