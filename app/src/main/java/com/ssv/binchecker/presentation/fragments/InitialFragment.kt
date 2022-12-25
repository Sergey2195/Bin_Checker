package com.ssv.binchecker.presentation.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ssv.binchecker.BinApp
import com.ssv.binchecker.R
import com.ssv.binchecker.databinding.FragmentInitialBinding
import com.ssv.binchecker.domain.*
import com.ssv.binchecker.presentation.adapters.BinHistoryAdapter
import com.ssv.binchecker.presentation.viewModels.InitialViewModel
import com.ssv.binchecker.presentation.viewModelsFactory.ViewModelsFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class InitialFragment : Fragment() {

    private lateinit var binding: FragmentInitialBinding

    @Inject
    lateinit var viewModelFactory: ViewModelsFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[InitialViewModel::class.java]
    }
    private lateinit var adapter: BinHistoryAdapter

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
        setupRecyclerView()
        setupKeyboardClickListener()
        loadingState(false)
    }

    private fun setupKeyboardClickListener() {
        binding.inputEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                nextButtonClicked()
                return@setOnEditorActionListener true
            }else{
                return@setOnEditorActionListener false
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = BinHistoryAdapter()
        binding.historyRv.adapter = adapter
        binding.historyRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        loadHistory()
        adapter.clickListener = {
            binding.inputEditText.setText(it.id)
            binding.nextButton.performClick()
        }
    }

    private fun loadHistory(){
        lifecycleScope.launch(Dispatchers.IO){
            val list = viewModel.loadHistory()
            withContext(Dispatchers.Main){
                adapter.submitList(list)
            }
        }
    }

    private fun checkResult(res: BinState){
        when (res){
            is BinSuccess -> startSecondFragment(res)
            is BinFailure -> showError(res)
            is BinLoading -> loadingState(true)
            is BinInitial-> return
        }
    }

    private fun loadingState(isLoading: Boolean){
        binding.progress.isVisible = isLoading
        binding.nextButton.isVisible = !isLoading
        binding.inputEditText.isVisible = !isLoading
    }

    private fun showError(binFailure: BinFailure){
        val text = requireContext().getText(viewModel.showError(binFailure))
        Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG).show()
        loadingState(false)
    }

    private fun startSecondFragment(state: BinSuccess){
        viewModel.clearFlow()
        val binNumber = binding.inputEditText.text.toString()
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainFragmentContainer, BinInfoFragment.newInstance(state.binInfo, binNumber))
            .commit()
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setupClickListeners() {
        binding.nextButton.setOnClickListener {
            nextButtonClicked()
        }
    }
    private fun nextButtonClicked(){
        val text = binding.inputEditText.text.toString()
        sendToViewModel(text)
        hideKeyboardFrom(requireContext(), requireView())
        lifecycleScope.launch {
            viewModel.getInfoFlow().collect() {
                checkResult(it)
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