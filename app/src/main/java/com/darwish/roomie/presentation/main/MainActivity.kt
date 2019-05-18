package com.darwish.roomie.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.darwish.roomie.data.database.ConfigDatabase
import com.darwish.roomie.R
import com.darwish.roomie.presentation.group.GroupActivity
import com.darwish.roomie.presentation.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Allowing main thread executions because the configuration variables
        // in this database are needed to create this activities child fragment views
        val configDatabase = Room.databaseBuilder(
            applicationContext,
            ConfigDatabase::class.java, "config-database"
        ).allowMainThreadQueries().build()

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null || auth.currentUser?.reload() == null) {
            navigateToLoginActivity()
        } else {
            loadGroupInfo(configDatabase)
            setupBottomNav()
        }
    }

    private fun loadGroupInfo(configDatabase: ConfigDatabase) {
        if (intent.getStringExtra("groupId") == null) {
            val groupIdConfig = configDatabase.configDao().get("groupId")

            if (groupIdConfig == null) {
                navigateToGroupActivity()
            } else {
                intent.putExtra("groupId", groupIdConfig.value)
            }
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
        finish()
    }
}
