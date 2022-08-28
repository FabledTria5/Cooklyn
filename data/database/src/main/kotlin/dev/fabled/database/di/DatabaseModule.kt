package dev.fabled.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.fabled.database.CooklynDatabase
import dev.fabled.database.dao.RecipesDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRecipesDatabase(@ApplicationContext context: Context): CooklynDatabase = Room
        .databaseBuilder(
            context = context,
            klass = CooklynDatabase::class.java,
            name = "cooklyn_database"
        )
        .build()

    @Provides
    @Singleton
    fun provideRecipesDao(cooklynDatabase: CooklynDatabase): RecipesDao =
        cooklynDatabase.recipesDao()

}