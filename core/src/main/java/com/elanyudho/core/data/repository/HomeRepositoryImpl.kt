package com.elanyudho.core.data.repository

import com.elanyudho.core.data.remote.mapper.DetailGameMapper
import com.elanyudho.core.data.remote.mapper.GamesMapper
import com.elanyudho.core.data.remote.source.RemoteDataSource
import com.elanyudho.core.model.model.DetailGame
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.repository.HomeRepository
import com.elanyudho.core.util.vo.Either
import com.sentuh.core.util.exception.Failure
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val gamesMapper: GamesMapper,
    private val detailGameMapper: DetailGameMapper
) : HomeRepository {

    override suspend fun getGames(page: String): Either<Failure, List<Game>> {
        return when (val response = remoteDataSource.getGames(page)) {
            is Either.Success -> {
                val data = gamesMapper.mapToDomain(response.body)
                Either.Success(data)
            }

            is Either.Error -> {
                Either.Error(response.failure)
            }
        }
    }

    override suspend fun getDetailGame(id: String): Either<Failure, DetailGame> {
        return when (val response = remoteDataSource.getDetailGame(id)) {
            is Either.Success -> {
                val data = detailGameMapper.mapToDomain(response.body)
                Either.Success(data)
            }

            is Either.Error -> {
                Either.Error(response.failure)
            }
        }
    }

    override suspend fun getSearchGames(page: String, name: String): Either<Failure, List<Game>> {
        return when (val response = remoteDataSource.getSearchGames(page, name)) {
            is Either.Success -> {
                val data = gamesMapper.mapToDomain(response.body)
                Either.Success(data)
            }

            is Either.Error -> {
                Either.Error(response.failure)
            }
        }    }

}