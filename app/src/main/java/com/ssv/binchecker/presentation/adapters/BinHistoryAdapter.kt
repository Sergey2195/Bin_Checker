package com.ssv.binchecker.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ssv.binchecker.databinding.ItemHistoryBinding
import com.ssv.binchecker.domain.entity.BinHistory
import com.ssv.binchecker.presentation.diffUtils.BinItemHistoryDiffCallback
import com.ssv.binchecker.presentation.viewHolders.BinHistoryViewHolder

class BinHistoryAdapter :
    ListAdapter<BinHistory, BinHistoryViewHolder>(BinItemHistoryDiffCallback()) {

    var clickListener: ((BinHistory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return BinHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinHistoryViewHolder, position: Int) {
        val item = getItem(position)
        setupClickListeners(holder, item)
        setupViews(holder, item)
    }

    private fun setupViews(holder: BinHistoryViewHolder, item: BinHistory) {
        holder.binding.historyBinTv.text = item.id
    }

    private fun setupClickListeners(holder: BinHistoryViewHolder, binHistory: BinHistory) {
        holder.binding.itemConstraintLayout.setOnClickListener {
            clickListener?.invoke(binHistory)
        }
    }
}