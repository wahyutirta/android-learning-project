package com.example.mylocalization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.example.mylocalization.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val pokeCount = 3
        val hello = resources.getString(R.string.hello_world, "Narenda Wicaksono", pokeCount, "Yoza Aprilio")

        binding.tvHello.text = hello

        //teks yang ditampilkan akan sesuai dengan jumlah yang diberikan
        val songCount = 5
        val pluralText = resources.getQuantityString(R.plurals.numberOfSongsAvailable, songCount, songCount)
        binding.tvPlural.text = pluralText

        binding.tvXliff.text = resources.getString(R.string.app_homeurl)


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if (item.itemId == R.id.action_change_settings){
            //pindah intent berdasarkan bahasa
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}