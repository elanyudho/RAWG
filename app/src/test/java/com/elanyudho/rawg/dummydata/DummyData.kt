package com.elanyudho.rawg.dummydata

import com.elanyudho.core.model.model.Game

object DummyData {
    fun generateDummyGameEntity(): List<Game> {
        val games = ArrayList<Game>()
        for (i in 0..10) {
            val news = Game(
                "3498",
                "Grand Theft Auto V",
                "2013-09-17",
                "4.6",
                "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"
            )
            games.add(news)
        }
        return games
    }
}