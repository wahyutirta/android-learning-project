package com.example.mytestingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnSetValue: Button
    private lateinit var tvText: TextView

    private var names = ArrayList<String>()
    
    private lateinit var imgPreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSetValue = findViewById(R.id.btn_set_value)
        tvText = findViewById(R.id.tv_text)

        btnSetValue.setOnClickListener(this)

        names.add("Narenda Wicaksono")
        names.add("Kevin")
        names.add("Yoza")
        
        imgPreview = findViewById(R.id.img_preview)
        //withoutglade
        //imgPreview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fronalpstock_big))
        //with glade
        Glide.with(this).load(R.drawable.fronalpstock_big).into(imgPreview)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_set_value) {
            Log.d("MainActivity", names.toString())
            val name = StringBuilder()
            for (i in 0..3) {
                name.append(names[i]).append("\n")
            }
            tvText.text = name.toString()
        }
    }
}