package com.example.githubsubmission1.helper

import android.content.Context
import android.util.Log
import com.example.githubsubmission1.model.UserModel

import com.example.githubsubmission1.R
import org.json.JSONObject

@Suppress("UNCHECKED_CAST")
class UserModelHelper(val context:Context) {
    companion object {

    fun <ArrayList> getVersionsList(): ArrayList {

        val userList = ArrayList<UserModel>()

            userList.add(UserModel("JakeWharton", "Jake Wharton", R.drawable.user1, "56995","12","Google, Inc.","Pittsburgh, PA, USA","102"))
            userList.add(UserModel("amitshekhariitbhu","AMIT SHEKHAR", R.drawable.user2,"5153","2","MindOrksOpenSource","New Delhi, India","37"))
            userList.add(UserModel("romainguy","Romain Guy", R.drawable.user3,"7972","0","Google","California","9"))
            userList.add(UserModel("chrisbanes","Chris Banes", R.drawable.user4,"14725","1","Google Working On Android","Sydney, Australia","30"))
            userList.add(UserModel("tipsy","David", R.drawable.user5,"788","0","Working Group Two","Trondheim, Norway","56"))
            userList.add(UserModel("ravi8x","Ravi Tamada", R.drawable.user6,"18628","3","AndroidHive|Droid5","India","28"))
            userList.add(UserModel("jasoet","Deny Prasetyo", R.drawable.user7,"277","39","Gojek-engineering","Kotagede, Yogyakarta, Indonesia","44"))
            userList.add(UserModel("budioktaviyan","Budi Oktaviyan", R.drawable.user8,"178","23","KotlinID","Jakarta, Indonesia","110"))
            userList.add(UserModel("hendisantika","Hendi Santika", R.drawable.user9,"428","61","JVMDeveloperID KotlinID IDDevOps","Bojongsoang - Bandung Jawa Barat","1064"))
            userList.add(UserModel("sidiqpermana","Sidiq Permana", R.drawable.user10,"465","10","Nusantara Beta Studio","Jakarta Indonesia","65"))
            return userList as ArrayList
        }

    }
}
