package com.vladshvyrev.auroratask.UI.fragments.ExampleDaggerFragment

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MyViewModel @Inject constructor(private val repositoryForDagger: RepositoryForDagger) :
    ViewModel() {
    fun getDataViewModel():String {
        val myDaggerName =repositoryForDagger.getData()
        return myDaggerName

    }
}