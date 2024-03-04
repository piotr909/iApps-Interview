package com.piotrapps.iappsinterview.ui.list.recycler

import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.piotrapps.iappsinterview.databinding.PhotoCardItemBinding

class PhotoItemViewHolder(
    private val binding: PhotoCardItemBinding,
    private val onItemClick: (link: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photoItem: PhotoItem) = with(binding) {
        description.text =
            HtmlCompat.fromHtml(photoItem.description, HtmlCompat.FROM_HTML_MODE_COMPACT)

        Glide
            .with(root)
            .load(photoItem.imageUrl)
            .into(image.apply {
                layout(0, 0, 0, 0)
            })

        card.setOnClickListener {
            onItemClick(photoItem.link)
        }
    }
}