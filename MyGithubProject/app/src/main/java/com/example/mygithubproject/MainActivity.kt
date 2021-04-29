package com.example.mygithubproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubproject.model.UserModel
import com.example.mygithubproject.viewmodel.UserAdapter
import com.example.mygithubproject.viewmodel.ViewModelMainAct
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var listUser: ArrayList<UserModel> = ArrayList()
    private lateinit var adapterList: UserAdapter
    private lateinit var viewModelMain: ViewModelMainAct
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterList = UserAdapter(listUser)
        viewModelMain = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ViewModelMainAct::class.java)
        viewConfig()
        getDataGit()
        searchUsers()
        configMainActViewModel(adapterList)
    }

    private fun viewConfig() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        adapterList.notifyDataSetChanged()
        recyclerView.adapter = adapterList
    }

    private fun getDataGit() {
        viewModelMain.loadApiGit(applicationContext)
        showLoading(true)
    }

    private fun configMainActViewModel(adapter: UserAdapter) {
        viewModelMain.loadUser().observe(this, Observer { listDataUser ->
            if (listDataUser != null) {
                adapter.setData(listDataUser)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            loadingProgress.visibility = View.VISIBLE
        } else {
            loadingProgress.visibility = View.INVISIBLE
        }
    }

    private fun searchUsers() {
        search_user.setOnQueryTextListener( object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotEmpty()!!) {
                    listUser.clear()
                    viewConfig()
                    viewModelMain.searchUser(query, applicationContext)
                    showLoading(true)
                    configMainActViewModel(adapterList)
                } else {
                    return true
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val moveIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(moveIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}