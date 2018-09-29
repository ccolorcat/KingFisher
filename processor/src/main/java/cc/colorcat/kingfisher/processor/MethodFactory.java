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
    private String requestRelativePath;
    private List<Pair<String, String>> requestParameters; // name-value, value 实际是形参的 name
    private List<Pair<String, String>> requestHeader; // name-value, value 实际是形参的 name

    MethodFactory(TypeElement interfaceElement, ExecutableElement executableElement) {
        this.interfaceElement = interfaceElement;
        this.executableElement = executableElement;
    }

    MethodSpec generateMethodSpec() {
        return MethodSpec.overriding(executableElement).build();
    }

    private void parse() {

    }
}
