package com.liulishuo.demo

import android.util.MutableDouble
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(coutsave: Int): ViewModel(){

//    val count = MutableLiveData<Int>()
private val _count = MutableLiveData<Int>()

    val count: LiveData<Int>
        get() =_count

    init {
        _count.value =coutsave
    }

    fun plusOne(){
        val add=_count.value ?: 0
        _count.value =add+1
    }

    fun clear(){
        _count.value=0
    }
}