package com.sideki.imdb_app

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sideki.imdb_app.data.api.ProfileFragment
import com.sideki.imdb_app.databinding.ActivityMainBinding
import com.sideki.imdb_app.ui.movies_list.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var fragment: Fragment = MoviesFragment()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentNavHost)
        navController = navHostFragment!!.findNavController()
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.moviesFragment -> bottomNavigation(R.id.moviesFragment, it)
                R.id.profileFragment -> bottomNavigation(R.id.profileFragment, it)
            }
            return@setOnItemSelectedListener true
        }
    }
    private fun bottomNavigation(fragmentId: Int, item: MenuItem) {
        NavigationUI.onNavDestinationSelected(item, navController)
        navController.navigate(fragmentId)
    }
}
