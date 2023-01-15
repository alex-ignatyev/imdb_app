package com.sideki.imdb_app

import android.os.Bundle
import android.view.MenuItem
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
            when (it.itemId) {
                R.id.moviesFragment -> bottomNavigation(R.id.moviesFragment, it)
                R.id.profileFragment -> bottomNavigation(R.id.profileFragment, it)
            }
            return@setOnItemSelectedListener true
        }
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
}
