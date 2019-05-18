package com.darwish.roomie.presentation.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.darwish.roomie.presentation.main.MainActivity
import android.content.Intent
import androidx.room.Room
import com.darwish.roomie.data.database.ConfigDatabase
import com.darwish.roomie.presentation.group.GroupActivity
import com.darwish.roomie.presentation.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        }
    }

    private fun loadGroupInfo(configDatabase: ConfigDatabase) {
        if (intent.getStringExtra("groupId") == null) {
            val groupIdConfig = configDatabase.configDao().get("groupId")

            if (groupIdConfig == null) {
                navigateToGroupActivity()
            } else {
                navigateToMainActivity(groupIdConfig.value)
            }
        }
    }

    private fun navigateToMainActivity(groupId: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("groupId", groupId)
        startActivity(intent)
        finish()
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
