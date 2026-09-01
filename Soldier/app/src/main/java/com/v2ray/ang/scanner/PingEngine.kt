package com.v2ray.ang.scanner

import java.io.BufferedReader
import java.io.InputStreamReader

object PingEngine {

    fun ping(ip: String): Int {

        return try {

            val process = Runtime.getRuntime().exec(
                arrayOf(
                    "/system/bin/ping",
                    "-c",
                    "1",
                    "-W",
                    "1",
                    ip
                )
            )

            val reader =
                BufferedReader(
                    InputStreamReader(process.inputStream)
                )

            val output = reader.readText()

            process.waitFor()

            val regex = Regex("""time=([0-9.]+)""")

            val match = regex.find(output)

            if (match != null) {

                match.groupValues[1].toFloat().toInt()

            } else {

                999

            }

        } catch (e: Exception) {

            999

        }

    }

}