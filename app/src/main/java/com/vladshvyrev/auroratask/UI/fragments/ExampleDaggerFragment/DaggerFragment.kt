package com.vladshvyrev.auroratask.UI.fragments.ExampleDaggerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vladshvyrev.auroratask.R
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.fragment_dagger.*
import javax.inject.Inject

class DaggerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dagger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var stones = Example.Stones()
        var tech = Example.Technology()
        var component: BattleComponent = DaggerDaggerFragment_BattleComponent.builder().upgradeModule(UpgradeModule(stones,tech)).build()
        var battle: Battle = component.getBattle()
        stones = component.getStones()
        tech = component.getTechnology()
        prepare.setOnClickListener {
            Toast.makeText(context, battle.prepare(), Toast.LENGTH_LONG).show()
            Toast.makeText(context, tech.getCostume(), Toast.LENGTH_LONG).show()
            Toast.makeText(context, stones.getPowerStone(), Toast.LENGTH_LONG).show()
        }
        report.setOnClickListener {
            Toast.makeText(context, battle.report(), Toast.LENGTH_LONG).show()

        }

    }


    interface characters {
        fun prepareForBattle(): String
        fun reportAfterBattle(): String
    }


    @Component(modules = [UpgradeModule::class])
    interface BattleComponent {
        fun getBattle(): Battle
        fun getStones(): Example.Stones
        fun getTechnology(): Example.Technology
    }

    class Heroes @Inject constructor() : characters {

        override fun prepareForBattle(): String {
            return "Preparing, Calling all Allies Heroes"
        }

        override fun reportAfterBattle(): String {
            return "Heroes Are Won!"
        }
    }

    class Villains @Inject constructor() : characters {

        override fun prepareForBattle(): String {
            return "Preparing, Calling all Allies Villains"
        }

        override fun reportAfterBattle(): String {
            return "Villains Are Defeat("
        }
    }

    class Battle @Inject constructor(private var heroes: Heroes, private var villains: Villains) {

        fun prepare(): String {
            val strHeroPrepare = heroes.prepareForBattle()
            val strVillainPrepare = villains.prepareForBattle()
            return "$strHeroPrepare and $strVillainPrepare"
        }

        fun report(): String {
            val strHeroReport = heroes.reportAfterBattle()
            val strVillainReport = villains.reportAfterBattle()
            return "$strHeroReport and $strVillainReport"
        }
    }





}