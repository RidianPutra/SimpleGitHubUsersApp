package com.ridianputra.simplegithubusersapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import com.ridianputra.simplegithubusersapp.R
import com.ridianputra.core.domain.model.GitHubUsers
import com.ridianputra.simplegithubusersapp.databinding.ActivityDetailUserBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailUserViewModel: DetailUsersViewModel by viewModel()
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailUser = intent.getParcelableExtra<GitHubUsers>(EXTRA_DATA)
        showDetailUser(detailUser)
    }

    private fun showDetailUser(detailUser: GitHubUsers?) {
        detailUser?.let {
            binding.apply {
                nameDetail.text = getString(R.string.name_detail, it.login)
                githubUrl.text = it.htmlUrl
                reposUrl.text = it.reposUrl
                organizationsUrl.text = it.organizationsUrl
                subscriptionUrl.text = it.subscriptionsUrl
            }

            Glide.with(this@DetailUserActivity)
                .load(it.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.avatarDetail)

            var statusFavorite = detailUser.isFavorite
            setStatusFavorite(statusFavorite)
            binding.favoriteFab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailUserViewModel.setFavoriteUser(detailUser, statusFavorite)
                setStatusFavorite(statusFavorite)
                setDisplaySnackBar(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favoriteFab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.favoriteFab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_unfavorite
                )
            )
        }
    }

    private fun setDisplaySnackBar(statusFavorite: Boolean) {
        if (statusFavorite) {
            Snackbar.make(
                binding.favoriteFab,
                getString(R.string.user_favorite),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                binding.favoriteFab,
                getString(R.string.user_unfavorite),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
