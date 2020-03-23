package com.vladshvyrev.auroratask.UI.fragments.MainPageFragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vladshvyrev.auroratask.MainActivity
import com.vladshvyrev.auroratask.R
import com.vladshvyrev.auroratask.Repository.network.Data
import com.vladshvyrev.auroratask.UI.fragments.EventClass
import kotlinx.android.synthetic.main.fragment_main_page.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainPageFragment : Fragment()  {
    lateinit var viewModel: MainPageViewModel
    private lateinit var blogAdapter: DataAdapterForMainPage
    lateinit var filteredList: ArrayList<Data>
//
    var data :ArrayList<String>? = null
    @Subscribe(threadMode =ThreadMode.MAIN)
    fun onMessage(event : EventClass)
    {
            data =ArrayList()
            data?.addAll(event.mValue)

    }

    override fun onStart() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }


        super.onStart()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filteredList = ArrayList()
        viewModel = ViewModelProviders.of(this).get(MainPageViewModel::class.java)
        viewModel.userListLiveData.observe(this, observer)
        viewModel.getList()
        initRecyclerView()

        var edit = filter_text
        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        filter_but.setOnClickListener{
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,FilterFragment())
                .addToBackStack("8")
                .commit()
        }
    }

    fun filter(text: String) {
        var buffList = ArrayList<Data>()
        for (item in filteredList) {
            if (item.hero_or_villain!!.toLowerCase().contains(text.toLowerCase())) {
                buffList.add(item)
            }
        }

        blogAdapter.filteredList(buffList)


    }

    override fun onResume() {

        super.onResume()
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun addDataSet(data: List<Data>) {
        blogAdapter.submitList(data)
    }

    private val observer = Observer<List<Data>> { response ->
        filteredList.addAll(response)

        addDataSet(response)
        if(data != null){
        filter(data!![0])}

    }

    private fun initRecyclerView() {
        context?.let {
            recycler_view.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
                blogAdapter = DataAdapterForMainPage({ itemView: Data ->
                    itemViewClicked(itemView)
                })
                adapter = blogAdapter
            }
        }
    }

    private fun itemViewClicked(itemView: Data) {
        (activity as MainActivity).getId(itemView.id.toString())
    }
}