package dev.fabled.network.dto.random_recipe


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Equipment(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("localizedName")
    val localizedName: String,
    @SerialName("name")
    val name: String
)