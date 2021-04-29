package com.sample.dextestapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import com.sample.data.CredentialsDataSource
import com.sample.data.PostDataSource
import com.sample.data.UserDataSource
import com.sample.framework.CredentialsDataSourceImpl
import com.sample.framework.PostDataSourceImpl
import com.sample.framework.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {

    @Provides
    fun provideSecurePreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        val sharedPrefsFile: String = "Secure_preferences"
        val mainKeyAlias: String = "cred_key"

        return EncryptedSharedPreferences.create(
            sharedPrefsFile,
            mainKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    fun provideCredentialsDataSourceImpl(
        sharedPreferences: SharedPreferences
    ): CredentialsDataSourceImpl {
        return CredentialsDataSourceImpl(sharedPreferences)
    }

    @Provides
    fun providePostsDataSourceImpl(
    ): PostDataSourceImpl {
        return PostDataSourceImpl()
    }

    @Provides
    fun provideUserDataSourceImpl(
    ): UserDataSourceImpl {
        return UserDataSourceImpl()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class FrameworkBinds {

    @Binds
    abstract fun bindCredentialsDataSource(
        credentialsDataSourceImpl: CredentialsDataSourceImpl
    ): CredentialsDataSource

    @Binds
    abstract fun bindPostsDataSource(
        postDataSourceImpl: PostDataSourceImpl
    ): PostDataSource

    @Binds
    abstract fun bindUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource
}