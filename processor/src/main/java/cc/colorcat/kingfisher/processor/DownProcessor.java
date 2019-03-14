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

import javax.lang.model.element.Element;

import cc.colorcat.kingfisher.annotation.Down;
import cc.colorcat.kingfisher.core.DownPack;

/**
 * Author: cxx
 * Date: 2018-10-10
 * GitHub: https://github.com/ccolorcat
 */
final class DownProcessor implements AnnotationProcessor<Down> {
    private static final String DOWN_PACK = DownPack.class.getCanonicalName();

    @Override
    public void process(MethodModel.Builder builder, Element element, Down down) {
        String typeName = element.asType().toString();
        String paramName = element.getSimpleName().toString();
        if (Utils.FILE.equals(typeName)) {
            builder.downSavePath(paramName);
        } else if (DOWN_PACK.equals(typeName)) {
            builder.downPack(paramName);
        } else {
            throw new IllegalArgumentException(element + ", must be File/DownPack, but is " + typeName);
        }
    }
}
