package com.sideki.imdb_app

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sideki.imdb_app.databinding.ActivityMainBinding
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var dataStorePreferenceStorage: DataStorePreferenceStorage


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
        visibilityNavElements(navController)
        lifecycleScope.launchWhenStarted {
            dataStorePreferenceStorage.isLoggedIn.collect { isLoggedIn ->
                if (isLoggedIn) {
                    val navHostFragment =
                        supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment
                    val inflater = navHostFragment.navController.navInflater
                    val graph = inflater.inflate(R.navigation.nav_graph)
                    graph.setStartDestination(R.id.moviesFragment)
                    val navController = navHostFragment.navController
                    navController.setGraph(graph, intent.extras)
                }
            }
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

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment2 -> binding.bottomNavigation?.visibility = View.GONE
                R.id.registrationFragment -> binding.bottomNavigation?.visibility = View.GONE
                else -> binding.bottomNavigation?.visibility = View.VISIBLE
            }
        }
    }
}
