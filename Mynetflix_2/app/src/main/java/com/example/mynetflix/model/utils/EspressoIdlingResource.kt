package com.example.mynetflix.model.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun decrement() {
        idlingResource.decrement()
    }

    fun increment() {
        idlingResource.increment()
    }

}