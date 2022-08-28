package dev.fabled.cooklyn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.fabled.domain.repository.FiltersRepository
import dev.fabled.domain.repository.authentication.AuthRepository
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import dev.fabled.domain.repository.recipes.RecipesRepository
import dev.fabled.frameworks.preferences.AppPreferencesRepositoryImpl
import dev.fabled.repository.repository.authentication.AuthRepositoryImpl
import dev.fabled.repository.repository.filters.FiltersRepositoryImpl
import dev.fabled.repository.repository.recipes.RecipesRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface BindingModule {

    @Binds
    @ViewModelScoped
    fun bindPreferencesRepository(
        appPreferencesRepositoryImpl: AppPreferencesRepositoryImpl
    ): AppPreferencesRepository

    @Binds
    @ViewModelScoped
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    fun bindFiltersRepository(filtersRepositoryImpl: FiltersRepositoryImpl): FiltersRepository

    @Binds
    @ViewModelScoped
    fun bindRecipesRepository(recipesRepositoryImpl: RecipesRepositoryImpl): RecipesRepository

}