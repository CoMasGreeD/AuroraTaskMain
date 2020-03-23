package com.vladshvyrev.auroratask.Repository.network

data class Data (
    var id : Int?= null,
    var name : String?= null,
    var hero_or_villain: String?= null,
    var marvel_or_dc : String? = null,
    val image : String? =null,
    val image_icon : String? = null,
    var detail :String? = null
)