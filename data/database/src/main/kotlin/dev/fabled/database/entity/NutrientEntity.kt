package dev.fabled.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrients_table")
data class NutrientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = -1,
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    @ColumnInfo(name = "nutrient_name")
    val ingredientName: String,
    @ColumnInfo(name = "amount")
    val amount: String,
    @ColumnInfo(name = "unit")
    val unit: String
)
