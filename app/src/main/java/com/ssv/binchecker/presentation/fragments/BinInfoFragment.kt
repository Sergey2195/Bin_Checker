package com.ssv.binchecker.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssv.binchecker.BinApp
import com.ssv.binchecker.databinding.FragmentBinInfoBinding
import com.ssv.binchecker.databinding.FragmentInitialBinding
import com.ssv.binchecker.domain.entity.BinInfo
import com.ssv.binchecker.domain.entity.DescriptionContent
import com.ssv.binchecker.presentation.adapters.BinInfoAdapter
import com.ssv.binchecker.presentation.viewModels.BinInfoViewModel
import com.ssv.binchecker.presentation.viewModels.InitialViewModel
import com.ssv.binchecker.presentation.viewModelsFactory.ViewModelsFactory
import javax.inject.Inject

class BinInfoFragment : Fragment() {

    private var binInfoParc: Parcelable? = null
    private var binInfo: BinInfo? = null
    private var binNumber: String? = null
    private lateinit var binding: FragmentBinInfoBinding
    private lateinit var adapter: BinInfoAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelsFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[BinInfoViewModel::class.java]
    }

    private val component by lazy {
        ((requireActivity().application) as BinApp).component
    }

    override fun onAttach(context: Context) {
        component.injectBinInfoFragment(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            binInfoParc = it.getParcelable(BIN_INFO)
            binInfo = binInfoParc as BinInfo
            binNumber = it.getString(BIN_NUMBER)
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
        prepareViews()
        prepareRecyclerView()
    }

    private fun prepareViews() {
        binding.binNumber.text = binNumber
    }

    private fun prepareRecyclerView() {
        adapter = BinInfoAdapter()
        binding.infoRv.adapter = adapter
        binding.infoRv.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false)
        val list = viewModel.prepareListItems(binInfo ?: return)
        adapter.submitList(list)
    }

    companion object {

        private const val BIN_INFO = "Bin Info"
        private const val BIN_NUMBER = "Bin Number"

        fun newInstance(binInfo: BinInfo, binNumber: String) =
            BinInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BIN_INFO, binInfo)
                    putString(BIN_NUMBER, binNumber)
                }
            }
    }
}