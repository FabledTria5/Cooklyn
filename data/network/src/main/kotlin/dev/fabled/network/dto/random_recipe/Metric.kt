package dev.fabled.network.dto.random_recipe


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Metric(
    @SerialName("amount")
    val amount: Double,
    @SerialName("unitLong")
    val unitLong: String,
    @SerialName("unitShort")
    val unitShort: String
)