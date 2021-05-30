package com.javierkontos.legendarybank.ui.transactions.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.javierkontos.legendarybank.R
import com.javierkontos.legendarybank.databinding.ItemTransactionDetailsBinding
import com.javierkontos.legendarybank.domain.Transaction

class TransactionDetailsAdapter(
    private val transactions: List<Transaction>
) : RecyclerView.Adapter<TransactionDetailsViewHolder>() {

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionDetailsViewHolder =
        TransactionDetailsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_transaction_details,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TransactionDetailsViewHolder, position: Int) {
        holder.bind(transactions[position])
    }
}

class TransactionDetailsViewHolder(val binding: ItemTransactionDetailsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        transaction: Transaction
    ) {
        binding.amount = "${transaction.amount}€"

    }
}