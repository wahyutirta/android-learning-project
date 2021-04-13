package com.example.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btn_start)
        val tvStatus = findViewById<TextView>(R.id.tv_status)

        /*
        //executor
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        using executor
        btnStart.setOnClickListener{
            try {
                //simulate process in background thread
                //Executor
                for (i in 0..10){
                    Thread.sleep(500)
                    val percentage = i * 10
                    handler.post{
                        if (percentage == 100){
                            tvStatus.setText(R.string.task_completed)
                        } else {
                            tvStatus.text = String.format(getString(R.string.compressing),percentage)
                        }
                    }

                }
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
        */
        //using croutine
        btnStart.setOnClickListener{
            //scope untuk menjalankan coroutine
            lifecycleScope.launch(Dispatchers.Default){
                for (i in 0..10){
                    delay(500)
                    val percentage = i*10
                    //pindah thread
                    withContext(Dispatchers.Main){
                        if (percentage == 100){
                            tvStatus.setText(R.string.task_completed)
                        }else {
                            tvStatus.text = String.format(getString(R.string.compressing), percentage)
                        }
                    }
                }
            }
        }
    }
}