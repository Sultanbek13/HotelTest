package com.sultandev.presentation.ui.extension

import android.util.Patterns
import android.view.View
import android.widget.TextView
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Int.toFormatAmount(): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("ru", "RU")) as DecimalFormat
    currencyFormat.applyPattern("###,### ₽")
    return currencyFormat.format(this.toLong())
}

fun View.click(action: () -> Unit) {
    this.setOnClickListener {
        action.invoke()
    }
}