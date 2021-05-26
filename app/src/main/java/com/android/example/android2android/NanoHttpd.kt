package com.android.example.android2android

import fi.iki.elonen.NanoHTTPD



class NanoHttpd {


    companion object {
        var deviceList: String = ""
        val server = object : NanoHTTPD(8080) {
            override fun serve(
                uri: String?,
                method: Method?,
                headers: MutableMap<String, String>?,
                parms: MutableMap<String, String>?,
                files: MutableMap<String, String>?
            ): Response? = when (uri) {
                "/" -> newFixedLengthResponse("Hello From REST Endpoint")
                "/register" -> {
                    val username = parms?.get("username")
                    deviceList += "$username\n"
                    newFixedLengthResponse("Hello $username")
                }
                else -> newFixedLengthResponse(
                    Response.Status.NOT_FOUND,
                    NanoHTTPD.MIME_PLAINTEXT,
                    "Not found"
                )
            }
        }
        fun main(): String {
            this.server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
            return this.deviceList
        }
    }
}