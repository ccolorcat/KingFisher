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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import cc.colorcat.kingfisher.annotation.DELETE;
import cc.colorcat.kingfisher.annotation.GET;
import cc.colorcat.kingfisher.annotation.HEAD;
import cc.colorcat.kingfisher.annotation.Header;
import cc.colorcat.kingfisher.annotation.OPTIONS;
import cc.colorcat.kingfisher.annotation.POST;
import cc.colorcat.kingfisher.annotation.PUT;
import cc.colorcat.kingfisher.annotation.Param;
import cc.colorcat.kingfisher.annotation.Path;
import cc.colorcat.kingfisher.annotation.TRACE;
import cc.colorcat.kingfisher.annotation.Url;
import cc.colorcat.kingfisher.core.BaseCall;
import cc.colorcat.kingfisher.core.Call;
import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.core.TypeToken;
import cc.colorcat.netbird.Method;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
class MethodFactory {
    private final TypeElement interfaceElement;
    private final ExecutableElement executableElement;
    private String requestUrl;
    private String requestPath;
    private Method requestMethod;
    // 第一个值用以替换 requestPath 中对应的值，第二个值对应形参 name
    private Pair<String, String> requestRelativePath;
    private List<Pair<String, String>> relativePath = new ArrayList<>();
    // name-value, value 实际是形参的 name
    private List<Pair<String, String>> requestParameters = new ArrayList<>();
    // name-value, value 实际是形参的 name
    private List<Pair<String, String>> requestHeaders = new ArrayList<>();
    private TypeName returnTypeName;

    MethodFactory(TypeElement interfaceElement, ExecutableElement executableElement) {
        this.interfaceElement = interfaceElement;
        this.executableElement = executableElement;
    }

    MethodSpec generateMethodSpec() {
        parse();
        MethodSpec.Builder builder = MethodSpec.overriding(executableElement)
                .addStatement(CodeBlock.of("$T dataType = new $T<$T>() {}.generateType()", Type.class, TypeToken.class, returnTypeName))
                .addStatement(CodeBlock.of("$T<$T> call = $T.call(dataType)", BaseCall.class, returnTypeName, KingFisher.class));
        if (Utils.isNotBlank(requestUrl)) {
            builder.addStatement("call.url($S)", requestUrl);
        }
        if (Utils.isNotBlank(requestPath)) {
            final int relativeSize = relativePath.size();
            if (relativeSize > 0) {
                Pair<String, String> path = relativePath.get(0);
                if (relativeSize == 1) {
                    builder.addStatement("call.path($S.replace(\"{$N}\", $N))", requestPath, path.first, path.second);
                } else {
                    CodeBlock.Builder pathBuilder = CodeBlock.of("$T path = $S.replace(\"{$N}\", $N)", String.class, requestPath, path.first, path.second).toBuilder();
                    for (int i = 1; i < relativeSize; ++i) {
                        path = relativePath.get(i);
                        pathBuilder.add(".replace(\"{$N}\", $N)", path.first, path.second);
                    }
                    builder.addStatement(pathBuilder.build());
                }
            } else {
                builder.addStatement("call.path($S)", requestPath);
            }
        }
        builder.addStatement("call.method($T.$N)", Method.class, requestMethod.name());
        for (Pair<String, String> params : requestParameters) {
            builder.addStatement("call.addParameter($S, $T.valueOf($N))", params.first, String.class, params.second);
        }
        for (Pair<String, String> header : requestHeaders) {
            builder.addStatement("call.addHeader($S, $T.valueOf($N))", header.first, String.class, header.second);
        }
        return builder.addStatement("return call").build();
    }

    private void parse() {
        parseUrl();
        parsePathAndMethod();
        parseParameters();
        parseReturnType();
    }

    private void parseUrl() {
        Url url = executableElement.getAnnotation(Url.class);
        if (url != null) {
            requestUrl = url.value();
        }
    }

    private void parsePathAndMethod() {
        GET get = executableElement.getAnnotation(GET.class);
        if (get != null) {
            requestPath = get.value();
            requestMethod = Method.GET;
            return;
        }
        POST post = executableElement.getAnnotation(POST.class);
        if (post != null) {
            requestPath = post.value();
            requestMethod = Method.POST;
            return;
        }
        DELETE delete = executableElement.getAnnotation(DELETE.class);
        if (delete != null) {
            requestPath = delete.value();
            requestMethod = Method.DELETE;
            return;
        }
        PUT put = executableElement.getAnnotation(PUT.class);
        if (put != null) {
            requestPath = put.value();
            requestMethod = Method.PUT;
            return;
        }
        HEAD head = executableElement.getAnnotation(HEAD.class);
        if (head != null) {
            requestPath = head.value();
            requestMethod = Method.HEAD;
            return;
        }
        TRACE trace = executableElement.getAnnotation(TRACE.class);
        if (trace != null) {
            requestPath = trace.value();
            requestMethod = Method.TRACE;
            return;
        }
        OPTIONS options = executableElement.getAnnotation(OPTIONS.class);
        if (options != null) {
            requestPath = options.value();
            requestMethod = Method.OPTIONS;
        }
    }

    private void parseParameters() {
        List<? extends VariableElement> variables = executableElement.getParameters();
        for (VariableElement v : variables) {
            String vName = v.getSimpleName().toString();
            Path path = v.getAnnotation(Path.class);
            if (path != null) {
                relativePath.add(new Pair<>(path.value(), vName));
                requestRelativePath = new Pair<>(path.value(), vName);
                continue;
            }
            Param param = v.getAnnotation(Param.class);
            if (param != null) {
                requestParameters.add(new Pair<>(param.value(), vName));
                continue;
            }
            Header header = v.getAnnotation(Header.class);
            if (header != null) {
                requestHeaders.add(new Pair<>(header.value(), vName));
            }
        }
    }

    private void parseReturnType() {
        TypeName typeName = TypeName.get(executableElement.getReturnType());
        if (typeName instanceof ParameterizedTypeName) {
            ParameterizedTypeName ptn = (ParameterizedTypeName) typeName;
            ClassName raw = ptn.rawType;
            if (ClassName.get(Call.class).compareTo(raw) != 0) {
                throw new IllegalArgumentException("return type must be Call");
            }
            List<TypeName> typeArguments = ptn.typeArguments;
            if (typeArguments.size() != 1) {
                throw new IllegalArgumentException("return type's typeArguments must equal to 1");
            }
            returnTypeName = typeArguments.get(0);
        } else {
            throw new RuntimeException("Missing type parameter.");
        }
    }
}
