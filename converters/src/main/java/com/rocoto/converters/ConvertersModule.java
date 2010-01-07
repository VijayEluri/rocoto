/*
 *    Copyright 2009-2010 The Rocoto Team
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
package com.rocoto.converters;

import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeConverter;

/**
 * 
 * @author Simone Tripodi
 * @version $Id$
 */
public final class ConvertersModule extends AbstractModule {

    private final Map<Class<?>, TypeConverter> converters = new HashMap<Class<?>, TypeConverter>();

    public ConvertersModule() {
        this.registerConverter(URL.class, new URLTypeConverter());
        this.registerConverter(java.sql.Date.class, new SQLDateConverter());
        this.registerConverter(Time.class, new SQLTimeConverter());
        this.registerConverter(Timestamp.class, new SQLTimestampConverter());
        this.registerConverter(Class.class, new ClassConverter());

        TypeConverter booleanConverter = new BooleanConverter();
        this.registerConverter(boolean.class, booleanConverter);
        this.registerConverter(Boolean.class, booleanConverter);
    }

    public void registerConverter(Class<?> target, TypeConverter converter) {
        this.converters.put(target, converter);
    }

    public void registerConverters(Map<Class<?>, TypeConverter> converters) {
        this.converters.putAll(converters);
    }

    public TypeConverter lookup(Class<?> target) {
        return this.converters.get(target);
    }

    public TypeConverter deregister(Class<?> target) {
        return this.converters.remove(target);
    }

    @Override
    protected void configure() {
        for (Entry<Class<?>, TypeConverter> converter : this.converters.entrySet()) {
            this.binder().convertToTypes(Matchers.only(TypeLiteral.get(converter.getKey())), converter.getValue());
        }
    }

}
