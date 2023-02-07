package com.example.paybackandroidchallenge.common

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 12:13 AM
 */


sealed class BaseResult <out T : Any, out U : Any> {
    data class Success <T: Any>(val data : T) : BaseResult<T, Nothing>()
    data class Error <U : Any>(val rawResponse: U) : BaseResult<Nothing, U>()
}