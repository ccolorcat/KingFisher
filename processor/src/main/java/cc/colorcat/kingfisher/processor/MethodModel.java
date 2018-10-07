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

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

import cc.colorcat.kingfisher.core.BaseCall;
import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.core.TypeToken;
import cc.colorcat.netbird.Method;

/**
 * Author: cxx
 * Date: 2018-10-03
 * GitHub: https://github.com/ccolorcat
 */
class MethodModel {
    private static final String NAME_TYPE = "dataType";
    private static final String NAME_CALL = "call";

    private final ExecutableElement methodElement;
    private final String url;
    private final String path;
    private final Method method;
    private final List<Pair<String, String>> relativePaths;
    private final List<Pair<String, String>> parameters;
    private final List<Pair<String, String>> headers;
    private final TypeName returnType;

    private MethodModel(Builder builder) {
        this.methodElement = builder.methodElement;
        this.url = builder.url;
        this.path = builder.path;
        this.method = builder.method;
        this.relativePaths = builder.relativePaths;
        this.parameters = builder.parameters;
        this.headers = builder.headers;
        this.returnType = builder.returnType;
    }

    MethodSpec generateCode() {
        MethodSpec.Builder builder = MethodSpec.overriding(methodElement)
                .addStatement("$T $N = new $T<$T>() {}.generateType()", Type.class, NAME_TYPE, TypeToken.class, returnType)
                .addStatement("$T<$T> $N = $T.call($N)", BaseCall.class, returnType, NAME_CALL, KingFisher.class, NAME_TYPE);
        if (Utils.isNotBlank(url)) {
            builder.addStatement("$N.url($S)", NAME_CALL, url);
        }
        return builder.build();
    }

    static class Builder {
        private ExecutableElement methodElement;
        private String url;
        private String path;
        private Method method;
        private List<Pair<String, String>> relativePaths = new ArrayList<>(1);
        private List<Pair<String, String>> parameters = new ArrayList<>(4);
        private String paramMapName;
        private List<Pair<String, String>> headers = new ArrayList<>(1);
        private String headerMapName;
        private TypeName returnType;

        Builder() {
        }

        Builder method(ExecutableElement methodElement) {
            this.methodElement = methodElement;
            return this;
        }

        Builder url(String url) {
            this.url = url;
            return this;
        }

        Builder path(String path) {
            this.path = path;
            return this;
        }

        Builder method(Method method) {
            this.method = method;
            return this;
        }

        Builder addRelativePath(Pair<String, String> relativePath) {
            this.relativePaths.add(relativePath);
            return this;
        }

        Builder addParameter(Pair<String, String> parameter) {
            this.parameters.add(parameter);
            return this;
        }

        Builder parameterMap(String name) {
            this.paramMapName = name;
            return this;
        }

        Builder addHeader(Pair<String, String> header) {
            this.headers.add(header);
            return this;
        }

        Builder headerMap(String name) {
            this.headerMapName = name;
            return this;
        }

        Builder returnType(TypeName typeName) {
            this.returnType = typeName;
            return this;
        }
    }
}
