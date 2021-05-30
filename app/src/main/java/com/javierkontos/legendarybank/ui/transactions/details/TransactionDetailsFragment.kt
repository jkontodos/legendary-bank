package com.javierkontos.legendarybank.ui.transactions.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.javierkontos.legendarybank.R
import com.javierkontos.legendarybank.databinding.FragmentTransactionDetailsBinding

class TransactionDetailsFragment : Fragment() {
    private lateinit var binding: FragmentTransactionDetailsBinding
    private val args: TransactionDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productSku = args.productSku

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}