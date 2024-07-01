package ru.salnikov.myspringbootstarterexample.client.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import ru.salnikov.myspringbootstarterexample.client.ExampleClient
import ru.salnikov.myspringbootstarterexample.client.impl.DefaultExampleClient

/**
 * @author Anton Salnikov
 * @since 23.06.2024
 */
@AutoConfiguration
@PropertySource("classpath:example-client-defaults.properties")
@EnableConfigurationProperties(ExampleClientProperties::class)
@ConditionalOnProperty(
    prefix = "integration.example",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
class ExampleClientAutoconfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun exampleClient(properties: ExampleClientProperties): ExampleClient {
        return DefaultExampleClient(properties)
    }

}
