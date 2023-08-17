package com.elanyudho.rawg.presentation.detailgame

import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.core.model.model.DetailGame
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.util.extension.glide
import com.elanyudho.rawg.R
import com.elanyudho.rawg.databinding.ActivityDetailGameBinding
import com.sentuh.core.util.exception.Failure
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailGameActivity : BaseActivityBinding<ActivityDetailGameBinding>(),
    Observer<DetailGameViewModel.DetailGameUiState> {

    @Inject
    lateinit var detailGameViewModel: DetailGameViewModel

    private lateinit var id: String
    private var isFavorite: Boolean = false
    private lateinit var game: Game

    override val bindingInflater: (LayoutInflater) -> ActivityDetailGameBinding
        get() = { ActivityDetailGameBinding.inflate(layoutInflater) }

    override fun setupView() {
        id = intent.getStringExtra("id").toString()
        initViewModel()
        setHeader()
    }

    override fun onChanged(state: DetailGameViewModel.DetailGameUiState?) {
        when (state) {
            is DetailGameViewModel.DetailGameUiState.Loading -> {
                startLoading()
            }

            is DetailGameViewModel.DetailGameUiState.DetailGameLoaded -> {
                stopLoading()
                showDetail(state.data)
            }

            is DetailGameViewModel.DetailGameUiState.IsFavorite -> {
                isFavorite = state.isFavorite
                setBtnFavoriteState(isFavorite)
            }

            is DetailGameViewModel.DetailGameUiState.FailedLoaded -> {
                stopLoading()
                handleFailure(state.failure)
            }

            else -> {}
        }
    }

    private fun initViewModel() {
        detailGameViewModel.uiState.observe(this, this)
        detailGameViewModel.getDetailGame(id)
        detailGameViewModel.isFavoriteGame(id)
    }

    private fun showDetail(data: DetailGame) {
        with(binding) {
            imageGame.glide(this@DetailGameActivity, data.image)
            tvName.text = data.name
            tvDesc.text = data.desc
            tvRating.text = data.rating
            tvPublisher.text = data.publisher
            tvReleased.text = "Released date ${data.releasedDate}"
            tvPlayingTime.text = "${data.playTime} played"
        }

        game = Game(data.id, data.name, data.releasedDate, data.rating, data.image)
    }

    private fun startLoading() {
        binding.loadinView.parent.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.loadinView.parent.visibility = View.GONE
    }

    private fun handleFailure(failure: Failure) {
        Toast.makeText(this, failure.throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun setHeader() {
        with(binding) {
            headerDetail.tvHeader.text = getString(R.string.detail_game)
            headerDetail.btnBack.setOnClickListener { onBackPressed() }
            headerDetail.imgFavorite.setOnClickListener {
                setBtnFavoriteState(!isFavorite)
                updateFavoriteData(!isFavorite)
                isFavorite = !isFavorite
            }
        }
    }

    private fun setBtnFavoriteState(isFavorite: Boolean) {
        with(binding) {

            if (isFavorite) {
                headerDetail.imgFavorite.setColorFilter(
                    ContextCompat.getColor(
                        this@DetailGameActivity,
                        R.color.red
                    ), PorterDuff.Mode.SRC_IN
                )
            } else {
                headerDetail.imgFavorite.setColorFilter(
                    ContextCompat.getColor(
                        this@DetailGameActivity,
                        R.color.white
                    ), PorterDuff.Mode.SRC_IN
                )

            }
        }
    }

    private fun updateFavoriteData(isFavorite: Boolean) {
        if (isFavorite) {
            detailGameViewModel.insertFavorite(game)
        } else {
            detailGameViewModel.deleteFavorite(game.id)
        }
    }

}