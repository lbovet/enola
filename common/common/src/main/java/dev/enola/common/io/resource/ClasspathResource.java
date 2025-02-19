/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2023-2024 The Enola <https://enola.dev> Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.enola.common.io.resource;

import com.google.common.io.Resources;
import com.google.common.net.MediaType;

import java.nio.charset.Charset;

public class ClasspathResource extends UrlResource {

    public static final String SCHEME = "classpath";

    public ClasspathResource(String path, MediaType mediaType) {
        super(Resources.getResource(path), mediaType);
    }

    public ClasspathResource(String path, Charset charset) {
        // TODO mediaType should be set based on looking at file extension from path
        super(Resources.getResource(path), charset);
    }

    public ClasspathResource(String path) {
        // TODO mediaType should be set based on looking at file extension from path
        super(Resources.getResource(path));
    }
}
