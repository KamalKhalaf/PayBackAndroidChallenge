package com.example.common

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 2:14 AM
 */


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard(view: View) {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.gone(){
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

fun <T> LifecycleOwner.observeEvent(
    liveData: LiveData<SingleEvent<T>>,
    action: (t: SingleEvent<T>) -> Unit
) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

