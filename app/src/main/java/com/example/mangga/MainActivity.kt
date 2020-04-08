package com.example.mangga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mangga.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainActivityViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar.toolbar)

        val navController = this.findNavController(R.id.myNavHostFragment)

        val bottomNavigation = binding.navView

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.dashboardFragment, R.id.mangaListFragment, R.id.profileFragment
        ).build()

        viewModel.bottomNavigationVisibility.observe(this, Observer {
            bottomNavigation.visibility = it
        })

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mangaDetailFragment -> {
                    viewModel.hideBottomNav()
                    supportActionBar?.hide()
                }
                else -> {
                    setSupportActionBar(binding.toolbar.toolbar)
                    viewModel.showBottomNav()
                    supportActionBar?.show()
                }
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
