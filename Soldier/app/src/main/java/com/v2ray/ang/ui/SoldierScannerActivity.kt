package com.v2ray.ang.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v2ray.ang.R
import com.v2ray.ang.ui.ScanResult
import com.v2ray.ang.ui.Status
import androidx.lifecycle.lifecycleScope
import com.v2ray.ang.scanner.ScannerEngine
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

class SoldierScannerActivity : AppCompatActivity() {
    
    private lateinit var spinnerCdn: Spinner
    private lateinit var switchCert: Switch
    private lateinit var etCustomRange: EditText
    private lateinit var etDomain: EditText
    private lateinit var etPort: EditText
    private lateinit var btnScan: Button
    private lateinit var btnCopyGreen: Button
    private lateinit var btnCopyTop16: Button
    private lateinit var btnCopyAll: Button
    private lateinit var btnStop: Button
    private lateinit var btnClear: Button
    private lateinit var rvResults: RecyclerView
    private lateinit var adapter: ResultAdapter
    private var isScanning = false
    private var scanJob: Job? = null
    
    private val cdnTypes = arrayOf(
    "Cloudflare",
    "Akamai",
    "Fastly",
    "Bunny",
    "EdgeCenter",
    "Gcore",
    "CDN77",
    "Edgio",
    "Imperva",
    "Azure Front Door",
    "Netlify",
    "Google Cloud CDN",
    "Vercel"
)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soldier_scanner)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        initViews()
        setupSpinner()
    }
    
    private fun initViews() {
        spinnerCdn = findViewById(R.id.spinnerCdn)
        switchCert = findViewById(R.id.switchCert)
        etCustomRange = findViewById(R.id.etCustomRange)
        etDomain = findViewById(R.id.etDomain)
        etPort = findViewById(R.id.etPort)
        btnScan = findViewById(R.id.btnScan)
        btnCopyGreen = findViewById(R.id.btnCopyGreen)
        btnCopyTop16 = findViewById(R.id.btnCopyTop16)
        btnCopyAll = findViewById(R.id.btnCopyAll)
        btnStop = findViewById(R.id.btnStop)
        btnClear = findViewById(R.id.btnClear)
        rvResults = findViewById(R.id.rvResults)
        
        rvResults.layoutManager = LinearLayoutManager(this)
        adapter = ResultAdapter()
rvResults.adapter = adapter

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
    finish()
}
        
        btnScan.setOnClickListener { startScan() }
        btnCopyGreen.setOnClickListener { copyResults(Status.GREEN) }
        btnCopyTop16.setOnClickListener { copyTopResults(16) }
        btnCopyAll.setOnClickListener { copyAllResults() }
        btnStop.setOnClickListener { stopScan() }
        btnClear.setOnClickListener { clearResults() }
    }
    
    private fun setupSpinner() {

    val adapter = ArrayAdapter(
        this,
        android.R.layout.simple_spinner_item,
        cdnTypes
    )

    adapter.setDropDownViewResource(
        android.R.layout.simple_spinner_dropdown_item
    )

    spinnerCdn.adapter = adapter
}
    
    private fun startScan() {

    if (isScanning) return

    adapter.clearResults()

    val cdnType = spinnerCdn.selectedItem.toString()
    val customRange = etCustomRange.text.toString().trim()
    val domain = etDomain.text.toString().trim()
    val port = etPort.text.toString().toIntOrNull() ?: 443

val rangesToScan: List<String> =
    if (customRange.isNotEmpty()) {

        customRange
            .split("\n")
            .map { it.trim() }
            .filter { it.isNotEmpty() }

    } else {

        getDefaultRangesForCdn(cdnType)
            .shuffled()
            .take(4)

    }
    val checkCert = switchCert.isChecked

    isScanning = true

    btnScan.isEnabled = false
    btnStop.isEnabled = true

    Toast.makeText(
    this,
    "Scan started: $cdnType | ${rangesToScan.size} ranges",
    Toast.LENGTH_SHORT
).show()

scanJob = lifecycleScope.launch {

    ScannerEngine.scan(
        ranges = rangesToScan,
        domain = domain,
        port = port,
        ipPerRange = 150,
        threadCount = 6
    ) { result ->

        if (!isScanning) return@scan

        runOnUiThread {

            if (isScanning) {
                adapter.addResult(result)
            }

        }

    }

    runOnUiThread {
        isScanning = false
        btnScan.isEnabled = true
        btnStop.isEnabled = false
    }
  }
}

private fun getDefaultRangesForCdn(cdnType: String): List<String> {
    return when (cdnType) {
     
        else -> emptyList()
   } 
}

private fun pingIp(ip: String): Int {
    return try {
        val process = Runtime.getRuntime().exec("/system/bin/ping -c 1 -W 1 $ip")
        val exit = process.waitFor()
        if (exit == 0) (50 + Math.random() * 200).toInt() else 999
    } catch (e: Exception) {
        999
    }
}

private fun generateIpsFromRange range: String: List<String> {
    return listOf(range.split("/")[0])
    
    private fun copyResults(status: Status) {

    val text = adapter.getResults()
        .filter { it.status == status }
        .joinToString("\n") { it.ip }

    copyToClipboard(text)

    Toast.makeText(this, "Green copied", Toast.LENGTH_SHORT).show()
}
    
    private fun copyTopResults(count: Int) {

    val text = adapter.getResults()

        .sortedBy { it.latency }

        .take(count)

        .joinToString("\n") { it.ip }

    copyToClipboard(text)

    Toast.makeText(this, "Top $count copied", Toast.LENGTH_SHORT).show()
}
    
    private fun copyAllResults() {

    val text = adapter.getResults()
        .joinToString("\n") { it.ip }

    copyToClipboard(text)

    Toast.makeText(this, "All copied", Toast.LENGTH_SHORT).show()
}
    
    private fun stopScan() {

    isScanning = false

    ScannerEngine.stop()

    scanJob?.cancel()

    btnScan.isEnabled = true
    btnStop.isEnabled = false

    Toast.makeText(this, "Scan stopped", Toast.LENGTH_SHORT).show()
}
    
    private fun clearResults() {

    adapter.clearResults()

}
    
    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Soldier Scanner", text)
        clipboard.setPrimaryClip(clip)
    }

    override fun onSupportNavigateUp(): Boolean {
    onBackPressedDispatcher.onBackPressed()
    return true
   }
 }
 