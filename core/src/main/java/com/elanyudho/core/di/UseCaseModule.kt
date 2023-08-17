package com.elanyudho.core.di

import com.elanyudho.core.model.repository.FavoriteRepository
import com.elanyudho.core.model.repository.HomeRepository
import com.elanyudho.core.model.usecase.detail.GetDetailGameUseCase
import com.elanyudho.core.model.usecase.favorite.DeleteFavoriteGameUseCase
import com.elanyudho.core.model.usecase.favorite.GetFavoriteGamesUseCase
import com.elanyudho.core.model.usecase.favorite.InsertFavoriteGameUseCase
import com.elanyudho.core.model.usecase.home.GetGamesUseCase
import com.elanyudho.core.model.usecase.home.GetSearchGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {
    @Provides
    @ActivityScoped
    fun provideGetGamesUseCase(repository: HomeRepository) = GetGamesUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideDetailGetGameUseCase(repository: HomeRepository) = GetDetailGameUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideGetSearchGamesUseCase(repository: HomeRepository) = GetSearchGamesUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideGetFavoriteGamesUseCase(repository: FavoriteRepository) = GetFavoriteGamesUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideInsertFavoriteGameUseCase(repository: FavoriteRepository) = InsertFavoriteGameUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideDeleteFavoriteGameUseCase(repository: FavoriteRepository) = DeleteFavoriteGameUseCase(repository)
}

