package com.v2ray.ang.scanner

import com.v2ray.ang.ui.ScanResult
import com.v2ray.ang.ui.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

object ScannerEngine {

    @Volatile
    private var stopRequested = false

    fun stop() {
        stopRequested = true
    }

    suspend fun scan(
        ranges: List<String>,
        domain: String = "",
        port: Int = 443,
        ipPerRange: Int = 50,
        threadCount: Int = 4,
        onResult: (ScanResult) -> Unit
    ) = coroutineScope {

        stopRequested = false

        val jobs = mutableListOf<kotlinx.coroutines.Deferred<Unit>>()

        for (range in ranges) {

            val ips = CidrGenerator.randomIps(range, ipPerRange)

            ips.chunked(threadCount).forEach { chunk ->

                jobs += async(Dispatchers.IO) {

                    chunk.forEach { ip ->

                        if (stopRequested) return@async

                        // Ping
                        val latency = PingEngine.ping(ip)

                        if (stopRequested) return@async

                        // TLS Check
                        var tlsOk = true

                        if (domain.isNotBlank()) {
                            tlsOk = TlsEngine.test(
                                ip = ip,
                                host = domain,
                                port = port
                            )
                        }

                        val status = when {

                            !tlsOk -> Status.RED

                            latency <= 150 -> Status.GREEN

                            latency <= 250 -> Status.YELLOW

                            else -> Status.RED
                        }

                        onResult(
                            ScanResult(
                                ip = ip,
                                latency = latency,
                                status = status
                            )
                        )

                        delay(40)

                    }

                }

            }

        }

        jobs.awaitAll()

    }
}