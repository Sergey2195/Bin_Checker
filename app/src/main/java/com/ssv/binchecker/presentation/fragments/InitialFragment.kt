package com.ssv.binchecker.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ssv.binchecker.BinApp
import com.ssv.binchecker.R
import com.ssv.binchecker.databinding.FragmentInitialBinding
import com.ssv.binchecker.domain.*
import com.ssv.binchecker.presentation.viewModels.InitialViewModel
import com.ssv.binchecker.presentation.viewModelsFactory.ViewModelsFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitialFragment : Fragment() {

    private lateinit var binding: FragmentInitialBinding

    @Inject
    lateinit var viewModelFactory: ViewModelsFactory

    var flow: Flow<BinState>? = null

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[InitialViewModel::class.java]
    }

    private val component by lazy {
        ((requireActivity().application) as BinApp).component
    }

    override fun onAttach(context: Context) {
        component.injectInitialFragment(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInitialBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupFlow()
    }

    private fun setupFlow() {
        lifecycleScope.launch {
            flow = viewModel.getInfoFlow()
        }
    }

    private fun checkResult(res: BinState){
        when (res){
            is BinSuccess -> startSecondFragment(res)
            is BinFailure -> {}
            is BinLoading -> {}
            is BinInitial-> return
        }
    }

    private fun startSecondFragment(state: BinSuccess){
        viewModel.clearFlow()
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack("second")
            .replace(R.id.mainFragmentContainer, BinInfoFragment.newInstance(state.binInfo))
            .commit()
    }

    private fun setupClickListeners() {
        binding.nextButton.setOnClickListener {
            val text = binding.inputEditText.text.toString()
            sendToViewModel(text)
            lifecycleScope.launch {
                flow?.collectLatest {
                    checkResult(it)
                }
            }
        }
    }

    private fun sendToViewModel(str: String) {
        viewModel.sendBin(str)
    }

    companion object {
        fun newInstance() =
            InitialFragment()
    }
}