package de.fabianrump.composesuperhero.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val testData = MutableLiveData("")

    fun fetchDataFromWebService() {
        testData.value = "Test Data"
    }

    fun onClick() {
        testData.value = "You clicked me"
    }
}