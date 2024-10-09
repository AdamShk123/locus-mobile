package com.example.locusmobile.data.source

import com.example.locusmobile.data.model.User

object FakeUsersData {
    private val studentOne = Pair("1",User("1","Adam","Shkolnik"))
    private val studentTwo = Pair("2",User("2","Diana","Shea"))
    private val studentThree = Pair("3",User("3","Adeola","Shodiya-Alakija"))
    private val studentFour = Pair("4",User("4","Deepika","Buddhiraju"))

    val users: Map<String,User> = hashMapOf(studentOne, studentTwo, studentThree, studentFour)
}