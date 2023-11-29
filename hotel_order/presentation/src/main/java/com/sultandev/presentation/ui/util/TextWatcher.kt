package com.sultandev.presentation.ui.util

import android.text.Editable
import android.text.TextWatcher

internal interface TextWatcher: TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int)

    override fun afterTextChanged(s: Editable?) {}
}