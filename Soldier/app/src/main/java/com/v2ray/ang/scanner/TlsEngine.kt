package com.v2ray.ang.scanner

import java.net.InetSocketAddress
import java.net.Socket
import javax.net.ssl.SNIHostName
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket

object TlsEngine {

    fun test(
        ip: String,
        host: String,
        port: Int = 443,
        timeout: Int = 3000
    ): Boolean {

        return try {

            val socket = Socket()
            socket.connect(InetSocketAddress(ip, port), timeout)

            val context = SSLContext.getInstance("TLS")
            context.init(null, null, null)

            val sslSocket = context.socketFactory.createSocket(
                socket,
                host,
                port,
                true
            ) as SSLSocket

            val params = sslSocket.sslParameters
            params.serverNames = listOf(SNIHostName(host))
            sslSocket.sslParameters = params

            sslSocket.soTimeout = timeout

            sslSocket.startHandshake()

            sslSocket.close()

            true

        } catch (e: Exception) {

            false

        }

    }
}