package com.vladshvyrev.auroratask.UI.fragments.ExampleDaggerFragment

import dagger.Module
import dagger.Provides

@Module
class UpgradeModule(private var stones: Example.Stones, private var technology: Example.Technology)
{
    @Provides
    fun getStones(): Example.Stones {
        return stones }
    @Provides
    fun getTechnology(): Example.Technology {
        return technology }

}