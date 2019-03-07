package com.aimicor.pulselive.viewmodel.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aimicor.pulselive.ItemSummary
import com.aimicor.pulselive.viewmodel.R
import com.aimicor.pulselive.viewmodel.databinding.MainListItemBinding
import com.aimicor.pulselive.viewmodel.inflateBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainAdapter @Inject constructor() : ListAdapter<ItemSummary, MainAdapter.ViewHolder>(DiffCallback()) {

    private val clickSubject = PublishSubject.create<Int>()
    internal val clickEvent: Observable<Int> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflateBinding(R.layout.main_list_item, parent))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = getItem(position)
        holder.binding.clickSubject = clickSubject
    }

    inner class ViewHolder(val binding: MainListItemBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<ItemSummary>() {
        override fun areItemsTheSame(oldItem: ItemSummary, newItem: ItemSummary) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ItemSummary, newItem: ItemSummary) = oldItem.title == newItem.title
    }
}
