package com.elanyudho.core.di

import com.elanyudho.core.data.repository.FavoriteRepositoryImpl
import com.elanyudho.core.data.repository.HomeRepositoryImpl
import com.elanyudho.core.model.repository.FavoriteRepository
import com.elanyudho.core.model.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    @ActivityScoped
    abstract fun bindHomeRepository(repositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @ActivityScoped
    abstract fun bindFavoriteRepository(repositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}