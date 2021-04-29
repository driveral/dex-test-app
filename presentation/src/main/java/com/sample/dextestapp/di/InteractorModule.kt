package com.sample.dextestapp.di

import com.sample.data.CredentialsRepository
import com.sample.data.PostRepository
import com.sample.data.UserRepository
import com.sample.interactor.GetPostsInteractor
import com.sample.interactor.IsUserLoggedInInteractor
import com.sample.interactor.LoginInteractor
import com.sample.interactor.LogoutInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class InteractorModule {

    @Provides
    fun provideGetPostInteractor(
        credentialsRepository: CredentialsRepository,
        postRepository: PostRepository
    ): GetPostsInteractor {
        return GetPostsInteractor(credentialsRepository, postRepository)
    }

    @Provides
    fun provideIsUserLoggedInInteractor(
        credentialsRepository: CredentialsRepository,
        userRepository: UserRepository
    ): IsUserLoggedInInteractor {
        return IsUserLoggedInInteractor(credentialsRepository, userRepository)
    }

    @Provides
    fun provideLoginInteractor(
        credentialsRepository: CredentialsRepository,
        userRepository: UserRepository
    ): LoginInteractor {
        return LoginInteractor(userRepository, credentialsRepository)
    }

    @Provides
    fun provideLogoutInteractor(
        credentialsRepository: CredentialsRepository,
        userRepository: UserRepository
    ): LogoutInteractor {
        return LogoutInteractor(userRepository, credentialsRepository)
    }
}