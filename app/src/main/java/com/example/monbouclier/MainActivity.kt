package com.example.monbouclier

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScanner = findViewById<Button>(R.id.btnScanner)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnScanner.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val lien = clipboard.primaryClip?.getItemAt(0)?.text.toString()

            val motsDangereux = listOf("bit.ly", "arnaque", "gratuit", "cadeau", "gagné", "urgence", "cliquez")
            val estDangereux = motsDangereux.any { mot -> lien.contains(mot, ignoreCase = true) }

            if (estDangereux) {
                txtResult.text = "⚠️ DANGER ! N'ouvre pas ce lien. C'est une arnaque probable"
                txtResult.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            } else {
                txtResult.text = "✅ Ce lien semble OK"
                txtResult.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            }
        }
    }
}
