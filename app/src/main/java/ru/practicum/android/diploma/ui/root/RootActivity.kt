package ru.practicum.android.diploma.ui.root

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.HH_ACCESS_TOKEN)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavBar.setupWithNavController(navController)

        // На будущее когда потребуется убирать строку команд в заисимости от экрана

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.playerFragment -> {
//                    bottomNavBar.visibility = View.GONE
//                }
//                R.id.newPlaylistFragment -> {
//                    bottomNavBar.visibility = View.GONE
//                }
//                R.id.showAlbumFragment ->{
//                    bottomNavBar.visibility = View.GONE
//                }
//                else -> {
//                    bottomNavBar.visibility = View.VISIBLE
//                }
//            }
//        }

    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

}