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

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    static final String FILE = File.class.getCanonicalName();

    private static final String STRING = String.class.getCanonicalName();
    private static final String STRING_MAP = String.format(
            "%s<%s,%s>",
            Map.class.getCanonicalName(),
            String.class.getCanonicalName(),
            String.class.getCanonicalName()
    );

    static void assertString(Element element) {
        String typeName = element.asType().toString();
        if (!STRING.equals(typeName)) {
            throw new IllegalArgumentException(element + " must be String, but is " + typeName);
        }
    }

    static void assertStringMap(Element element) {
        String typeName = element.asType().toString();
        if (!STRING_MAP.equals(typeName)) {
            throw new IllegalArgumentException(element + " must be " + STRING_MAP + " but is " + typeName);
        }
    }

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
            throw new RuntimeException(element + " is not interface");
        }
        return (TypeElement) element;
    }

    static ExecutableElement assertMethod(Element element) {
        if (element.getKind() != ElementKind.METHOD) {
            throw new RuntimeException(element + " is not method");
        }
        return (ExecutableElement) element;
    }

    static TypeName getActualReturnTypeName(ExecutableElement element) {
        TypeName typeName = TypeName.get(element.getReturnType());
        if (typeName instanceof ParameterizedTypeName) {
            ParameterizedTypeName ptn = (ParameterizedTypeName) typeName;
            if (ClassName.get(Call.class).compareTo(ptn.rawType) != 0) {
                throw new IllegalArgumentException(element + " returns " + typeName + ", it must be " + Call.class.getCanonicalName());
            }
            List<TypeName> typeArguments = ptn.typeArguments;
            if (typeArguments.size() != 1) {
                throw new IllegalArgumentException(element + " returns " + typeName + ", it must have one and only one generic type");
            }
            TypeName actualTypeName = typeArguments.get(0);
            assertNoWildcardType(actualTypeName, element);
            return actualTypeName;
        }
        throw new IllegalArgumentException(element + " returns " + typeName + ", it missing type parameter");
    }

    private static void assertNoWildcardType(TypeName typeName, ExecutableElement element) {
        if (typeName instanceof WildcardTypeName) {
            throw new IllegalArgumentException(element + ", found wildcard");
        }
        if (typeName instanceof ParameterizedTypeName) {
            for (TypeName name : ((ParameterizedTypeName) typeName).typeArguments) {
                assertNoWildcardType(name, element);
            }
        }
    }

    static <T> void checkNotNull(T value, String msg) {
        if (value == null) {
            throw new NullPointerException(msg);
        }
    }

    static void assertNotBlank(String text, String msg) {
        if (isBlank(text)) throw new IllegalArgumentException(msg);
    }

    static boolean isBlank(String text) {
        return text == null || text.trim().length() == 0;
    }

    private Utils() {
        throw new AssertionError("no instance");
    }
}
