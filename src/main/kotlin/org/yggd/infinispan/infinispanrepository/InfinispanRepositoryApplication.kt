package org.yggd.infinispan.infinispanrepository

import org.infinispan.client.hotrod.RemoteCache
import org.infinispan.client.hotrod.RemoteCacheManager
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.annotation.PreDestroy

@SpringBootApplication
class InfinispanRepositoryApplication {

    @Bean
    fun remoteCacheManager(@Value("\${repository.host}") host: String,
                           @Value("\${repository.port}") port: Int) : RemoteCacheManager =
        RemoteCacheManager(ConfigurationBuilder()
                .addServer()
                .host(host)
                .port(port)
                .build())

    @Autowired
    lateinit var manager: RemoteCacheManager

    @PreDestroy
    fun preDestroy() {
        manager.close()
    }
}

fun main(args: Array<String>) {
    runApplication<InfinispanRepositoryApplication>(*args)
}
