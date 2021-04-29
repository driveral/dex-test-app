package com.sample.dextestapp.di

import com.sample.data.CredentialsDataSource
import com.sample.data.PostDataSource
import com.sample.data.UserDataSource
import com.sample.framework.CredentialsDataSourceImpl
import com.sample.framework.PostDataSourceImpl
import com.sample.framework.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


}

