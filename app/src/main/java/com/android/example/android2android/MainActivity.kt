package com.android.example.android2android

import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.format.Formatter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var connectedDevices: String
    lateinit var txtIP: TextView
    lateinit var txtDevices: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.connectedDevices = NanoHttpd.main()
        txtIP = findViewById(R.id.txtIP)
        txtDevices = findViewById(R.id.txtDevices)
    }

    override fun onStart() {
        super.onStart()
        val wm = getSystemService(WIFI_SERVICE) as WifiManager
        val ip: String = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
        txtIP.text = ip
        Handler(Looper.getMainLooper()).postDelayed(
            { txtDevices.text = NanoHttpd.deviceList },
            300
        )
    }
}