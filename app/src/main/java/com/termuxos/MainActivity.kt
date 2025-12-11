package com.termuxos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var statusTextView: TextView
    private lateinit var terminalOutput: TextView
    private lateinit var launchButton: Button

    private lateinit var linuxBootstrap: LinuxBootstrap
    private lateinit var prootManager: ProotManager
    private lateinit var terminalEngine: TerminalEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusTextView = findViewById(R.id.statusTextView)
        terminalOutput = findViewById(R.id.terminalOutput)
        launchButton = findViewById(R.id.launchButton)

        linuxBootstrap = LinuxBootstrap(this)
        prootManager = ProotManager(this)
        terminalEngine = TerminalEngine()

        terminalEngine.setCallback(object : TerminalEngine.TerminalCallback {
            override fun onOutput(output: String) {
                runOnUiThread {
                    terminalOutput.append(output)
                }
            }
        })

        launchButton.setOnClickListener {
            launchTermuxOS()
        }

        checkBootstrap()
    }

    private fun checkBootstrap() {
        if (linuxBootstrap.isBootstrapped()) {
            statusTextView.text = "System Ready."
            launchButton.isEnabled = true
        } else {
            statusTextView.text = getString(R.string.init_linux)
            launchButton.isEnabled = false
            // Simulate background bootstrap
            Thread {
                val success = linuxBootstrap.bootstrap()
                runOnUiThread {
                    if (success) {
                        statusTextView.text = "System Ready."
                        launchButton.isEnabled = true
                    } else {
                        statusTextView.text = "Initialization Failed."
                    }
                }
            }.start()
        }
    }

    private fun launchTermuxOS() {
        terminalOutput.text = ""
        val command = prootManager.getProotCommand("/bin/bash")
        terminalEngine.startSession(command)
    }
}
