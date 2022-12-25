package com.ssv.binchecker.presentation.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.ssv.binchecker.domain.entity.BinHistory

class BinItemHistoryDiffCallback: DiffUtil.ItemCallback<BinHistory>() {
    override fun areItemsTheSame(oldItem: BinHistory, newItem: BinHistory): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BinHistory, newItem: BinHistory): Boolean {
        return oldItem == newItem
    }
}