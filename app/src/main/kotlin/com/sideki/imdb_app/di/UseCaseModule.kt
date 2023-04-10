package com.sideki.imdb_app.di

import com.sideki.imdb_app.domain.AccountRepository
import com.sideki.imdb_app.domain.use_case.ChangePasswordUseCase
import com.sideki.imdb_app.domain.use_case.GetAccountUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetAccountUseCase(repository: AccountRepository): GetAccountUseCase = GetAccountUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideChangePasswordUseCase(repository: AccountRepository): ChangePasswordUseCase =
        ChangePasswordUseCase(repository)
}
