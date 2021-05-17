package com.example.sandeep.di

import com.example.sandeep.repo.MainRepoSource
import com.example.sandeep.repo.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sandeep Sankla
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun providesMainRepository(repository: MainRepository) : MainRepoSource


}