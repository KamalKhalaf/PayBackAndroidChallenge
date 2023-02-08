package com.example.common

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 1:49 AM
 */


data class WrappedErrorResponse <T>(
    var statusCode: Int?,
    var status: String?
)

data class ErrorImp (var statusCode : Int, var status : String)