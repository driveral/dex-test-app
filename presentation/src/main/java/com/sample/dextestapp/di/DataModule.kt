package com.sample.dextestapp.di

import com.sample.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCredentialsRepository(
        credentialsDataSource: CredentialsDataSource
    ): CredentialsRepository {
        return CredentialsRepository(credentialsDataSource)
    }

    @Provides
    @Singleton
    fun providePostsRepository(
        postDataSource: PostDataSource
    ): PostRepository {
        return PostRepository(postDataSource)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDataSource: UserDataSource
    ): UserRepository {
        return UserRepository(userDataSource)
    }
}