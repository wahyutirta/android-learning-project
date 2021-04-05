package com.example.projectsubmissionone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class detail_goods : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_goods)
        val actionbar = supportActionBar
        actionbar!!.title = "Detail Barang"
        actionbar.setDisplayHomeAsUpEnabled(true)


        val tvName: TextView = findViewById(R.id.tv_set_name)
        val tvDetail: TextView = findViewById(R.id.tv_set_detail)
        val tvImg: ImageView = findViewById(R.id.tv_set_image)

        val tImage = intent.getIntExtra(EXTRA_PHOTO,0)

        Glide.with(this)
            .load(tImage)
            .apply(RequestOptions())
            .into(tvImg)

        //take data from compact
        tvName.text = intent.getStringExtra(EXTRA_NAME)
        tvDetail.text = intent.getStringExtra(EXTRA_DETAIL)
    }
    //data in object
    companion object {

        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_PHOTO = "extra_photo"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}