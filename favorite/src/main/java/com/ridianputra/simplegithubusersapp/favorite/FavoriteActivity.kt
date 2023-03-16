package com.ridianputra.simplegithubusersapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridianputra.core.ui.GitHubUsersListAdapter
import com.ridianputra.simplegithubusersapp.detail.DetailUserActivity
import com.ridianputra.simplegithubusersapp.di.favoriteModule
import com.ridianputra.simplegithubusersapp.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val usersAdapter = GitHubUsersListAdapter() { selectedData ->
            val intent = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteUsers.observe(this) { dataUsers ->
            usersAdapter.submitList(dataUsers)
            binding.viewEmpty.root.visibility =
                if (dataUsers.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = usersAdapter
        }
    }
}
