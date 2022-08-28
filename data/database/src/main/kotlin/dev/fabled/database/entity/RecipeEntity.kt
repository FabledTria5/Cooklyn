package dev.fabled.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_table")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = -1,
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    @ColumnInfo(name = "recipe_name")
    val recipeName: String,
    @ColumnInfo(name = "recipe_image")
    val recipeImage: String?,
    @ColumnInfo(name = "recipe_time")
    val recipeTime: Int,
    @ColumnInfo(name = "servings")
    val servings: Int,
)
