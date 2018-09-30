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

package cc.colorcat.kingfisher.processor;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import cc.colorcat.kingfisher.core.Call;
import cc.colorcat.netbird.Method;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
public class Main {
    public static void main(String[] args) {
        Method method = Method.GET;
        System.out.println(method.getClass().getCanonicalName());
        System.out.println(method.getClass().getTypeName());
        Type type = new Test<Call<List<String>>>() {}.generateTypes()[0];
        TypeName typeName = TypeName.get(type);
        System.out.println(typeName.toString());
        System.out.println(typeName instanceof ArrayTypeName);
        System.out.println(typeName instanceof ClassName);
        System.out.println(typeName instanceof ParameterizedTypeName);
        System.out.println(typeName instanceof TypeVariableName);
        System.out.println(typeName instanceof WildcardTypeName);
        System.out.println();
        System.out.println();

        ParameterizedTypeName ptn = (ParameterizedTypeName) typeName;
        ClassName raw = ptn.rawType;
        System.out.println(raw.compareTo(ClassName.get(Call.class)));
        System.out.println(ptn.rawType);
        System.out.println(ptn.typeArguments);
        System.out.println(ptn.typeArguments.get(0));

//        Test<Call<List<String>>> test = new Test<Call<List<String>>>() {};
//        System.out.println(test.generateTypes()[0].getTypeName());

//        System.out.println(token.getSubtype(Call.class));
    }

    static abstract class Test<T> {
        protected final Type[] generateTypes() {
            Type superClass = getClass().getGenericSuperclass();
            if (superClass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterizedType = (ParameterizedType) superClass;
            return parameterizedType.getActualTypeArguments();
        }
    }
}
