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

import cc.colorcat.netbird.DownloadListener;

/**
 * Author: cxx
 * Date: 2018-10-10
 * GitHub: https://github.com/ccolorcat
 */
public final class DownPack {
    public static DownPack create(File savePath) {
        return create(savePath, null);
    }

    public static DownPack create(File savePath, DownloadListener listener) {
        if (savePath.exists()) {
            throw new RuntimeException(savePath + " exists");
        }
        File parent = savePath.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new RuntimeException("Can't create directory, " + parent.getPath());
        }
        return new DownPack(savePath, listener);
    }

    final File savePath;
    final DownloadListener listener;

    private DownPack(File savePath, DownloadListener listener) {
        this.savePath = savePath;
        this.listener = listener;
    }
}
