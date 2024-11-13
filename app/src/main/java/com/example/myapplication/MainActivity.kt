package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTime = findViewById<EditText>(R.id.editTextTime)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val time = editTextTime.text.toString().toIntOrNull()
            if (time != null && time > 0) {
                val intent = Intent(this, CountdownService::class.java)
                intent.putExtra("time", time)
                startService(intent)
            } else {
                // Handle invalid input (e.g., show a toast message)
            }
        }
    }
}