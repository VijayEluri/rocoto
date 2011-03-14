/*
 *    Copyright 2009-2010 The 99 Software Foundation
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.nnsoft.guice.rocoto.configuration.traversal;

import java.io.File;

import org.nnsoft.guice.rocoto.configuration.ConfigurationReader;

/**
 * 
 *
 * 
 */
public final class PropertiesReaderBuilder extends ConfigurationReaderBuilder {

    private static final String PROPERTIES_PATTERN = "**/*.properties";

    public PropertiesReaderBuilder() {
        super(PROPERTIES_PATTERN);
    }

    @Override
    public ConfigurationReader create(File configurationFile) {
        return null;
        // return new PropertiesURLReader(configurationFile);
    }

}
