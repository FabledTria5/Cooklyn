package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ProductMatche(
    @SerialName("averageRating")
    val averageRating: Double,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("link")
    val link: String,
    @SerialName("price")
    val price: String,
    @SerialName("ratingCount")
    val ratingCount: Double,
    @SerialName("score")
    val score: Double,
    @SerialName("title")
    val title: String
)