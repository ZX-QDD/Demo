package com.liulishuo.demo

class Student(
        val sno: String,
        val grade: Int,
        name: String,
        age: Int):Person(name,age),Study{

    override fun readBook() {
        //todo
    }
}

//open class Teacher(val name: String) {
//
//}
//
//class TeacherLi: Person{
//    constructor(name: String,age: Int){
//
//    }
//}

class Student1 (var name: String) {
    // 直接调用主构造器
    constructor(name: String, id: Int) : this(name) {
    }
    //通过上一个次构造器，间接调用主构造器
    constructor(name: String, id: Int, age: Int) : this(name, id) {
    }
}

class User(var name: String) {
}
// 等价于：
class User1(name: String) {
    var name: String = name
}

data class Data(val id:Int,val name: String)

object Singleton{

}