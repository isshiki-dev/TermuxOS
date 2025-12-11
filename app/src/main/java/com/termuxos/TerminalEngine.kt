package com.termuxos

import android.util.Log

class TerminalEngine {

    interface TerminalCallback {
        fun onOutput(output: String)
    }

    private var callback: TerminalCallback? = null

    fun setCallback(cb: TerminalCallback) {
        this.callback = cb
    }

    fun startSession(command: Array<String>) {
        // In a real implementation, this would start a PTY process
        // For now, we simulate output
        Thread {
            try {
                callback?.onOutput("Starting session: ${command.joinToString(" ")}\n")
                Thread.sleep(500)
                callback?.onOutput("TermuxOS Environment Initialized.\n")
                Thread.sleep(500)
                callback?.onOutput("root@localhost:~# \n")
            } catch (e: Exception) {
                Log.e("TerminalEngine", "Error in session", e)
            }
        }.start()
    }
}
