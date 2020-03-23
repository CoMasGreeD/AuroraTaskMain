package com.vladshvyrev.auroratask.UI.fragments.DetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.vladshvyrev.auroratask.R
import com.vladshvyrev.auroratask.Repository.network.Data
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment:Fragment() {
    lateinit var viewModel: DetailsViewModel
    companion object {
        private const val ID_KEY = "id"
        fun getInstance(email: String?): DetailsFragment {
            val bundle = Bundle()
            bundle.putString(ID_KEY, email)
            val detailedFr = DetailsFragment()
            detailedFr.arguments = bundle
            return detailedFr
        }
    }
    private lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments!!
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStatus()
    }
    private fun observeStatus() {
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        viewModel.userLiveData.observe(this, observer)
        arguments?.getString("id")?.let {
            viewModel.getUserId(it)
        }
    }

    private val observer = Observer<Data> { response->
        response?.let { result ->
            initViewItems(result)
        }

    }
    private fun initViewItems(data: Data) {
        name_detail?.text = data.name
        hero_or_villain_detail?.text =data.hero_or_villain
        details?.text = data.detail
        marv_dc_detail?.text = data.marvel_or_dc
        Glide.with(this).load(data.image).into(main_image)
        Glide.with(this).load(data.image_icon).into(image_icon)

    }
}