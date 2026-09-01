package com.v2ray.ang.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SoldierScannerViewModel {

    private val _results = MutableStateFlow<List<ScanResult>>(emptyList())
    val results: StateFlow<List<ScanResult>> = _results.asStateFlow()

    private val _progress = MutableStateFlow(0 to 0)
    val progress: StateFlow<Pair<Int, Int>> = _progress.asStateFlow()

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    fun setResults(list: List<ScanResult>) {
        _results.value = list
    }

    fun addResult(result: ScanResult) {
        val current = _results.value.toMutableList()
        current.add(result)
        _results.value = current
    }

    fun clearResults() {
        _results.value = emptyList()
    }

    fun setProgress(done: Int, total: Int) {
        _progress.value = done to total
    }

    fun setScanning(value: Boolean) {
        _isScanning.value = value
    }
}