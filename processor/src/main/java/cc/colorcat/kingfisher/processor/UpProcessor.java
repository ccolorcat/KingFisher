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

import java.io.File;
import java.util.List;

import javax.lang.model.element.Element;

import cc.colorcat.kingfisher.annotation.Up;
import cc.colorcat.kingfisher.core.UpPack;

/**
 * Author: cxx
 * Date: 2018-10-10
 * GitHub: https://github.com/ccolorcat
 */
final class UpProcessor implements AnnotationProcessor<Up> {
    private static final String UP_PACK = UpPack.class.getCanonicalName();
    private static final String UP_PACK_LIST = String.format(
            "%s<%s>", List.class.getCanonicalName(), UpPack.class.getCanonicalName()
    );

    @Override
    public void process(MethodModel.Builder builder, Element element, Up up) {
        String typeName = element.asType().toString();
        String paramName = element.getSimpleName().toString();
        String name = up.name();
        String contentType = up.contentType();
        if (Utils.FILE.equals(typeName)) {
            Utils.assertNotBlank(contentType, element + ", contentType is empty.");
            builder.singleUp(new Triple<>(up.name(), up.contentType(), paramName));
        } else if (UP_PACK.equals(typeName)) {
            if (!Utils.isBlank(name) || !Utils.isBlank(contentType)) {
                throw new IllegalArgumentException(element + " is " + UP_PACK + ", name and contentType must be default");
            }
            builder.upPack(paramName);
        } else if (UP_PACK_LIST.equals(typeName)) {
            if (!Utils.isBlank(name) || !Utils.isBlank(contentType)) {
                throw new IllegalArgumentException(element + " is " + UP_PACK_LIST + ", name and contentType must be default");
            }
            builder.batchUpPack(paramName);
        } else {
            throw new IllegalArgumentException(element + ", must be File/UpPack/List<UpPack>, but is " + typeName);
        }
    }
}
