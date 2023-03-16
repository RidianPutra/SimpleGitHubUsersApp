package com.ridianputra.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ridianputra.core.databinding.ItemListUserBinding
import com.ridianputra.core.domain.model.GitHubUsers

class GitHubUsersListAdapter(private val onClick: (GitHubUsers) -> Unit) :
    ListAdapter<GitHubUsers, GitHubUsersListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onClick(data)
        }
    }

    class MyViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GitHubUsers) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(avatar)
                tvItemUsername.text = data.login
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GitHubUsers> =
            object : DiffUtil.ItemCallback<GitHubUsers>() {
                override fun areItemsTheSame(oldItem: GitHubUsers, newItem: GitHubUsers): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: GitHubUsers,
                    newItem: GitHubUsers
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}