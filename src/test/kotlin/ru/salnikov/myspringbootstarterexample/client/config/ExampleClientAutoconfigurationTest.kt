package ru.salnikov.myspringbootstarterexample.client.config

import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import ru.salnikov.myspringbootstarterexample.client.ExampleClient
import java.util.*
import kotlin.test.Test

/**
 * @author Anton Salnikov
 * @since 23.06.2024
 */
class ExampleClientAutoconfigurationTest {

    private val runner = ApplicationContextRunner()
        .withUserConfiguration(ExampleClientAutoconfiguration::class.java)

    @Test
    fun contextHasBeanTest() {
        runner.run {
            assertThat(it).hasSingleBean(ExampleClient::class.java)
        }
    }

    @Test
    fun contextDoesNotHaveBeanTest() {
        runner.withPropertyValues("integration.example.enabled=false")
            .run {
                assertThat(it).doesNotHaveBean(ExampleClient::class.java)
            }
    }

    @Test
    fun onlyCustomConfigurationIntoContextTest() {
        ApplicationContextRunner()
            .withUserConfiguration(
                CustomConfiguration::class.java,
                ExampleClientAutoconfiguration::class.java
            )
            .run {
                assertThat(it).hasSingleBean(CustomConfiguration::class.java)
            }
    }

    class CustomConfiguration {
        @Bean
        fun anotherExampleClient(): ExampleClient {
            return object : ExampleClient {
                override fun getInfoById(id: UUID): String {
                    return ""
                }

                override fun sendInfo(info: String): String {
                    return ""
                }
            }
        }
    }

}