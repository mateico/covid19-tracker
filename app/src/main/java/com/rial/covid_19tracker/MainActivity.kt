package com.rial.covid_19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.rial.covid_19tracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // Finds the navigation controller object
        val navController = this.findNavController(R.id.myNavHostFragment)

        // Links the navigation controller to the app bar
        NavigationUI.setupActionBarWithNavController(this,navController)

    }

    // Overrides the onSupportNavigateUp() method to call navigateUp() in the navigation controller
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}