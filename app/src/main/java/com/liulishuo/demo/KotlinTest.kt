package com.liulishuo.demo

import android.graphics.Color
import android.view.View

class KotlinTest {

    lateinit var string: String
//    lateinit var int: Char
    lateinit var array1: IntArray
    var array :IntArray = intArrayOf(1,2,3)


//    inline fun test01(noinline param: () -> Unit) {
//
//        test01No(param)
//
//    }
//
//    fun test01No(param:()->Unit) {
//        param()
//    }
//
//
//    fun test02() {
//        println("this02")
//    }
//
//    fun test03() {
//        test01(::test02)
//    }
//
//
//    fun test04() {
//        test01No(::test02)
//    }
}

    fun main() {

        var intArray = IntArray(50)
        var count=0
        for (i in 1..10){
            for (j in i+1..10){
                if (intArray[i]+intArray[j]==0)
                    count++
            }
        }
        println(count)
    }

    fun test(param1: Int, param2: Int): Int {
        return 0
    }
//当一个函数中只有一行代码的时，kotlin允许我们不必编写函数体，可以将唯一的一行代码写在函数定义的尾部，中间用等号连接即可。
    fun test1(param1: Int, param2: Int): Int = 0
//因为使用的等号连接，根据类型推导，也不用显示的声明返回值
    fun test2(param1: Int, param2: Int) = 0
//
