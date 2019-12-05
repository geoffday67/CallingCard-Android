package com.sullenart.callingcard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_phone -> phoneCall()
            R.id.action_email -> sendEmail()
            R.id.action_source -> showSource()
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun sendEmail() {
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(resources.getString(R.string.geoff_email)))
            putExtra(Intent.EXTRA_SUBJECT, "Enquiry from CallingCard")
            resolveActivity(packageManager)?.let {
                startActivity(this)
            }
        }
    }

    private fun phoneCall() {
        Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:" + resources.getString(R.string.geoff_phone))
            resolveActivity(packageManager)?.let {
                startActivity(this)
            }
        }
    }

    private fun showSource() {
        val uri = Uri.parse("https://github.com/geoffday67/callingcard-android?files=1")
        Intent(Intent.ACTION_VIEW, uri).apply {
            startActivity(this)
        }
    }
}
