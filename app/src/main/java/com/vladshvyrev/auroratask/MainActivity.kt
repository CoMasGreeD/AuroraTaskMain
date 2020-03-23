package com.vladshvyrev.auroratask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladshvyrev.auroratask.Repository.network.DataForFilter
import com.vladshvyrev.auroratask.UI.fragments.DetailsFragment.DetailsFragment
import com.vladshvyrev.auroratask.UI.fragments.MainPageFragment.FilterFragment
import com.vladshvyrev.auroratask.UI.fragments.MainPageFragment.MainPageFragment

class MainActivity : AppCompatActivity()  {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,MainPageFragment())
            .commit()


    }

    fun getFilters(data : DataForFilter)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,FilterFragment.getInstance(data))
            .addToBackStack("8")
            .commit()
    }
    fun getId(id: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment.getInstance(id))
            .addToBackStack("6")
            .commit()
    }
}
