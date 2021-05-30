package com.javierkontos.legendarybank.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.javierkontos.legendarybank.R
import com.javierkontos.legendarybank.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, {
            when (it) {
                is SplashViewModel.UiModel.Success -> {
                    lifecycleScope.launch {
                        delay(1000)
                        navigateToTransactions()
                    }
                }
                is SplashViewModel.UiModel.Failure -> showFailureError()
                is SplashViewModel.UiModel.Loading -> Timber.d("Loading . . .")
            }
        })

        viewModel.getCurrencyRates()
    }

    private fun showFailureError(){
        val snackbar = Snackbar
            .make(
                binding.root,
                getString(R.string.error_currencyRates),
                Snackbar.LENGTH_LONG
            )
        snackbar.show()
    }

    private fun navigateToTransactions() {
        Timber.d("Navigate to Transactions")
        val action = SplashFragmentDirections.actionSplashFragmentToTransactionsFragment()
        findNavController().navigate(
            action,
            NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
        )
    }
}