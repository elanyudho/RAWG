package com.elanyudho.rawg.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elanyudho.core.abstraction.BaseViewHolder
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.util.extension.glide
import com.elanyudho.rawg.databinding.ItemGameBinding

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.Holder>() {

    private var listData = ArrayList<Game>()
    private var onClick: ((data: Game) -> Unit?)? = null

    fun submitList(newList: List<Game>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.Holder {
        return Holder(ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: FavoriteAdapter.Holder,
        position: Int
    ) {
        holder.bind(data = listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder (itemView: ItemGameBinding) :
        BaseViewHolder<Game, ItemGameBinding>(itemView){
        override fun bind(data: Game) {
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

    fun setOnClickData(onClick: (data: Game) -> Unit) {
        this.onClick = onClick
    }


}