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

import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.net.MediaType;

import java.net.URI;
import java.util.function.Supplier;

/**
 * Read-only resources which when read are always immediately EOF. This is a bit like /dev/null on
 * *NIX OS for reading, but not for writing (because /dev/null ignores writes, whereas this fails).
 *
 * @see NullResource for an alternatives that returns infinite 0s instead of EOF.
 */
public class EmptyResource implements ReadableButNotWritableResource {
    // TODO Perhaps rename this to VoidResource with void:/ URI?

    static final String SCHEME = "empty";
    private static final URI EMPTY_URI = URI.create(SCHEME + ":?");

    private final MediaType mediaType;
    private final Supplier<URI> uriSupplier;
    private URI uri;

    public EmptyResource(MediaType mediaType) {
        this.mediaType = mediaType;
        this.uri = URIs.addMediaType(EMPTY_URI, mediaType);
        this.uriSupplier = null;
    }

    public EmptyResource(MediaType mediaType, Supplier<URI> uriSupplier) {
        this.mediaType = mediaType;
        this.uriSupplier = uriSupplier;
    }

    @Override
    public URI uri() {
        if (uri == null) uri = uriSupplier.get();
        return uri;
    }

    @Override
    public MediaType mediaType() {
        return mediaType;
    }

    @Override
    public ByteSource byteSource() {
        return ByteSource.empty();
    }

    @Override
    public CharSource charSource() {
        return CharSource.empty();
    }

    @Override
    public String toString() {
        return "EmptyResource{uri=" + uri() + ", mediaType=" + mediaType + "}";
    }
}
