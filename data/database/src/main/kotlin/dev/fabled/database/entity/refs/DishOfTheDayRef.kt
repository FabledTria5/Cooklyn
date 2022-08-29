package dev.fabled.database.entity.refs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "dish_of_the_day_table")
data class DishOfTheDayRef(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDate = LocalDate.now()
)
