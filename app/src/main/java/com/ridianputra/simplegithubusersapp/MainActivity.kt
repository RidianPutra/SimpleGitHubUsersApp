package com.ridianputra.simplegithubusersapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ridianputra.simplegithubusersapp.databinding.ActivityMainBinding
import com.ridianputra.simplegithubusersapp.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    val uri = Uri.parse("githubapp://favorite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    true
                }
                else -> false
            }
        }
    }
}
