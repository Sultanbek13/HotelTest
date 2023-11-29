package com.sultandev.presentation.ui.reservation.adapter.vh

import android.util.Patterns
import androidx.recyclerview.widget.RecyclerView
import com.sultandev.presentation.R
import com.sultandev.presentation.databinding.ItemVhInfoAboutCustomerBinding
import com.sultandev.presentation.ui.util.TextWatcher

internal class CustomerInfoViewHolder(
    private val binding: ItemVhInfoAboutCustomerBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private val MAX_LENGTH = 12
    private var isFormatting = false

    fun bind() {
        binding.apply {
            customerPhoneNumberEditTextInput.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    charSequence: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int,
                ) {
                    if (isFormatting) {
                        isFormatting = false
                        return
                    }

                    if (before > count) {
                        return
                    }

                    val length = charSequence?.length ?: 0

                    if (length > 0 && !isValidPhoneNumber(charSequence.toString())) {
                        isFormatting = true

                        customerPhoneNumberEditTextInput.text?.delete(length - 1, length)

                        isFormatting = false
                        return
                    }

                    if (length == 1) {
                        isFormatting = true
                        customerPhoneNumberEditTextInput.text?.insert(
                            0, "${root.resources.getString(R.string.tv_start_mask_phone_number)} "
                        )
                    } else if ((length == 3 || length == 7 || length == 10) && charSequence?.get(
                            length - 1
                        ) != ' '
                    ) {
                        isFormatting = true
                        customerPhoneNumberEditTextInput.text?.insert(length - 1, " ")
                    }
                }
            })

            customerEmailEditTextInput.addTextChangedListener(object: TextWatcher {
                override fun onTextChanged(
                    charSequence: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int,
                ) {
                    charSequence?.let {
                        if(!isValidEmail(it)) customerEmailEditTextInput.error = root.resources.getString(R.string.tv_error_wrong_email)
                    }
                }
            })
        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length <= MAX_LENGTH
    }


    private fun isValidEmail(email: CharSequence): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}