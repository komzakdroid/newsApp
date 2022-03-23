package com.limuealimi.newsapp.presentation.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.limuealimi.newsapp.R
import com.limuealimi.newsapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
         menuInflater.inflate(R.menu.menu_main, menu)

         val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
         (menu.findItem(R.id.action_search)?.actionView as SearchView).apply {
             queryHint = getString(R.string.search_atcile_name)
             setSearchableInfo(searchManager.getSearchableInfo(componentName))
             setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                 override fun onQueryTextSubmit(query: String?): Boolean {
                     return true
                 }

                 override fun onQueryTextChange(newText: String?): Boolean {
                     return true
                 }
             })
         }
         return super.onCreateOptionsMenu(menu)
     }*/
}
