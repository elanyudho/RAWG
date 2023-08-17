package com.elanyudho.core.data.remote.mapper

import com.elanyudho.core.abstraction.BaseMapper
import com.elanyudho.core.data.remote.response.GamesResponse
import com.elanyudho.core.model.model.Game

class GamesMapper : BaseMapper<GamesResponse, List<Game>> {

    override fun mapToDomain(raw: GamesResponse): List<Game> {
        return raw.results?.map {
            Game(it?.id.toString(), it?.name.toString(), it?.released.toString(), it?.rating.toString(), it?.backgroundImage.toString())
        } ?: emptyList()
    }

    override fun mapToRaw(domain: List<Game>): GamesResponse {
        return GamesResponse()
    }


}