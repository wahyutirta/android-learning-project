package com.example.mygithubuserfavorite.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ModelSearchUser (
    @Expose
    @SerializedName("items")
    val itemUserModels: ArrayList<ModelSearchUserItem>
)