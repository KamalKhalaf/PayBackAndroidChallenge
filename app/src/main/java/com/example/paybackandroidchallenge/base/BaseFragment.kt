package com.example.paybackandroidchallenge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.common.Inflate

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 10:08 AM
 */


abstract class BaseFragment <VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment(){

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun initViews()

    //for navigation with args data
    fun navigate(navAction: NavDirections) {
        val action =
            Navigation.findNavController(binding.root).currentDestination?.getAction(navAction.actionId)
        if (action != null && Navigation.findNavController(binding.root).currentDestination?.id != action.destinationId) {
            Navigation.findNavController(binding.root).navigate(navAction)
        }
    }

    fun navigate(actionName: Int, b: Bundle? = null) {
        val action =
            Navigation.findNavController(binding.root).currentDestination?.getAction(actionName)
        if (action != null && Navigation.findNavController(binding.root).currentDestination?.id != action.destinationId) {
            b?.let { Navigation.findNavController(binding.root).navigate(actionName, b) }
                ?: Navigation.findNavController(binding.root).navigate(actionName)
        }
    }

    //use the name of the class for tag
    val Any.TAG: String
        get() {
            return javaClass.simpleName
        }

    fun showShortToast(text: String) {
        Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
    }

    fun showShortToast(txtRes: Int) {
        Toast.makeText(requireActivity(), txtRes, Toast.LENGTH_SHORT).show()
    }
}