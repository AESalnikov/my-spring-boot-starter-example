package ru.salnikov.myspringbootstarterexample.client

import java.util.*

/**
 * @author Anton Salnikov
 * @since 23.06.2024
 */
interface ExampleClient {

    fun getInfoById(id: UUID): String

    fun sendInfo(info: String): String

}
