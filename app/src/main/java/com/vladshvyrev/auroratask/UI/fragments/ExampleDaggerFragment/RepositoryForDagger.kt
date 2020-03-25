package com.vladshvyrev.auroratask.UI.fragments.ExampleDaggerFragment

import javax.inject.Inject

class RepositoryForDagger @Inject constructor() {
    fun getData():String
    {
        return "This my ViewModel"
    }
}