package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class CountdownService : Service() {

    private var job: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val countdownTime = intent?.getIntExtra("time", 0) ?: 0

        job = CoroutineScope(Dispatchers.Default).launch {
            startCountdown(countdownTime)
        }

        return START_NOT_STICKY
    }

    private suspend fun startCountdown(time: Int) {
        for (i in time downTo 0) {
            Log.d("CountdownService", "Time remaining: $i seconds")
            delay(1000L) // Delay 1 second between each countdown
        }
        stopSelf() // Stop the service after the countdown finishes
    }

    override fun onBind(intent: Intent?): IBinder? {
        // Not used since this is a started service, not a bound service.
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel() // Cancel the coroutine if the service is destroyed
    }
}
