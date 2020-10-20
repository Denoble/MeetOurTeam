package com.gevcorst.s_g_coffeemeetsbagel.ui

import android.database.DatabaseUtils
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.gevcorst.s_g_coffeemeetsbagel.R
import com.gevcorst.s_g_coffeemeetsbagel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        as ActivityMainBinding
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            window.navigationBarColor = resources.getColor(R.color.sky_blue_dark,null)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}