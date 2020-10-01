package org.yggd.infinispan.infinispanrepository

import org.infinispan.client.hotrod.RemoteCache
import org.infinispan.client.hotrod.RemoteCacheManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class InfinispanRepository(val manager: RemoteCacheManager) {

    lateinit var cache: RemoteCache<String, String>

    @Value("\${app.cacheName}")
    lateinit var cacheName: String

    @PostConstruct
    fun postConstruct() {
        cache = manager.getCache(cacheName)
    }

    fun size() = cache.size
    fun keys() = cache.keys

    fun clear() {
        cache.clear()
    }
    fun put(key: String, value: String) {
        cache[key] = value
    }
    fun get(key: String) = cache[key]
}
