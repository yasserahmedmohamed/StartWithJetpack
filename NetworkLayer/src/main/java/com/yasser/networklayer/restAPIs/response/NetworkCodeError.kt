package com.yasser.networklayer.restAPIs.response

enum class NetworkCodeError {

    FORBIDDEN,
    UNAUTHORIZED,
    BAD_REQUEST,
    GENERIC_HTTP_ERROR,
    SERVER_NOT_REACHABLE,
    NOT_FOUND,
    NO_AVAILABLE_DATA,
    INVALID_PARAMETER
}