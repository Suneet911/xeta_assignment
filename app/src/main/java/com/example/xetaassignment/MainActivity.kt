package com.example.xetaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xetaassignment.adapter.RecyclerViewAdapter
import com.example.xetaassignment.apiService.ApiService
import com.example.xetaassignment.apiService.ApiUtility
import com.example.xetaassignment.databinding.ActivityMainBinding
import com.example.xetaassignment.repository.Repository
import com.example.xetaassignment.ui.HomeFragment
import com.example.xetaassignment.viewModel.MainViewModel
import com.example.xetaassignment.viewModel.ExerciseViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, HomeFragment())
        fragmentTransaction.commit()

        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home1 -> replaceFragment(HomeFragment())
                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val frameManager = supportFragmentManager
        val frameTransaction = frameManager.beginTransaction()
        frameTransaction.replace(R.id.frameLayout, fragment)
        frameTransaction.commit()
    }
}