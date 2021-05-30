package com.javierkontos.legendarybank.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.javierkontos.legendarybank.R
import com.javierkontos.legendarybank.databinding.ItemTransactionBinding
import com.javierkontos.legendarybank.domain.Transaction

class TransactionsAdapter(
    private val transactions: List<Transaction>,
    private val listener: TransactionOnClickListener
) : RecyclerView.Adapter<TransactionsViewHolder>() {

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder =
        TransactionsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_transaction,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.bind(transactions[position], listener)
    }
}

class TransactionsViewHolder(val binding: ItemTransactionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        transaction: Transaction,
        listener: TransactionOnClickListener
    ) {
        binding.productSku = transaction.sku

        binding.cvTransaction.setOnClickListener {
            listener.onClickProduct(transaction.sku)
        }
    }
}