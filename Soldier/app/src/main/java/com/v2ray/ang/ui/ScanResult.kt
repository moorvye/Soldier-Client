package com.v2ray.ang.ui

data class ScanResult(
    val ip: String,
    val latency: Int,
    val status: Status
)

enum class Status {
    GREEN, YELLOW, RED
}