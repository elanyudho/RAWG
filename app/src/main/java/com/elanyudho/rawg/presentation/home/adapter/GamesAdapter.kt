package com.elanyudho.rawg.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.elanyudho.core.abstraction.BaseViewHolder
import com.elanyudho.core.abstraction.PagingRecyclerViewAdapter
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.util.extension.glide
import com.elanyudho.rawg.databinding.ItemGameBinding

class GamesAdapter : PagingRecyclerViewAdapter<GamesAdapter.GamesViewHolder, Game>() {

    private var onClick: ((Game) -> Unit)? = null

    override val holderInflater: (LayoutInflater, ViewGroup, Boolean) -> GamesAdapter.GamesViewHolder
        get() = { inflater, viewGroup, boolean ->
            GamesViewHolder(ItemGameBinding.inflate(inflater, viewGroup, boolean))
        }

    inner class GamesViewHolder(itemView: ItemGameBinding) :
        BaseViewHolder<Game, ItemGameBinding>(itemView) {
        override fun bind(data:Game) {
            with(binding) {
                tvName.text = data.name
                tvReleased.text = "Released Date ${data.releaseDate}"
                tvRating.text = data.rating
                imageGames.glide(itemView, data.image)

                root.setOnClickListener {
                    onClick?.invoke(data)

                }
            }

        }
    }

    override fun onBindViewHolder(holder: GamesAdapter.GamesViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    fun setOnClickData(onClick: (data: Game) -> Unit) {
        this.onClick = onClick
    }
}

