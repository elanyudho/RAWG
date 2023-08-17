package com.elanyudho.core.data.remote.mapper

import com.elanyudho.core.abstraction.BaseMapper
import com.elanyudho.core.data.remote.response.DetailGameResponse
import com.elanyudho.core.model.model.DetailGame

class DetailGameMapper : BaseMapper<DetailGameResponse, DetailGame> {

    override fun mapToDomain(raw: DetailGameResponse): DetailGame {
        return DetailGame(
            raw.id.toString(),
            raw.name.toString(),
            raw.released.toString(),
            raw.backgroundImage.toString(),
            raw.description.toString(),
            raw.publishers?.get(0)?.name.toString(),
            raw.playtime.toString(),
            raw.rating.toString()
        )
    }

    override fun mapToRaw(domain: DetailGame): DetailGameResponse {
        return DetailGameResponse()
    }


}