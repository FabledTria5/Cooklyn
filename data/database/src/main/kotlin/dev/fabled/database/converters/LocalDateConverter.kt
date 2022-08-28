package dev.fabled.database.converters

import androidx.room.TypeConverter
import java.time.LocalDate

object LocalDateConverter {

    @TypeConverter
    fun fromEntity(date: String): LocalDate = LocalDate.parse(date)

    @TypeConverter
    fun toEntity(localDate: LocalDate): String = localDate.toString()

}