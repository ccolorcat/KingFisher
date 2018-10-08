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

import com.squareup.javapoet.CodeBlock;
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
    private static final String TYPE = "dataType";
    private static final String CALL = "call";
    private static final String PATH = "path";

    private final ExecutableElement element;
    private final String url;
    private final String path;
    private final Method method;
    private final List<Pair<String, String>> relativePaths;
    private final List<Pair<String, String>> parameters;
    private final String paramMapName;
    private final List<Pair<String, String>> headers;
    private final String headerMapName;
    private final TypeName returnType;

    private MethodModel(Builder builder) {
        this.element = builder.element;
        this.url = builder.url;
        this.path = builder.path;
        this.method = builder.method;
        this.relativePaths = builder.relativePaths;
        this.parameters = builder.parameters;
        this.paramMapName = builder.paramMapName;
        this.headers = builder.headers;
        this.headerMapName = builder.headerMapName;
        this.returnType = builder.returnType;
    }

    MethodSpec generateCode() {
        MethodSpec.Builder builder = MethodSpec.overriding(element);
        processCall(builder);
        processUrl(builder);
        processPath(builder);
        processMethod(builder);
        processParameters(builder);
        processHeaders(builder);
        processReturn(builder);
        return builder.build();
    }

    private void processCall(MethodSpec.Builder builder) {
        builder.addStatement("$T $N = new $T<$T>() {}.generateType()", Type.class, TYPE, TypeToken.class, returnType)
                .addStatement("$T<$T> $N = $T.newCall($N)", BaseCall.class, returnType, CALL, KingFisher.class, TYPE);
    }

    private void processUrl(MethodSpec.Builder builder) {
        if (!Utils.isBlank(url)) builder.addStatement("$N.url($S)", CALL, url);
    }

    private void processPath(MethodSpec.Builder builder) {
        if (Utils.isBlank(path)) return;

        final int size = relativePaths.size();
        if (size == 0) {
            builder.addStatement("$N.path($S)", CALL, path);
            return;
        }

        Pair<String, String> p = relativePaths.get(0);
        if (size == 1) {
            builder.addStatement("$N.path($S.replace(\"{$N}\", $N))", CALL, path, p.first, p.second);
            return;
        }
        CodeBlock.Builder pb = CodeBlock.of("$T $N = $S.replace(\"{$N}\", $N)", String.class, PATH, path, p.first, p.second).toBuilder();
        for (int i = 1; i < size; ++i) {
            p = relativePaths.get(i);
            pb.add(".replace(\"{$N}\", $N)", p.first, p.second);
        }
        builder.addStatement(pb.build());
    }

    private void processMethod(MethodSpec.Builder builder) {
        builder.addStatement("$N.method($T.$N)", CALL, Method.class, method.name());
    }

    private void processParameters(MethodSpec.Builder builder) {
        for (int i = 0, size = parameters.size(); i < size; ++i) {
            Pair<String, String> param = parameters.get(i);
            builder.addStatement("$N.parameter($S, $T.valueOf($N))", CALL, param.first, String.class, param.second);
        }
        if (!Utils.isBlank(paramMapName)) {
            builder.addStatement("$N.parameters($N)", CALL, paramMapName);
        }
    }

    private void processHeaders(MethodSpec.Builder builder) {
        for (int i = 0, size = headers.size(); i < size; ++i) {
            Pair<String, String> header = headers.get(i);
            builder.addStatement("$N.header($S, $T.valueOf($N))", CALL, header.first, String.class, header.second);
        }
        if (!Utils.isBlank(headerMapName)) {
            builder.addStatement("$N.headers($N)", CALL, headerMapName);
        }
    }

    private void processReturn(MethodSpec.Builder builder) {
        builder.addStatement("return $N", CALL);
    }

    static class Builder {
        private ExecutableElement element; // method
        /**
         * @see cc.colorcat.kingfisher.annotation.Url
         */
        private String url;
        /**
         * @see cc.colorcat.kingfisher.annotation.GET
         * @see cc.colorcat.kingfisher.annotation.HEAD
         * @see cc.colorcat.kingfisher.annotation.TRACE
         * @see cc.colorcat.kingfisher.annotation.OPTIONS
         * @see cc.colorcat.kingfisher.annotation.POST
         * @see cc.colorcat.kingfisher.annotation.PUT
         * @see cc.colorcat.kingfisher.annotation.DELETE
         */
        private String path;
        /**
         * @see cc.colorcat.kingfisher.annotation.GET
         * @see cc.colorcat.kingfisher.annotation.HEAD
         * @see cc.colorcat.kingfisher.annotation.TRACE
         * @see cc.colorcat.kingfisher.annotation.OPTIONS
         * @see cc.colorcat.kingfisher.annotation.POST
         * @see cc.colorcat.kingfisher.annotation.PUT
         * @see cc.colorcat.kingfisher.annotation.DELETE
         */
        private Method method;
        /**
         * @see cc.colorcat.kingfisher.annotation.Path
         */
        private List<Pair<String, String>> relativePaths = new ArrayList<>(1);
        /**
         * @see cc.colorcat.kingfisher.annotation.Param
         */
        private List<Pair<String, String>> parameters = new ArrayList<>(4);
        /**
         * Map<String, String>, name of arg
         *
         * @see cc.colorcat.kingfisher.annotation.ParamMap
         */
        private String paramMapName;
        /**
         * @see cc.colorcat.kingfisher.annotation.Header
         */
        private List<Pair<String, String>> headers = new ArrayList<>(1);
        /**
         * Map<String, String>, name of arg
         *
         * @see cc.colorcat.kingfisher.annotation.HeaderMap
         */
        private String headerMapName;
        private TypeName returnType;

        Builder(ExecutableElement element) {
            this.element = element;
        }

        Builder element(ExecutableElement methodElement) {
            this.element = methodElement;
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
            if (this.paramMapName != null) {
                throw new IllegalArgumentException("more than one ParamMap");
            }
            this.paramMapName = name;
            return this;
        }

        Builder addHeader(Pair<String, String> header) {
            this.headers.add(header);
            return this;
        }

        Builder headerMap(String name) {
            if (this.headerMapName != null) {
                throw new IllegalArgumentException("more than one HeaderMap");
            }
            this.headerMapName = name;
            return this;
        }

        Builder returnType(TypeName typeName) {
            this.returnType = typeName;
            return this;
        }

        MethodModel build() {
            Utils.checkNotNull(method, "no request method in " + element);
            Utils.checkNotNull(returnType, "no return type in " + element);
            if (relativePaths.size() > 0) {
                if (Utils.isBlank(path)) {
                    throw new IllegalArgumentException(element + ", relative path has been set, but path is empty.");
                }
                for (int i = 0, size = relativePaths.size(); i < size; ++i) {
                    Pair<String, String> pair = relativePaths.get(i);
                    String rp = '{' + pair.first + '}';
                    if (!path.contains(rp)) {
                        throw new IllegalArgumentException(path + " not contain" + rp + " in " + element);
                    }
                }
            }
            return new MethodModel(this);
        }
    }
}
