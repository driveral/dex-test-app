package com.sample.framework.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.sample.data.CredentialsDataSource
import com.sample.data.PostDataSource
import com.sample.data.UserDataSource
import com.sample.framework.CredentialsDataSourceImpl
import com.sample.framework.PostDataSourceImpl
import com.sample.framework.UserDataSourceImpl
import com.sample.framework.UserDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {

    @Provides
    fun provideSecurePreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        val sharedPrefsFile = "Secure_preferences"
        val mainKeyAlias = "cred_key"

        val masterKey = MasterKey.Builder(context, mainKeyAlias)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            sharedPrefsFile,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun provideSecureUserDatabase(
        @ApplicationContext context: Context
    ): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java, "user-database"
        ).build()
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