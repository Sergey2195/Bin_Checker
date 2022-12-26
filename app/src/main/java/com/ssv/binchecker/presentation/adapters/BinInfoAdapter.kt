package com.ssv.binchecker.presentation.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.ssv.binchecker.R
import com.ssv.binchecker.databinding.ItemInfoBinding
import com.ssv.binchecker.domain.entity.DescriptionContent
import com.ssv.binchecker.presentation.diffUtils.DescriptionContentDiffCallback
import com.ssv.binchecker.presentation.viewHolders.BinInfoViewHolder

class BinInfoAdapter :
    ListAdapter<DescriptionContent, BinInfoViewHolder>(DescriptionContentDiffCallback()) {

    var clickListener: ((DescriptionContent) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemInfoBinding.inflate(inflater, parent, false)
        return BinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinInfoViewHolder, position: Int) {
        val item = getItem(position)
        setupClickListener(holder, item)
        setupView(holder, item)
    }

    private fun setupView(holder: BinInfoViewHolder, item: DescriptionContent) {
        with(holder.binding) {
            descriptionItem.text = item.description
            contentItem.text = item.content
            when (holder.itemViewType) {
                CLICKABLE -> setupClickable(holder)
                NON_CLICKABLE -> setupNonClickable(holder)
            }
        }
    }

    private fun setupClickable(holder: BinInfoViewHolder) {
        with(holder.binding.contentItem) {
            setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.blue))
            paintFlags = holder.binding.contentItem.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    private fun setupNonClickable(holder: BinInfoViewHolder) {
        with(holder.binding.contentItem) {
            setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
            paintFlags = holder.binding.contentItem.paintFlags or Paint.ANTI_ALIAS_FLAG
        }
    }

    private fun setupClickListener(holder: BinInfoViewHolder, item: DescriptionContent) {
        holder.binding.contentItem.setOnClickListener {
            if (item.isClickable) {
                clickListener?.invoke(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isClickable) CLICKABLE else NON_CLICKABLE
    }

    companion object {
        private const val CLICKABLE = 1
        private const val NON_CLICKABLE = 0
    }
}