package com.javierkontos.legendarybank.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.javierkontos.legendarybank.R
import com.javierkontos.legendarybank.databinding.FragmentTransactionsBinding
import com.javierkontos.legendarybank.domain.Transaction
import com.javierkontos.legendarybank.domain.TransactionList
import com.javierkontos.legendarybank.ui.commons.visibleOrGone
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TransactionsFragment : Fragment() {
    private lateinit var binding: FragmentTransactionsBinding
    private val viewModel: TransactionsViewModel by viewModels()
    private lateinit var transactionList: TransactionList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_transactions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, {
            when (it) {
                is TransactionsViewModel.UiModel.Success -> showTransactionsContent(it.transactionList)
                is TransactionsViewModel.UiModel.Failure -> showFailureError()
                is TransactionsViewModel.UiModel.Loading -> loading()
            }
        })

        viewModel.getTransactions()
    }

    private fun loading() {
        binding.loading.visibleOrGone(true)
    }

    private fun showFailureError() {
        val snackbar = Snackbar
            .make(
                binding.root,
                getString(R.string.error_currencyRates),
                Snackbar.LENGTH_LONG
            )
        snackbar.show()
    }

    private fun showTransactionsContent(transactions: List<Transaction>) {
        binding.loading.visibleOrGone(false)
        this.transactionList = TransactionList(transactions)

        with(binding.rvTransactions) {
            layoutManager = LinearLayoutManager(activity)
            adapter =
                TransactionsAdapter(transactions.sortedByDescending { it.sku }
                    .distinctBy { it.sku }, object : TransactionOnClickListener {
                    override fun onClickProduct(productSku: String) {
                        navigateToTransactionDetail(productSku)
                    }

                })
        }
    }

    private fun navigateToTransactionDetail(productSku: String) {
        Timber.d("Click on Product: $productSku")
        val action =
            TransactionsFragmentDirections.actionTransactionsFragmentToTransactionDetailsFragment(
                productSku, this.transactionList
            )
        findNavController().navigate(action)
    }
}