package com.rheyansh.rpdfgenerator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_transaction.view.*
import com.rheyansh.model.RTransaction
import com.rheyansh.model.RTransactionType

private const val VIEW_TYPE_GENERAL_MESSAGE = 1

class TransListAdapter (val context: Context) : RecyclerView.Adapter<TransRowViewHolder>() {

    var onItemClick: ((item: RTransaction) -> Unit)? = null
    var items = emptyList<RTransaction>()

    init { }

    override fun onBindViewHolder(holder: TransRowViewHolder, position: Int) {
        val item = items.get(position)
        holder?.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransRowViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.row_transaction, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_GENERAL_MESSAGE
    }

    inner class ItemViewHolder (view: View) : TransRowViewHolder(view) {

        private var titleTextView: TextView = view.titleTextView
        private var subTitleTextView: TextView = view.subTitleTextView
        private var tTypeTextView: TextView = view.tTypeTextView
        private var quantityTextView: TextView = view.quantityTextView
        private var dateTextView: TextView = view.dateTextView
        private var priceTextView: TextView = view.priceTextView

        private var isSetupDone = false

        override fun bind(item: RTransaction) {
            super.bind(item)

            if (!isSetupDone) {
                itemView.setOnClickListener {
                    val item = items.get(adapterPosition)
                    onItemClick?.invoke(item)
                }
                isSetupDone = true
                setUp()
            }

            titleTextView.text = item.itemName
            subTitleTextView.text = item.custName
            quantityTextView.text = item.quantity.toString() + " x " + item.pricePerUnit.toString()
            dateTextView.text = item.transactionDateStr
            val totalPriceStr = item.quantity * item.pricePerUnit
            priceTextView.text = "= " + totalPriceStr

            if (item.transType == RTransactionType.minus) {
                tTypeTextView.setBackgroundResource(R.color.alert)
                priceTextView.setTextColor(context.getResources().getColor(R.color.alert));
            } else {
                tTypeTextView.setBackgroundResource(R.color.success)
                priceTextView.setTextColor(context.getResources().getColor(R.color.success));
            }
        }

        private fun setUp() {


        }
    }
}

open class TransRowViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(item: RTransaction) {}
}