package org.yggd.infinispan.infinispanrepository

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class InfinispanRepositoryApplicationTests {

    @Autowired
    lateinit var repository: InfinispanRepository

    @Test
    fun putAndGet() {
        repository.clear()
        (1..100).forEach{
            repository.put(it.toString(), UUID.randomUUID().toString())
        }

        assertThat(repository.size(), `is`(100))
    }
}
