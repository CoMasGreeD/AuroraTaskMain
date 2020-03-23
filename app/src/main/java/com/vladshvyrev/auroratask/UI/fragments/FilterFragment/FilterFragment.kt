package com.vladshvyrev.auroratask.UI.fragments.MainPageFragment

import android.content.Context
import android.os.Bundle
import android.view.*

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vladshvyrev.auroratask.MainActivity
import com.vladshvyrev.auroratask.R
import com.vladshvyrev.auroratask.Repository.network.Data
import com.vladshvyrev.auroratask.UI.fragments.FilterFragment.FilterViewModel
import com.vladshvyrev.auroratask.UI.fragments.EventClass
import kotlinx.android.synthetic.main.fragment_filter.*
import org.greenrobot.eventbus.EventBus



class FilterFragment : Fragment() {
    lateinit var viewModel: FilterViewModel
    private lateinit var blogAdapter: DataAdapterForFilter
    lateinit var filteredList: ArrayList<Data>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filteredList = ArrayList()
        viewModel = ViewModelProviders.of(this).get(FilterViewModel::class.java)
        viewModel.characterListLiveData.observe(this, observer)
        //initRecyclerView()
        Glide.with(this).load(R.drawable.backmarv).into(fon_image)
        show_list.setOnClickListener {
            chooseCategory()
            }

    }

    private fun chooseCategory() {
        when{
            hero_check.isChecked && !marvel_check.isChecked && !dc_check.isChecked ->
            {
                var input  = ArrayList<String>()
                input.add(hero_check.text.toString())
                sendFilters(input)
                activity!!.supportFragmentManager.popBackStack()
            }
            hero_check.isChecked && marvel_check.isChecked  ->
            {
                var input  = ArrayList<String>()
                input.add(hero_check.text.toString())
                input.add(marvel_check.text.toString())
                sendFilters(input)
                activity!!.supportFragmentManager.popBackStack()
            }
            hero_check.isChecked && dc_check.isChecked  ->
            {
                var input  = ArrayList<String>()
                input.add(hero_check.text.toString())
                input.add(dc_check.text.toString())
                sendFilters(input)
                activity!!.supportFragmentManager.popBackStack()
            }
            villain_check.isChecked && !marvel_check.isChecked && !dc_check.isChecked ->
            {
                var input  = ArrayList<String>()
                input.add(villain_check.text.toString())
                sendFilters(input)
                activity!!.supportFragmentManager.popBackStack()
            }
            villain_check.isChecked && marvel_check.isChecked  ->
            {
                var input  = ArrayList<String>()
                input.add(villain_check.text.toString())
                input.add(marvel_check.text.toString())
                sendFilters(input)
                activity!!.supportFragmentManager.popBackStack()
            }
            villain_check.isChecked && dc_check.isChecked  ->
            {
                var input  = ArrayList<String>()
                input.add(villain_check.text.toString())
                input.add(dc_check.text.toString())
                sendFilters(input)
                activity!!.supportFragmentManager.popBackStack()
            }
            marvel_check.isChecked && !hero_check.isChecked && !villain_check.isChecked->
            {
                var input  = ArrayList<String>()
                input.add(marvel_check.text.toString())
                sendFilters(input)
                activity!!.supportFragmentManager.popBackStack()
            }
            dc_check.isChecked && !hero_check.isChecked && !villain_check.isChecked ->
            {
                var input  = ArrayList<String>()
                input.add(dc_check.text.toString())
                sendFilters(input)
                activity!!.supportFragmentManager.popBackStack()
            }
        }
    }

    private fun sendFilters(input :ArrayList<String>) {
        EventBus.getDefault().post(EventClass(input))
    }

    override fun onResume() {
        viewModel.getList()
        allCheck()
        super.onResume()

    }

    private fun allCheck() {
        hero_check.setOnCheckedChangeListener { compoundButton, b ->
            if(!hero_check.isChecked) {
                villain_check.isEnabled = true
                when {
                    marvel_check.isChecked -> show_list.text = countMarvel().toString()
                    dc_check.isChecked -> show_list.text = countDC().toString()
                    else -> show_list.text = filteredList.size.toString()
                }

            }
            if (hero_check.isChecked) {
                villain_check.isEnabled = false
                if (marvel_check.isChecked) {
                    show_list.text = countHeroWithMarvel().toString()
                } else {
                    if (dc_check.isChecked) {
                        show_list.text = countHeroWithDC().toString()
                    } else {
                        show_list.text = countHero().toString()
                    }
                }
            }
        }
        villain_check.setOnCheckedChangeListener { compoundButton, b ->
            if(!villain_check.isChecked) {
                hero_check.isEnabled = true
                when {
                    marvel_check.isChecked -> show_list.text = countMarvel().toString()
                    dc_check.isChecked -> show_list.text = countDC().toString()
                    else -> show_list.text = filteredList.size.toString()
                }

            }
            if (villain_check.isChecked) {
                hero_check.isEnabled = false
                if (marvel_check.isChecked) {
                    show_list.text = countVillainWithMarvel().toString()
                } else {
                    if (dc_check.isChecked) {
                        show_list.text = countVillainWithDC().toString()
                    } else {
                        show_list.text = countVillain().toString()
                    }
                }
            }
        }
        marvel_check.setOnCheckedChangeListener { compoundButton, b ->
            if(!marvel_check.isChecked) {
                dc_check.isEnabled = true
                when {
                    hero_check.isChecked -> show_list.text = countHero().toString()
                    villain_check.isChecked -> show_list.text = countVillain().toString()
                    else -> show_list.text = filteredList.size.toString()
                }

            }
            if (marvel_check.isChecked) {
                dc_check.isEnabled = false
                if (hero_check.isChecked) {
                    show_list.text = countHeroWithMarvel().toString()
                } else {
                    if (villain_check.isChecked) {
                        show_list.text = countVillainWithMarvel().toString()
                    } else {
                        show_list.text = countMarvel().toString()
                    }
                }
            }
        }
        dc_check.setOnCheckedChangeListener { compoundButton, b ->
            if(!dc_check.isChecked) {
                marvel_check.isEnabled = true
                when {
                    hero_check.isChecked -> show_list.text = countHero().toString()
                    villain_check.isChecked -> show_list.text = countVillain().toString()
                    else -> show_list.text = filteredList.size.toString()
                }

            }
            if(dc_check.isChecked) {
                marvel_check.isEnabled = false
                if (hero_check.isChecked) {
                    show_list.text = countHeroWithDC().toString()
                } else {
                    if (villain_check.isChecked) {
                        show_list.text = countVillainWithDC().toString()
                    } else {
                        show_list.text = countDC().toString()
                    }
                }
            }
        }
    }

    private fun addDataSet(data: List<Data>) {
        blogAdapter.submitList(data)
    }

    private val observer = Observer<List<Data>> { response ->
        filteredList.addAll(response)
//        addDataSet(response)

    }

    private fun initRecyclerView() {
        context?.let {
            filter_list.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
                blogAdapter = DataAdapterForFilter { itemView: Data ->
                    itemViewClicked(itemView)
                }
                adapter = blogAdapter
            }
        }
    }

    private fun itemViewClicked(itemView: Data) {
        (activity as MainActivity).getId(itemView.id.toString())
    }

    private fun countHero(): Int {
        var count = 0
        for (item in filteredList) {
            if (item.hero_or_villain!!.toLowerCase().contains("hero")) {
                count++
            }
        }
        return count
    }

    private fun countHeroWithMarvel(): Int {
        var count = 0
        for (item in filteredList) {
            if (item.hero_or_villain!!.toLowerCase().contains("hero") && item.marvel_or_dc!!.toLowerCase().contains(
                    "marvel"
                )
            ) {
                count++
            }
        }
        return count
    }

    private fun countHeroWithDC(): Int {
        var count = 0
        for (item in filteredList) {
            if (item.hero_or_villain!!.toLowerCase().contains("hero") && item.marvel_or_dc!!.toLowerCase().contains(
                    "dc"
                )
            ) {
                count++
            }
        }
        return count
    }

    private fun countVillain(): Int {
        var count = 0
        for (item in filteredList) {
            if (item.hero_or_villain!!.toLowerCase().contains("villain")) {
                count++
            }
        }
        return count
    }

    private fun countVillainWithMarvel(): Int {
        var count = 0
        for (item in filteredList) {
            if (item.hero_or_villain!!.toLowerCase().contains("villain") && item.marvel_or_dc!!.toLowerCase().contains(
                    "marvel"
                )
            ) {
                count++
            }
        }
        return count
    }

    private fun countVillainWithDC(): Int {
        var count = 0
        for (item in filteredList) {
            if (item.hero_or_villain!!.toLowerCase().contains("villain") && item.marvel_or_dc!!.toLowerCase().contains(
                    "dc"
                )
            ) {
                count++
            }
        }
        return count
    }

    private fun countMarvel(): Int {
        var count = 0
        for (item in filteredList) {
            if (item.marvel_or_dc!!.toLowerCase().contains("marvel")) {
                count++
            }
        }
        return count
    }

    private fun countDC(): Int {
        var count = 0
        for (item in filteredList) {
            if (item.marvel_or_dc!!.toLowerCase().contains("dc")) {
                count++
            }
        }
        return count
    }
}