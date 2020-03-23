package com.vladshvyrev.auroratask.UI.fragments.MainPageFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vladshvyrev.auroratask.R
import com.vladshvyrev.auroratask.Repository.network.Data
import kotlinx.android.synthetic.main.item_data_for_main_page.view.*

class DataAdapterForMainPage(val clickListener: (Data) -> Unit): RecyclerView.Adapter<DataAdapterForMainPage.BlogViewHolder>() {
    private var items = mutableListOf<Data>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_data_for_main_page, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(items[position],clickListener)

    }
    fun submitList(blogList: List<Data>){
        items.addAll(blogList)
        notifyDataSetChanged()
    }
    fun filteredList(blogList: ArrayList<Data>)
    {
        items = blogList
        notifyDataSetChanged()
    }
    class BlogViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private var textId: Int? = 0
        private val textName = itemView.name
        private val textHerOrVil = itemView.hero_or_villain
        private val imageHero = itemView.img
        private  val marvDc = itemView.marv_dc
        private val imageIcon = itemView.img2

        fun bind(data: Data,clickListener: (Data) -> Unit) {
            itemView.setOnClickListener{
                clickListener(data)
            }
            textName?.text = data.name
            textHerOrVil?.text = data.hero_or_villain
            textId=data.id
            marvDc?.text = data.marvel_or_dc
            Glide.with(this.itemView).load(data.image).into(imageHero)
            Glide.with(this.itemView).load(data.image_icon).into(imageIcon)
        }
    }
}