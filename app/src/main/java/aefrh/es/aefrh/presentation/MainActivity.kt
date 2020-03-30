package aefrh.es.aefrh.presentation

import aefrh.es.aefrh.BuildConfig
import aefrh.es.aefrh.R
import aefrh.es.aefrh.utils.goToBrowser
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity: AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar & Navigation
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.nav_host)

        // AppBarConfiguration with the correct top-level destinations
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            drawer_layout)

        // This allows NavigationUI to decide what label to show in the action bar
        // By using appBarConfig, it will also determine whether to
        // show the up arrow or drawer menu icon
        setupActionBarWithNavController(navController, appBarConfiguration)

        // In split screen mode, you can drag this view out from the left
        // This does NOT modify the actionbar
        nav_view?.setupWithNavController(navController)

        // Set visibility for NavigationView & Toolbar
        // We never want this elements in SignIn
        visibilityNavElements(navController)

        // Set version
        tvVersion.text = getString(R.string.version, BuildConfig.VERSION_NAME)

    }

    // Allows NavigationUI to support proper up navigation or the drawer layout
    // drawer menu, depending on the situation
    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration)

    private fun visibilityNavElements(navController: NavController) {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.epocasFragment -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
                }
                else -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_espana -> {
                goToBrowser("https://www.fiestashistoricas.es/", this)
            }
            R.id.action_europa -> {
                goToBrowser("http://www.cefmh.eu/", this)
            }
        }
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

}