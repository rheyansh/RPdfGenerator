package com.rheyansh.rpdfgenerator

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rheyansh.helpers.RPermissionHelper
import com.rheyansh.lenden.model.RPdfGeneratorModel
import com.rheyansh.model.RTransaction
import com.rheyansh.model.RTransactionType
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransListAdapter
    private lateinit var totalCreditTextView: TextView
    private lateinit var totalDebitTextView: TextView
    private lateinit var totalProfitTextView: TextView
    private lateinit var createPdfButton: Button

    private lateinit var dummyInfo: RPdfGeneratorModel

    private var isGenerating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialSetup()
    }

    private fun initialSetup() {

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        totalDebitTextView = findViewById<TextView>(R.id.totalMinusValue)
        totalProfitTextView = findViewById<TextView>(R.id.totalPlusValue)
        totalCreditTextView = findViewById<TextView>(R.id.totalValue)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        createPdfButton = findViewById<Button>(R.id.createPdfButton)

        createPdf(false) //just ask permission for first time

        createPdfButton.setOnClickListener { createPdf(true) }

        dummyInfo = dummyModel()

        totalDebitTextView.text = dummyInfo.totalDebit
        totalCreditTextView.text = dummyInfo.totalCredit
        totalProfitTextView.text = dummyInfo.totalProfit

        adapter = TransListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.items = dummyInfo.list
    }

    fun createPdf(download: Boolean) {

        val permissionHelper = RPermissionHelper(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        permissionHelper.denied {
            if (it) {
                Log.d("Permission check", "Permission denied by system")
                permissionHelper.openAppDetailsActivity()
            } else {
                Log.d("Permission check", "Permission denied")
            }
        }

//Request all permission
        permissionHelper.requestAll {
            Log.d("Permission check", "All permission granted")

            if (!isGenerating && download) {
                isGenerating = true
                RPdfGenerator.generatePdf(this, dummyInfo)

                val handler = Handler()
                val runnable = Runnable {
                    //to avoid multiple generation at the same time. Set isGenerating = false on some delay
                    isGenerating = false
                }
                handler.postDelayed(runnable, 2000)
            }
        }

//Request individual permission
        permissionHelper.requestIndividual {
            Log.d("Permission check", "Individual Permission Granted")
        }
    }

    private fun dummyModel(): RPdfGeneratorModel {
        val list = dummyTransactions()
        val header = "Statement"
        val dummy = RPdfGeneratorModel(list, header)
        return dummy
    }

    private fun dummyTransactions(): List<RTransaction> {

        val list = arrayListOf<RTransaction>()

        val i1 = RTransaction()
        i1.custName = "Johan Store"
        i1.itemName = "Snacks"
        i1.quantity = 4
        i1.pricePerUnit = 40.0
        i1.totalPrice = i1.quantity * i1.pricePerUnit
        i1.transactionDateStr = "10 Sep, 20"
        i1.transType = RTransactionType.plus
        list.add(i1)


        val i2 = RTransaction()
        i2.custName = "Alice Store"
        i2.itemName = "Chocolate"
        i2.quantity = 3
        i2.pricePerUnit = 79.0
        i2.totalPrice = i2.quantity * i2.pricePerUnit
        i2.transactionDateStr = "9 Sep, 20"
        i2.transType = RTransactionType.plus
        list.add(i2)

        val i3 = RTransaction()
        i3.custName = "Alexa Mall"
        i3.itemName = "Shoes"
        i3.quantity = 2
        i3.pricePerUnit = 177.0
        i3.totalPrice = i3.quantity * i3.pricePerUnit
        i3.transactionDateStr = "9 Sep, 20"
        i3.transType = RTransactionType.minus
        list.add(i3)

        val i4 = RTransaction()
        i4.custName = "Zainab Baba"
        i4.itemName = "Chips"
        i4.quantity = 5
        i4.pricePerUnit = 140.0
        i4.totalPrice = i4.quantity * i4.pricePerUnit
        i4.transactionDateStr = "8 Sep, 20"
        i4.transType = RTransactionType.plus
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)


        return list
    }
}