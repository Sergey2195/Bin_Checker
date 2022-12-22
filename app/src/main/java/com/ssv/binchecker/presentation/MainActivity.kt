package com.ssv.binchecker.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ssv.binchecker.BinApp
import com.ssv.binchecker.R
import com.ssv.binchecker.databinding.ActivityMainBinding
import com.ssv.binchecker.presentation.fragments.InitialFragment
import com.ssv.binchecker.presentation.viewModels.MainViewModel
import com.ssv.binchecker.presentation.viewModelsFactory.ViewModelsFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//    @Inject
//    lateinit var viewModelFactory: ViewModelsFactory
//
//    private val component by lazy {
//        (application as BinApp).component
//    }

//    private val viewModel by lazy {
//        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
