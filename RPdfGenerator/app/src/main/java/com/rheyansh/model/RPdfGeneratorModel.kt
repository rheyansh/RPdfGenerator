package com.rheyansh.lenden.model

import com.rheyansh.model.RTransaction
import com.rheyansh.model.RTransactionType

class RPdfGeneratorModel(list: List<RTransaction>, header: String) {

    var list = emptyList<RTransaction>()
    var header = ""
    var totalCredit = ""
    var totalDebit = ""
    var totalProfit = ""

    init {
        this.list = list
        this.header = header
        calculateTotal(list)
    }

    private fun calculateTotal(items: List<RTransaction>) {
        val totalPlus = items.map {
            if (it.transType == RTransactionType.plus) {
                it.totalPrice
            } else { 0.0 }
        }.sum()

        val totalMinus = items.map {
            if (it.transType == RTransactionType.minus) {
                it.totalPrice
            } else { 0.0 }
        }.sum()

        val final = totalPlus - totalMinus
        totalDebit = "-" + totalMinus.toString()
        totalCredit = totalPlus.toString()
        totalProfit = final.toString()
    }
}