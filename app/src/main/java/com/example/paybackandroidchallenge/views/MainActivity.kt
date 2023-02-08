package com.example.paybackandroidchallenge.views

import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.paybackandroidchallenge.R
import com.example.paybackandroidchallenge.base.BaseActivity
import com.example.paybackandroidchallenge.databinding.ActivityMainBinding
import com.example.paybackandroidchallenge.viewmodel.ImagesViewModel
import com.example.paybackandroidchallenge.viewmodel.ImagesViewStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var fragments: List<Fragment>
    private lateinit var navHostFragment : NavHostFragment
    private lateinit var navController : NavController

    override fun initViews() {

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_start) as NavHostFragment
        fragments= ArrayList()

        navController = findNavController(R.id.nav_host_fragment_content_start)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

    override fun initConfigurations() {}
}