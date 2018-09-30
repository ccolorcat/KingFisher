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
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
final class ServiceFactory {
    private final PackageElement packageElement;
    private final TypeElement interfaceElement;
    private final String classSimpleName;
    private final List<? extends MethodFactory> methodFactories;

    private ServiceFactory(Builder builder) {
        this.packageElement = builder.packageElement;
        this.interfaceElement = builder.interfaceElement;
        this.classSimpleName = builder.classSimpleName;
        this.methodFactories = builder.methodFactories;
    }

    void writeOut(Filer filer) {
        ClassName className = ClassName.get(packageElement.getQualifiedName().toString(), classSimpleName);
        TypeSpec service = TypeSpec.classBuilder(className)
                .addSuperinterface(TypeName.get(interfaceElement.asType()))
                .addModifiers(Modifier.PUBLIC)
                .addMethods(Utils.map(methodFactories))
                .build();
        JavaFile file = JavaFile.builder(packageElement.getQualifiedName().toString(), service)
//                .addStaticImport(KingFisher.class)
                .build();
        try {
            file.writeTo(filer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static class Builder {
        private PackageElement packageElement;
        private TypeElement interfaceElement;
        private String classSimpleName;
        private List<ExecutableElement> executableElements = new ArrayList<>();
        private List<MethodFactory> methodFactories;

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
            classSimpleName = interfaceElement.getSimpleName() + "Service";
            methodFactories = Utils.map(interfaceElement, executableElements);
            return new ServiceFactory(this);
        }
    }
}
