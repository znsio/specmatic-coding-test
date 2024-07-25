package com.store

import io.specmatic.test.SpecmaticContractTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

//DO NOT ALTER
class ContractTest : SpecmaticContractTest {
    companion object {

        private lateinit var context: ConfigurableApplicationContext

        @JvmStatic
        @BeforeAll
        fun setUp() {
            System.setProperty("host", "localhost")
            System.setProperty("port", "8090")
            System.setProperty("endpointsAPI", "http://localhost:8090/actuator/mappings")
            System.setProperty("SPECMATIC_GENERATIVE_TESTS", "true")

            context = SpringApplication.run(Application::class.java)
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            context.close()
        }
    }
}
