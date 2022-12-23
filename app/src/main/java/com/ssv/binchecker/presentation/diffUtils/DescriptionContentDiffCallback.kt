package com.ssv.binchecker.presentation.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.ssv.binchecker.domain.entity.DescriptionContent

class DescriptionContentDiffCallback: DiffUtil.ItemCallback<DescriptionContent>() {
    override fun areItemsTheSame(
        oldItem: DescriptionContent,
        newItem: DescriptionContent
    ): Boolean {
        return oldItem.description == newItem.description
    }

    override fun areContentsTheSame(
        oldItem: DescriptionContent,
        newItem: DescriptionContent
    ): Boolean {
        return oldItem == newItem
    }
}