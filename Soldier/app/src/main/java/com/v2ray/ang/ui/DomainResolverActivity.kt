package com.v2ray.ang.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.v2ray.ang.R
import java.net.InetAddress

class DomainResolverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domain_resolver)

        val etDomain = findViewById<EditText>(R.id.et_domain)
        val btnResolve = findViewById<Button>(R.id.btn_resolve)
        val tvResult = findViewById<TextView>(R.id.tv_result)

        // Copy result
        tvResult.setOnClickListener {

            val text = tvResult.text.toString()

            if (text.isNotBlank() &&
                !text.startsWith("Enter") &&
                !text.startsWith("Exception")
            ) {

                val clipboard =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                clipboard.setPrimaryClip(
                    ClipData.newPlainText("IP Addresses", text)
                )

                Toast.makeText(
                    this,
                    "Copied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnResolve.setOnClickListener {

            val domain = etDomain.text.toString().trim()

            if (domain.isEmpty()) {
                tvResult.text = "Enter a domain"
                return@setOnClickListener
            }

            tvResult.text = "Resolving..."

            Thread {

                try {

                    val addresses = InetAddress.getAllByName(domain)

                    val result = StringBuilder()

                    result.append("Domain: ")
                    result.append(domain)
                    result.append("\n\n")

                    addresses.forEachIndexed { index, address ->
                        result.append(index + 1)
                        result.append(". ")
                        result.append(address.hostAddress)
                        result.append("\n")
                    }

                    result.append("\nTap to copy")

                    runOnUiThread {
                        tvResult.text = result.toString()
                    }

                } catch (e: Exception) {

                    e.printStackTrace()

                    runOnUiThread {

                        tvResult.text = """
Exception:
${e.javaClass.name}

Message:
${e.message}

Full:
$e
                        """.trimIndent()

                    }

                }

            }.start()

        }

    }
}