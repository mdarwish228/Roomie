package com.darwish.roomie.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.darwish.roomie.R
import com.darwish.roomie.presentation.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import com.darwish.roomie.presentation.group.GroupActivity

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null || auth.currentUser?.reload() == null) {
            navigateToLoginActivity()
        } else {
            setupBottomNav()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.user -> {
            navigateToGroupActivity()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setupBottomNav() {
        val navController = Navigation.findNavController(this, R.id.main_fragment)
        navigation.setupWithNavController(navController)
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToGroupActivity() {
        val intent = Intent(this, GroupActivity::class.java)
        startActivity(intent)
    }
}
