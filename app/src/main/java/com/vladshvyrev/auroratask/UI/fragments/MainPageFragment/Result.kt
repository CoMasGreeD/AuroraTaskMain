package com.vladshvyrev.auroratask.UI.fragments.MainPageFragment

import com.vladshvyrev.auroratask.Repository.network.Data

data class Result (
    var list: List<Data>?= null,
    var error : Throwable? = null
    )