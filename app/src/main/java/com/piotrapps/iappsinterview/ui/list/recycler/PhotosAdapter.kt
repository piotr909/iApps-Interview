package com.piotrapps.iappsinterview.ui.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.piotrapps.iappsinterview.databinding.PhotoCardItemBinding

class PhotosAdapter(
    private val clickListener: (String) -> Unit
) : ListAdapter<PhotoItem, PhotoItemViewHolder>(PhotoItemDiffCallback()) {

    private class PhotoItemDiffCallback : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(
            oldItem: PhotoItem,
            newItem: PhotoItem
        ): Boolean {
            return oldItem.link == newItem.link && oldItem.description == newItem.description && oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(
            oldItem: PhotoItem,
            newItem: PhotoItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val binding =
            PhotoCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoItemViewHolder(binding) {
            clickListener(it)
        }
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}