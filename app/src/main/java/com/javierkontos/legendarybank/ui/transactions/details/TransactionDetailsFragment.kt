package com.javierkontos.legendarybank.ui.transactions.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.javierkontos.legendarybank.R
import com.javierkontos.legendarybank.databinding.FragmentTransactionDetailsBinding
import com.javierkontos.legendarybank.domain.Transaction
import com.javierkontos.legendarybank.ui.commons.visibleOrGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailsFragment : Fragment() {
    private lateinit var binding: FragmentTransactionDetailsBinding
    private val viewModel: TransactionDetailsViewModel by viewModels()
    private val args: TransactionDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_transaction_details,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, {
            when (it) {
                is TransactionDetailsViewModel.UiModel.Success -> showTransactions(it.transactionList)
                is TransactionDetailsViewModel.UiModel.Loading -> loading()
            }
        })

        binding.productSku = args.productSku

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getTransactionDetails(args.productSku, args.transactions.transactions)
    }

    private fun loading() {
        binding.loading.visibleOrGone(true)
    }

    private fun showTransactions(transactionList: List<Transaction>) {
        binding.loading.visibleOrGone(false)

        with(binding.rvRelatedTransactions) {
            layoutManager = LinearLayoutManager(activity)
            adapter = TransactionDetailsAdapter(transactionList)
        }

        binding.total = "${viewModel.getTotal(transactionList)}€"
    }

}