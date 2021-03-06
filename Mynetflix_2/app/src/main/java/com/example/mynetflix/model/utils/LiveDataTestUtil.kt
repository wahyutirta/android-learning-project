package com.example.mynetflix.model.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {

    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(liveData: LiveData<T>): T {
        val latch = CountDownLatch(1)
        val data = arrayOfNulls<Any>(1)


        try {
            latch.await(2, TimeUnit.SECONDS)
            val observer = object : Observer<T> {
                override fun onChanged(o: T) {
                    data[0] = o
                    latch.countDown()
                    liveData.removeObserver(this)
                }
            }

            liveData.observeForever(observer)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return data[0] as T

    }

}