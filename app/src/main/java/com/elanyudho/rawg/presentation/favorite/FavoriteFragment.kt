package com.elanyudho.rawg.presentation.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.elanyudho.core.abstraction.BaseFragmentBinding
import com.elanyudho.rawg.databinding.FragmentFavoriteBinding
import com.elanyudho.rawg.presentation.detailgame.DetailGameActivity
import com.elanyudho.rawg.presentation.favorite.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseFragmentBinding<FragmentFavoriteBinding>(), Observer<FavoriteViewModel.FavoriteUiState> {

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel

    private val favoriteAdapter: FavoriteAdapter by lazy { FavoriteAdapter() }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding
        get() = { layoutInflater, viewGroup, b ->
            FragmentFavoriteBinding.inflate(layoutInflater, viewGroup, b)
        }

    override fun setupView() {
        callOnceWhenCreated {
            initViewModel()
            setAdapter()
            setAction()
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getFavoriteGames()
        binding.svGame.setQuery("")
    }

    override fun onChanged(state: FavoriteViewModel.FavoriteUiState?) {
        when(state) {
            is FavoriteViewModel.FavoriteUiState.GamesLoaded -> {
                stopLoading()
                if (state.data.isEmpty()) {
                    showEmptyData()
                } else {
                    hideEmptyData()
                    favoriteAdapter.submitList(state.data)
                }
            }
            is FavoriteViewModel.FavoriteUiState.Loading -> {
                initialLoading()
            }
            else -> {}
        }
    }

    private fun initViewModel() {
        favoriteViewModel.uiState.observe(viewLifecycleOwner, this)
    }

    private fun setAction() {
        with(binding) {
            refreshLayout.setOnRefreshListener {
                favoriteViewModel.getFavoriteGames()
                binding.svGame.setQuery("")
                hideKeyboard()
            }

            svGame.setOnQueryChangeListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.toString().isNotEmpty()) {

                        favoriteViewModel.getSearchFavoriteGameByName(newText!!)

                    } else {
                        favoriteViewModel.getFavoriteGames()
                    }
                    return true
                }

            })
        }
    }

    private fun setAdapter() {
        with(binding.rvGames) {
            adapter = favoriteAdapter
            setHasFixedSize(true)

            favoriteAdapter.setOnClickData {
                hideKeyboard()
                val intent = Intent(requireActivity(), DetailGameActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
        }
    }

    private fun showEmptyData() {
        binding.emptyDataView.parent.visibility = View.VISIBLE
    }

    private fun hideEmptyData() {
        binding.emptyDataView.parent.visibility = View.GONE
    }

    private fun initialLoading() {
        binding.progressCircular.visibility = View.VISIBLE
        binding.rvGames.visibility = View.GONE
    }

    private fun stopLoading() {
        binding.rvGames.visibility = View.VISIBLE
        binding.progressCircular.visibility = View.GONE
        binding.refreshLayout.isRefreshing = false
    }

}