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

package cc.colorcat.kingfisher.core;

import java.io.File;

import cc.colorcat.netbird.UploadListener;

/**
 * Author: cxx
 * Date: 2018-10-10
 * GitHub: https://github.com/ccolorcat
 */
public final class UpPack {
    public static UpPack create(String name, String contentType, File file) {
        return create(name, contentType, file, null);
    }

    public static UpPack create(String name, String contentType, File file, UploadListener listener) {
        Utils.checkNotNull(name, "name is null");
        Utils.checkNotNull(contentType, "contentType is null");
        if (file.isDirectory() || !file.exists()) {
            throw new IllegalArgumentException("file is a directory or not exists");
        }
        return new UpPack(name, contentType, file, listener);
    }

    final String name;
    final String contentType;
    final File file;
    final UploadListener listener;

    private UpPack(String name, String contentType, File file, UploadListener listener) {
        this.name = name;
        this.contentType = contentType;
        this.file = file;
        this.listener = listener;
    }
}
