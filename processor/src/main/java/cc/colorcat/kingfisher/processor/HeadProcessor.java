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

import java.lang.annotation.Annotation;

import javax.lang.model.element.Element;

import cc.colorcat.kingfisher.annotation.HEAD;
import cc.colorcat.netbird.Method;

/**
 * Author: cxx
 * Date: 2018-10-08
 * GitHub: https://github.com/ccolorcat
 */
public class HeadProcessor implements AnnotationProcessor {
    @Override
    public void process(MethodModel.Builder builder, Element element, Annotation annotation) {
        HEAD head = (HEAD) annotation;
        builder.path(head.value()).method(Method.HEAD);
    }
}
