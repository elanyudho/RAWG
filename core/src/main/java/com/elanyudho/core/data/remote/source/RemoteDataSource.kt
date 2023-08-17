package com.elanyudho.core.data.remote.source

import com.elanyudho.core.data.remote.response.DetailGameResponse
import com.elanyudho.core.data.remote.response.GamesResponse
import com.elanyudho.core.data.remote.service.ApiService
import com.elanyudho.core.pref.EncryptedPreferences
import com.elanyudho.core.util.pagination.PagingConstant
import com.elanyudho.core.util.vo.Either
import com.sentuh.core.data.remote.source.RemoteSafeRequest
import com.sentuh.core.util.exception.Failure
import javax.inject.Inject

class RemoteDataSource
@Inject constructor(private val api: ApiService, private val encryptedPreferences: EncryptedPreferences) : RemoteSafeRequest() {

    suspend fun getGames(page: String): Either<Failure, GamesResponse> =
        request {
            api.getGames(encryptedPreferences.encryptedToken, page, PagingConstant.BATCH_SIZE)
        }

    suspend fun getDetailGame(id: String): Either<Failure, DetailGameResponse> =
        request {
            api.getDetailGame(id, encryptedPreferences.encryptedToken)
        }

    suspend fun getSearchGames(page: String, name: String): Either<Failure, GamesResponse> =
        request {
            api.getSearchGames(encryptedPreferences.encryptedToken, page, PagingConstant.BATCH_SIZE, name)
        }

}