package aefrh.es.aefrh.presentation.main

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.ActivityMainBinding
import aefrh.es.aefrh.presentation.base.BaseActivity
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity: BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val vModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    override val layout: Int = R.layout.activity_main

    override fun initUI(binding: ViewDataBinding?) {
        this.binding = binding as ActivityMainBinding
    }

    override fun onCreate() {

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0, 0
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        initViewModel()

    }

    private fun initViewModel() {

        vModel.epocaList.observe(this, Observer { epocaList ->
            for(item in epocaList) {
                Timber.e("Epoca: $item")
            }
        })

        // Observe showLoading value and display or hide our activity's progressBar
//        vModel.showLoading.observe(this, Observer { showLoading ->
//            mainProgressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
//        })

        // Observe showError value and display the error message as a Toast
        vModel.showError.observe(this, Observer { showError ->
            Timber.e("showError: $showError")
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        })

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_espana -> {
                goToBrowser("https://www.fiestashistoricas.es/")
                true
            }
            R.id.action_europa -> {
                goToBrowser("http://www.cefmh.eu/")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToBrowser(url: String?) {
        val intentWeb = Intent(Intent.ACTION_VIEW)
        intentWeb.addCategory(Intent.CATEGORY_BROWSABLE)
        intentWeb.data = Uri.parse(url)
        startActivity(intentWeb)
    }

}