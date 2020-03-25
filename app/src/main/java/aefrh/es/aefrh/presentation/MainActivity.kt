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
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar
        setSupportActionBar(toolbar)

        //configure nav controller
        val navController = findNavController(R.id.nav_host)

        // setup action bar && nav controller
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Drawer layout listener
        nav_view.setNavigationItemSelectedListener(this)

    }

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

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host)
        .navigateUp(appBarConfiguration)

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