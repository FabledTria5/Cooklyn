package dev.fabled.cooklyn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import dev.fabled.frameworks.preferences.AppPreferencesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface BindingModule {

    @Binds
    fun bindPreferencesRepository(
        appPreferencesRepositoryImpl: AppPreferencesRepositoryImpl
    ): AppPreferencesRepository

}