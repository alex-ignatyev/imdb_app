package com.example.imdb_app.di

import com.example.imdb_app.data.api.ImdbApi
import com.example.imdb_app.data.db.AccountsDao
import com.example.imdb_app.data.db.DataStorePreferenceStorage
import com.example.imdb_app.data.db.MoviesDao
import com.example.imdb_app.data.repository.AccountRepositoryImpl
import com.example.imdb_app.data.repository.MovieInfoRepositoryImpl
import com.example.imdb_app.data.repository.MoviesRepositoryImpl
import com.example.imdb_app.domain.repository.AccountRepository
import com.example.imdb_app.domain.repository.MovieInfoRepository
import com.example.imdb_app.domain.repository.MoviesRepository
import com.example.imdb_app.domain.use_case.ChangePasswordUseCase
import com.example.imdb_app.domain.use_case.ClearAllMoviesUseCase
import com.example.imdb_app.domain.use_case.GetAccountUseCase
import com.example.imdb_app.domain.use_case.GetActorInfoUseCase
import com.example.imdb_app.domain.use_case.GetMovieInfoUseCase
import com.example.imdb_app.domain.use_case.GetMoviesUseCase
import com.example.imdb_app.domain.use_case.InsertAccountUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAccountRepository(accountsDao: AccountsDao): AccountRepository {
        return AccountRepositoryImpl(accountsDao)
    }

    @Provides
    @Singleton
    fun provideMovieInfoRepository(imdbApi: ImdbApi): MovieInfoRepository {
        return MovieInfoRepositoryImpl(imdbApi)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesDao: MoviesDao,
        imdbApi: ImdbApi,
        prefs: DataStorePreferenceStorage
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesDao, imdbApi, prefs)
    }

    @Provides
    @Singleton
    fun provideChangePasswordUseCase(repo: AccountRepository): ChangePasswordUseCase {
        return ChangePasswordUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetAccountUseCase(repo: AccountRepository): GetAccountUseCase {
        return GetAccountUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideInsertAccountUseCase(repo: AccountRepository): InsertAccountUseCase {
        return InsertAccountUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideClearAllMoviesUseCase(repo: MoviesRepository): ClearAllMoviesUseCase {
        return ClearAllMoviesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetActorInfoUseCase(repo: MovieInfoRepository): GetActorInfoUseCase {
        return GetActorInfoUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetMovieInfoUseCase(repo: MovieInfoRepository): GetMovieInfoUseCase {
        return GetMovieInfoUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(repo: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repo)
    }
}
