package com.limuealimi.newsapp.utils

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import java.util.*

fun Fragment.showErrorMessage(message: String) {
    val snack = Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG)
    snack.show()
}

fun Fragment.navigate(actionId: Int, bundle: Bundle? = null) {
    this.findNavController().navigate(actionId, bundle)
}

fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun Fragment.hideKeyBoard(v: View) {
    val imm =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}

fun <T> MutableList<T>.updateList(items: List<T>) {
    this.clear()
    this.addAll(items.toList())
}

fun Date.sameDay(date: Date): Boolean {
    val thisDate = Calendar.getInstance()
    thisDate.time = this
    return thisDate.let {
        val expectedDate = Calendar.getInstance()
        expectedDate.time = date
        it[Calendar.YEAR] == expectedDate[Calendar.YEAR] && it[Calendar.DAY_OF_YEAR] == expectedDate[Calendar.DAY_OF_YEAR] && it[Calendar.MONTH] == expectedDate[Calendar.MONTH]
    }
}