package com.example.nova

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nova.databinding.DataItemListBinding
import com.example.nova.pojo.Data

class DAdapter: RecyclerView.Adapter<DAdapter.DataHolder>()  {

    private var listDataItem = listOf<Data>()
    var selected = MutableLiveData<Data>()

    fun update(list: List<Data>){
        listDataItem = list
        notifyDataSetChanged()
    }

    inner class DataHolder(private val binding: DataItemListBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(data: Data){
            Glide.with(binding.ivData)
                .load(data.imgSrc)
                .centerCrop()
                .into(binding.ivData)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selected.value =listDataItem[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder{
        return DataHolder(DataItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = listDataItem[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int= listDataItem.size
}