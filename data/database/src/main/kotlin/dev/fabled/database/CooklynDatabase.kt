package dev.fabled.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.fabled.database.converters.LocalDateConverter
import dev.fabled.database.dao.RecipesDao
import dev.fabled.database.entity.IngredientEntity
import dev.fabled.database.entity.NutrientEntity
import dev.fabled.database.entity.RecipeEntity
import dev.fabled.database.entity.RecipeIngredientEntity
import dev.fabled.database.entity.refs.DishOfTheDayRef

@Database(
    entities = [
        RecipeEntity::class,
        NutrientEntity::class,
        RecipeIngredientEntity::class,
        DishOfTheDayRef::class,
        IngredientEntity::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(LocalDateConverter::class)
abstract class CooklynDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}