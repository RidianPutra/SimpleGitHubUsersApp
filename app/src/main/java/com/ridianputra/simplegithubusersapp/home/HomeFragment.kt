package com.ridianputra.simplegithubusersapp.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridianputra.simplegithubusersapp.R
import com.ridianputra.core.data.Resource
import com.ridianputra.core.ui.GitHubUsersListAdapter
import com.ridianputra.simplegithubusersapp.databinding.FragmentHomeBinding
import com.ridianputra.simplegithubusersapp.detail.DetailUserActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val usersAdapter = GitHubUsersListAdapter { selectedData ->
                val intent = Intent(activity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.gitHubUsers.observe(viewLifecycleOwner) { users ->
                if (users != null) {
                    when (users) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            usersAdapter.submitList(users.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                users.message ?: getString(R.string.went_wrong)
                        }
                    }
                }
            }

            with(binding.rvUsers) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = usersAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
