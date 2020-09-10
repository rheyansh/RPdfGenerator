package com.rheyansh.model

import java.util.*

enum class RTransactionType { plus, minus }

class RTransaction {

    var itemName: String = ""
    var custName: String = ""
    var transType: RTransactionType = RTransactionType.plus
    var pricePerUnit: Double = 0.0
    var quantity: Int = 0
    var totalPrice: Double = 0.0
    var transactionDateStr: String = ""

    constructor() {

//        itemName = map[kItemName].toString()
//        custName = map[kCustName].toString()
//        addedBy = map[kAddedBy].toString()
//        bookId = map[kBookId].toString()
//        userId = map[kUserId].toString()
//
//        quantityStr = map[kQuantity].toString()
//        quantity = quantityStr.toInt()
//
//        pricePerUnitStr = map[kPricePerUnit].toString()
//        pricePerUnit = pricePerUnitStr.toDouble()
//
//        quantityToPrice = "$quantity x $pricePerUnit"
//
//        totalPrice = quantity*pricePerUnit
//        totalPriceStr = totalPrice.toString()
//
//        val transType = map[kTransType].toString()
//        val orderType = map[kOrderType].toString()
//
//        this.transType = AppConstants.transactionType(transType)
//        this.orderType = AppConstants.orderType(orderType)
//
//        var txnTimestamp = map[kDateOfTrans] as? Timestamp
//        transactionDate = txnTimestamp?.toDate()
//
//        if (transactionDate != null) {
//            transactionDateStr = transactionDate!!.toSimpleString()
//            transactionDateFmtYY = transactionDate!!.toSimpleString("dd MMM, yy")
//        }
//
//        var entryTimestamp = map[kCREATED_AT] as? Timestamp
//
//        entryDate = entryTimestamp?.toDate()
//        if (entryDate != null) {
//            entryDateStr = entryDate!!.toSimpleString()
//        }
    }
}
