package ru.salnikov.myspringbootstarterexample.client.impl

import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.client.RestClient
import ru.salnikov.myspringbootstarterexample.client.ExampleClient
import ru.salnikov.myspringbootstarterexample.client.config.ExampleClientProperties
import java.util.*

/**
 * @author Anton Salnikov
 * @since 23.06.2024
 */
class DefaultExampleClient(properties: ExampleClientProperties) : ExampleClient {

    private val client = RestClient.builder()
        .baseUrl(properties.endpoint.baseUrl)
        .build()

    override fun getInfoById(id: UUID): String {
        return client.get()
            .uri { it.path("/example/{id}/info").build(mapOf("id" to id)) }
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .retrieve()
            .body(String::class.java)
            .orEmpty()
    }

    override fun sendInfo(info: String): String {
        return client.post()
            .uri { it.path("/example/info").build() }
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .body(info)
            .retrieve()
            .body(String::class.java)
            .orEmpty()
    }

}
