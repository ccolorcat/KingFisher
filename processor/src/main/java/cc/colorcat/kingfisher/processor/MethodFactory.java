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

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

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
    // name-value, value 实际是形参的 name
    private List<Pair<String, String>> requestParameters;
    // name-value, value 实际是形参的 name
    private List<Pair<String, String>> requestHeaders;

    MethodFactory(TypeElement interfaceElement, ExecutableElement executableElement) {
        this.interfaceElement = interfaceElement;
        this.executableElement = executableElement;
    }

    MethodSpec generateMethodSpec() {
        MethodSpec spec = MethodSpec.overriding(executableElement)
                .build();
        return spec;
    }

    private void parse() {
        parseUrl();
        parsePathAndMethod();
        parseParameters();
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
        TypeMirror returnType = executableElement.getReturnType();
//        TypeName.get(returnType);

    }
}
