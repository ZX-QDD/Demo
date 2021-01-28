package com.liulishuo.demo

open class Person constructor(name :String, age :Int){
    var name :String = name
    init {
        println(this.name+age)
    }


//    var age = 0
//
//    fun eat (){
//        println(name+age+"hello")
//    }
//

}

fun main(){
    val person = Person("qdd",21)
}