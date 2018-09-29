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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import cc.colorcat.netbird.Method;
import cc.colorcat.netbird.Parser;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
final class ServiceFactory {
    Messager messager;
    String packageName;
    String interfaceSimpleName;
    String classSimpleName;
    List<? extends ExecutableElement> methods;

    private Parser<?> requestParser;
    private String requestUrl;
    private String requestPath;
    private Method requestMethod;
    private String requestRelativePath;
    private List<Pair<String, String>> requestParameters; // name-value, value 实际是形参的 name
    private List<Pair<String, String>> requestHeader; // name-value, value 实际是形参的 name

    private ServiceFactory(Builder builder) {

    }

    void writeOut(Filer filer) {
    }

    static class Builder {
        private PackageElement packageElement;
        private TypeElement interfaceElement;
        private List<ExecutableElement> executableElements = new ArrayList<>();
        private String classSimpleName;

        private String requestUrl;
        private String requestPath;
        private Method requestMethod;
        private String requestRelativePath;
        private List<Pair<String, String>> requestParameters; // name-value, value 实际是形参的 name
        private List<Pair<String, String>> requestHeader; // name-value, value 实际是形参的 name

        Builder setPackageElement(PackageElement packageElement) {
            this.packageElement = packageElement;
            return this;
        }

        Builder setInterfaceElement(TypeElement interfaceElement) {
            this.interfaceElement = interfaceElement;
            return this;
        }

        Builder addExecutableElement(ExecutableElement executableElement) {
            this.executableElements.add(executableElement);
            return this;
        }


        ServiceFactory build() {
            Utils.checkNotNull(packageElement, "packageElement is null");
            Utils.checkNotNull(interfaceElement, "interfaceElement is null");
            generate();
            return new ServiceFactory(this);
        }

        private void generate() {
            this.classSimpleName = interfaceElement.getSimpleName() + "Service";
        }
    }
}
