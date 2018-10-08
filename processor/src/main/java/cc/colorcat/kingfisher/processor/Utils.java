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
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.WildcardTypeName;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import cc.colorcat.kingfisher.core.Call;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
final class Utils {
    static void writeToJava(ServiceFactory factory, Filer filer) {
        try {
            factory.writeOut(filer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    static List<Annotation> getAllAnnotations(Element element) {
        List<? extends AnnotationMirror> mirrors = element.getAnnotationMirrors();
        List<Annotation> annotations = new ArrayList<>(mirrors.size());
        try {
            for (AnnotationMirror mirror : mirrors) {
                Class<Annotation> clazz = (Class<Annotation>) Class.forName(mirror.getAnnotationType().toString());
                Annotation annotation = element.getAnnotation(clazz);
                annotations.add(annotation);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return annotations;
    }

    static TypeElement assertInterface(Element element) {
        if (element.getKind() != ElementKind.INTERFACE) {
            throw new RuntimeException(element + " is not interface.");
        }
        return (TypeElement) element;
    }

    static ExecutableElement assertMethod(Element element) {
        if (element.getKind() != ElementKind.METHOD) {
            throw new RuntimeException(element + " is not method.");
        }
        return (ExecutableElement) element;
    }

    static TypeName assertNoWildType(TypeName typeName, ExecutableElement element) {
        if (typeName instanceof WildcardTypeName) {
            throw new IllegalArgumentException("found wildcard return type in " + element);
        }
        if (typeName instanceof ParameterizedTypeName) {
            for (TypeName name : ((ParameterizedTypeName) typeName).typeArguments) {
                assertNoWildType(name, element);
            }
        }
        return typeName;
    }

    static TypeName getReturnTypeName(ExecutableElement element) {
        TypeName typeName = TypeName.get(element.getReturnType());
        if (typeName instanceof ParameterizedTypeName) {
            ParameterizedTypeName ptn = (ParameterizedTypeName) typeName;
            ClassName raw = ptn.rawType;
            if (ClassName.get(Call.class).compareTo(raw) != 0) {
                throw new IllegalArgumentException(element + " returns " + typeName + ", it must be " + Call.class.getCanonicalName());
            }
            List<TypeName> typeArguments = ptn.typeArguments;
            if (typeArguments.size() != 1) {
                throw new IllegalArgumentException(element + " returns " + typeName + ", it must have one and only one generic type.");
            }
            TypeName name = typeArguments.get(0);
            assertNoWildType(name, element);
            return name;
        } else {
            throw new IllegalArgumentException(element + " returns " + typeName + ", it missing type parameter.");
        }
    }

    static <T> T checkNotNull(T value, String msg) {
        if (value == null) {
            throw new NullPointerException(msg);
        }
        return value;
    }

    static boolean isNotBlank(String text) {
        return text != null && text.trim().length() > 0;
    }

    static boolean isBlank(String text) {
        return text == null || text.trim().length() == 0;
    }

    private Utils() {throw new AssertionError("no instance");}
}
