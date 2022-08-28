package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CaloricBreakdown(
    @SerialName("percentCarbs")
    val percentCarbs: Double,
    @SerialName("percentFat")
    val percentFat: Double,
    @SerialName("percentProtein")
    val percentProtein: Double
)