package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Measures(
    @SerialName("metric")
    val metric: Metric,
    @SerialName("us")
    val us: Us
)