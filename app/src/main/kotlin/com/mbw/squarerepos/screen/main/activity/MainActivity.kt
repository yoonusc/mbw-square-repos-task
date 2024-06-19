package com.mbw.squarerepos.screen.main.activity

import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.mbw.squarerepos.R
import com.mbw.squarerepos.base.activity.BaseActivity
import com.mbw.squarerepos.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity is the primary activity for the SquareRepos application.
 * This activity sets up the main navigation host fragment and configures the app's navigation components.
 * It extends from BaseActivity, utilizing ActivityMainBinding for view binding.
 *
 * The class is annotated with @AndroidEntryPoint to integrate with Dagger-Hilt for dependency injection.
 *
 * The appBarConfiguration is used to manage navigation within the app, ensuring that the ActionBar is properly
 * configured to handle navigation actions.
 *
 * Key functionalities include:
 * - Setting the window to not fit system windows, allowing for edge-to-edge content display.
 * - Finding the NavHostFragment and setting up the NavController for navigation.
 * - Configuring the ActionBar with the NavController and AppBarConfiguration.
 * - Overriding onSupportNavigateUp to handle navigation when the up button is pressed in the ActionBar.
 *
 * The activity_main layout resource file is used to define the UI layout for this activity.
 */

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}