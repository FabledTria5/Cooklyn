package dev.fabled.network.dto.recipes_list

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Result(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("imageType")
    val imageType: String,
    @SerialName("title")
    val title: String
)