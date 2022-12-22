package com.ssv.binchecker.presentation.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ssv.binchecker.databinding.FragmentBinInfoBinding
import com.ssv.binchecker.domain.entity.BinInfo

class BinInfoFragment : Fragment() {

    private var binInfoParc: Parcelable? = null
    private var binInfo: BinInfo? = null
    private lateinit var binding: FragmentBinInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            binInfoParc = it.getParcelable(BIN_INFO)
            binInfo = binInfoParc as BinInfo
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("TAG", binInfo.toString())
    }

    companion object {

        private const val BIN_INFO = "Bin Info"

        fun newInstance(binInfo: BinInfo) =
            BinInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BIN_INFO, binInfo)
                }
            }
    }
}