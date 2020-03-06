package com.ejrgeek.wikigetfilmes.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.ejrgeek.wikigetfilmes.R
import com.ejrgeek.wikigetfilmes.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infalter = menuInflater
        infalter.inflate(R.menu.search_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchView.onActionViewCollapsed()

                Toast.makeText(this@MainActivity, "$query", Toast.LENGTH_SHORT).show()

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Toast.makeText(this@MainActivity, "$query", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}
