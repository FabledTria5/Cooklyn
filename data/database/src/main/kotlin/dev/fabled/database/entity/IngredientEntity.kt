package dev.fabled.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients_table")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = -1,
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    @ColumnInfo(name = "ingredient_name")
    val ingredientName: String,
    @ColumnInfo(name = "amount")
    val amount: String,
    @ColumnInfo(name = "unit")
    val unit: String,
    @ColumnInfo(name = "ingredient_image")
    val ingredientImage: String
)