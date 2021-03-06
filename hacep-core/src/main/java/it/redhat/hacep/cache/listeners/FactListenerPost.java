/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.redhat.hacep.cache.listeners;

import it.redhat.hacep.cache.session.SessionSaver;
import it.redhat.hacep.model.Fact;
import it.redhat.hacep.model.Key;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener(primaryOnly = true, observation = Listener.Observation.POST)
public class FactListenerPost {

    private static final Logger audit = LoggerFactory.getLogger("audit.redhat.hacep");

    private final SessionSaver saver;

    public FactListenerPost(SessionSaver sessionSaver) {
        this.saver = sessionSaver;
    }

    @CacheEntryCreated
    public void created(CacheEntryCreatedEvent event) {
        Object key = event.getKey();
        Object value = event.getValue();
        audit.info(key + " | " + value + " | COD_11 | new fact in grid");
        if (!(checkClass(Fact.class, value) && checkClass(Key.class, key))) {
            audit.error(key + " | " + value + " | COD_14 | expected Fact and Key type");
            return;
        }
        saver.insert((Key) key, (Fact) value);
        audit.info(key + " | " + value + " | COD_12 | chain complete");
    }

    private boolean checkClass(Class<?> clazz, Object o) {
        return (o != null && clazz.isAssignableFrom(o.getClass()));
    }

}