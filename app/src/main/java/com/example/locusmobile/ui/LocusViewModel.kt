package com.example.locusmobile.ui

import androidx.lifecycle.ViewModel

class LocusViewModel : ViewModel() {
    private var _username = ""

    val username: String
        get() = _username
}