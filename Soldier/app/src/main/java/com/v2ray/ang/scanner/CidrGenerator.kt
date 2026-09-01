package com.v2ray.ang.scanner

import kotlin.random.Random

object CidrGenerator {

    fun randomIps(cidr: String, count: Int): List<String> {

        val parts = cidr.split("/")

        if (parts.size != 2) return emptyList()

        val ip = parts[0]
        val prefix = parts[1].toInt()

        val ipParts = ip.split(".").map { it.toInt() }

        if (ipParts.size != 4) return emptyList()

        val base =
            (ipParts[0] shl 24) or
            (ipParts[1] shl 16) or
            (ipParts[2] shl 8) or
            ipParts[3]

        val hostBits = 32 - prefix

        val maxHosts = 1 shl hostBits

        val list = mutableListOf<String>()

        repeat(count) {

            val host = Random.nextInt(maxHosts)

            val value = base + host

            val a = value ushr 24 and 0xff
            val b = value ushr 16 and 0xff
            val c = value ushr 8 and 0xff
            val d = value and 0xff

            list.add("$a.$b.$c.$d")
        }

        return list
    }
}