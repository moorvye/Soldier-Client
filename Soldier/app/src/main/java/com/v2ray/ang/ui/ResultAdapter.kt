package com.v2ray.ang.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.v2ray.ang.R

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private val results = mutableListOf<ScanResult>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvIp: TextView = view.findViewById(R.id.tvIp)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvLatency: TextView = view.findViewById(R.id.tvLatency)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scan_result, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = results[position]

        holder.tvIp.text = item.ip
        holder.tvLatency.text = "${item.latency} ms"

        when (item.status) {

            Status.GREEN -> {
                holder.tvStatus.text = "GOOD"
                holder.tvStatus.setTextColor(Color.GREEN)
            }

            Status.YELLOW -> {
                holder.tvStatus.text = "MEDIUM"
                holder.tvStatus.setTextColor(Color.YELLOW)
            }

            Status.RED -> {
                holder.tvStatus.text = "BAD"
                holder.tvStatus.setTextColor(Color.RED)
            }
        }

        holder.tvIp.setOnClickListener {

            val clipboard = holder.itemView.context
                .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            clipboard.setPrimaryClip(
                ClipData.newPlainText("IP", item.ip)
            )

            Toast.makeText(
                holder.itemView.context,
                "Copied: ${item.ip}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun addResult(result: ScanResult) {
        results.add(result)
        notifyItemInserted(results.lastIndex)
    }

    fun clearResults() {
        results.clear()
        notifyDataSetChanged()
    }

    fun getResults(): List<ScanResult> {
        return results
    }
}