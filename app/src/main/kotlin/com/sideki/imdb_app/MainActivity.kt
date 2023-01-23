package com.sideki.imdb_app

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sideki.imdb_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavHost()
        binding.bottomNavigation.setOnItemSelectedListener {
            bottomNavigation(it.itemId, it)
            return@setOnItemSelectedListener true
        }
        showBottomNavBar(navController)
    }

    private fun initNavHost() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentNavHost)
        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        } else {
            throw IllegalStateException("Couldn't find navigation controller.")
        }
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun bottomNavigation(fragmentId: Int, item: MenuItem) {
        NavigationUI.onNavDestinationSelected(item, navController)
        navController.navigate(fragmentId)
    }

    private fun showBottomNavBar(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.isGone =
                destination.id == R.id.loginFragment || destination.id == R.id.registrationFragment
        }
    }
}
