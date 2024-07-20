package com.example.book.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.book.R
import com.example.book.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {


    private val scheduler = Executors.newScheduledThreadPool(1)
    private val interval: Long = 60 // 60 segundos

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }
        setupMenu()

//        scheduler.scheduleAtFixedRate({
//            runOnUiThread {
//                aposTimeLeads()
//            }
//        }, 0, interval, TimeUnit.SECONDS)

    }

    private fun setupMenu() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        navController = navHostFragment.findNavController()
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> binding.navView.visibility = View.VISIBLE
                R.id.navigation_my_books -> binding.navView.visibility = View.VISIBLE
                R.id.navigation_profile -> binding.navView.visibility = View.VISIBLE
                else -> binding.navView.visibility = View.GONE
            }

        }
    }

//    private fun aposTimeLeads(){
//        val listSolicitation = viewModel.getSolicitation()
//        if (listSolicitation.isNotEmpty()){
//            viewModel.postLeadsList(listSolicitation)
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        scheduler.shutdown()
    }
}