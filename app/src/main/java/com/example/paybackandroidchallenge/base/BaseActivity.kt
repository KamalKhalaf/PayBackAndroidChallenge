package com.example.paybackandroidchallenge.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 2:06 AM
 */


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initConfigurations()
        _binding = inflateLayout(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 31) {
            window.decorView.layoutDirection = resources.configuration.layoutDirection
        }
        initViews()
        //hideKeyboard()
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB
    protected abstract fun initViews()
    protected abstract fun initConfigurations()

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            //hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }
}