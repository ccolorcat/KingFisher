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

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
final class Utils {

    static List<MethodSpec> map(List<? extends MethodFactory> factories) {
        final int size = factories.size();
        List<MethodSpec> methodSpecs = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            methodSpecs.add(factories.get(i).generateMethodSpec());
        }
        return methodSpecs;
    }

    static List<MethodFactory> map(TypeElement interfaceElement, List<? extends ExecutableElement> elements) {
        final int size = elements.size();
        List<MethodFactory> factories = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            factories.add(new MethodFactory(interfaceElement, elements.get(i)));
        }
        return factories;
    }

    static void assertInterface(Element element) {
        if (element.getKind() != ElementKind.INTERFACE) {
            throw new IllegalArgumentException(element.toString() + "is not interface");
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

    private Utils() {throw new AssertionError("no instance");}
}
