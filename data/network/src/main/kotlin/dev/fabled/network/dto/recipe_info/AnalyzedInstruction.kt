package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class AnalyzedInstruction(
    @SerialName("name")
    val name: String,
    @SerialName("steps")
    val steps: List<Step>
)