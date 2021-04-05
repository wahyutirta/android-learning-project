package com.example.projectsubmissionone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvGoods: RecyclerView
    private var list: ArrayList<Goods> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title= "Home"
        rvGoods = findViewById(R.id.rv_goods)
        rvGoods.setHasFixedSize(true)
        list.addAll(GoodsObject.listData)
        showRecyclerList()
    }
    private fun showRecyclerList(){
        rvGoods.layoutManager = LinearLayoutManager(this)
        val listGoodsAdapter = ListGoodsAdapter(list)
        rvGoods.adapter = listGoodsAdapter
        listGoodsAdapter.setOnItemClickCallback(object : ListGoodsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Goods) {
                showSelectedGoods(data)
            }
        })
    }
    private fun showAboutPage() {
        val aboutpage = Intent(this@MainActivity, AboutPage::class.java)
        startActivity(aboutpage)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        modeKage(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectedGoods(goods: Goods) {
        Toast.makeText(this, "Details: " + goods.name, Toast.LENGTH_SHORT).show()
    }

    fun modeKage(selectedMode: Int) {
        when (selectedMode ) {
            R.id.action_about -> {
                showAboutPage()
            }
        }
    }
}