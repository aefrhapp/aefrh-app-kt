package aefrh.es.aefrh.presentation

import aefrh.es.aefrh.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar & Navigation
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host)

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
//        visibilityNavElements(navController)

        // Drawer layout listener
        nav_view.setNavigationItemSelectedListener(this)

    }

    // Allows NavigationUI to support proper up navigation or the drawer layout
    // drawer menu, depending on the situation
    override fun onSupportNavigateUp() = findNavController(R.id.nav_host)
        .navigateUp(appBarConfiguration)

//    private fun visibilityNavElements(navController: NavController) {
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.splashFragment -> {
//                    toolbar?.visibility = View.GONE
//                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
//                }
//                else -> {
//                    toolbar?.visibility = View.VISIBLE
//                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
//                }
//            }
//        }
//
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {
                Toast.makeText(this, "nav_news clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_map -> {
                Toast.makeText(this, "nav_map clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_us -> {
                Toast.makeText(this, "nav_us clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_contact -> {
                Toast.makeText(this, "nav_contact clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_multimedia -> {
                Toast.makeText(this, "nav_multimedia clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_magazine -> {
                Toast.makeText(this, "nav_magazine clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_espana -> {
                goToBrowser("https://www.fiestashistoricas.es/")
            }
            R.id.action_europa -> {
                goToBrowser("http://www.cefmh.eu/")
            }
        }
        return item.onNavDestinationSelected(findNavController(R.id.nav_host))
                || super.onOptionsItemSelected(item)
    }

    private fun goToBrowser(url: String?) {
        val intentWeb = Intent(Intent.ACTION_VIEW)
        intentWeb.addCategory(Intent.CATEGORY_BROWSABLE)
        intentWeb.data = Uri.parse(url)
        startActivity(intentWeb)
    }

}