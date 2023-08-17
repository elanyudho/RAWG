package com.elanyudho.rawg.presentation.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elanyudho.core.abstraction.BaseFragmentBinding
import com.elanyudho.core.util.pagination.RecyclerViewPaginator
import com.elanyudho.rawg.databinding.FragmentHomeBinding
import com.elanyudho.rawg.presentation.detailgame.DetailGameActivity
import com.elanyudho.rawg.presentation.home.adapter.GamesAdapter
import com.sentuh.core.util.exception.Failure
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragmentBinding<FragmentHomeBinding>(), Observer<HomeViewModel.HomeUiState> {

    @Inject lateinit var homeViewModel: HomeViewModel

    private var paginator: RecyclerViewPaginator? = null

    private val gamesAdapter: GamesAdapter by lazy { GamesAdapter() }

    private var isFirstGet = true
    private var isSearchMode = false
    private lateinit var query: String

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = { layoutInflater, viewGroup, b ->
            FragmentHomeBinding.inflate(layoutInflater, viewGroup, b)
        }

    override fun setupView() {
        callOnceWhenCreated {
            initViewModel()
            setAdapter()
            setRvPagination()
            setAction()
        }

    }

    override fun onChanged(state: HomeViewModel.HomeUiState?) {
        when(state) {
            is HomeViewModel.HomeUiState.InitialLoading -> {
                initialLoading()
            }
            is HomeViewModel.HomeUiState.PagingLoading -> {
                pagingLoading()
            }
            is HomeViewModel.HomeUiState.GamesLoaded -> {
                stopLoading()
                if (state.data.isEmpty() && isFirstGet) {
                    showEmptyData()
                }else {
                    hideEmptyData()
                    gamesAdapter.appendList(state.data)
                }
            }
            is HomeViewModel.HomeUiState.FailedLoaded -> {
                stopLoading()
                handleFailure(state.failure)
            }
            else -> {}
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getGames(1L)
        binding.svGame.setQuery("")
        gamesAdapter.clearList()
        isFirstGet = true
    }

    private fun initViewModel() {
        homeViewModel.uiState.observe(this, this)
    }

    private fun setAdapter() {
        with(binding.rvGames) {
            adapter = gamesAdapter
            setHasFixedSize(true)

            gamesAdapter.setOnClickData {
                hideKeyboard()
                val intent = Intent(requireActivity(), DetailGameActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
        }
    }

    private fun setRvPagination() {
        paginator = RecyclerViewPaginator(binding.rvGames.layoutManager as LinearLayoutManager)
        paginator?.setOnLoadMoreListener { page ->
            isFirstGet = false
            if (isSearchMode) {
                homeViewModel.getSearchGames(page, query)
            } else {
                homeViewModel.getGames(page)
            }
        }
        paginator?.let { binding.rvGames.addOnScrollListener(it) }
    }

    private fun initialLoading() {
        binding.progressCircular.visibility = View.VISIBLE
        binding.rvGames.visibility = View.GONE
    }

    private fun pagingLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.rvGames.visibility = View.VISIBLE
        binding.progressCircular.visibility = View.GONE
        binding.refreshLayout.isRefreshing = false
    }

    private fun showEmptyData() {
        binding.emptyDataView.parent.visibility = View.VISIBLE
    }

    private fun hideEmptyData() {
        binding.emptyDataView.parent.visibility = View.GONE

    }

    private fun handleFailure(failure: Failure) {
        Toast.makeText(requireActivity(), failure.throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun setAction() {
        with(binding) {
            refreshLayout.setOnRefreshListener {
                isFirstGet = true
                gamesAdapter.clearList()
                homeViewModel.getGames(1L)
            }

            svGame.setOnQueryChangeListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.toString().isNotEmpty()) {
                        query = newText!!
                        homeViewModel.getSearchGames(1L, query)
                        gamesAdapter.clearList()
                        isFirstGet = true
                    } else {
                        homeViewModel.getGames(1L)
                        gamesAdapter.clearList()
                        isFirstGet = true
                    }
                    return true
                }

            })
        }
    }

}