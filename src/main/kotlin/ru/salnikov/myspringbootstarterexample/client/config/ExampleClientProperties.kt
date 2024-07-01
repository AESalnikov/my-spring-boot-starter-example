package ru.salnikov.myspringbootstarterexample.client.config

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

/**
 * @author Anton Salnikov
 * @since 23.06.2024
 */
@Validated
@ConfigurationProperties(prefix = "integration.example")
class ExampleClientProperties {

    val enabled = true

    @Valid
    val endpoint = Endpoint()

    class Endpoint {

        @NotEmpty
        lateinit var baseUrl: String

    }

}
