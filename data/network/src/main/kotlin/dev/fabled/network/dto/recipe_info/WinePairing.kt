package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class WinePairing(
    @SerialName("pairedWines")
    val pairedWines: List<String>,
    @SerialName("pairingText")
    val pairingText: String,
    @SerialName("productMatches")
    val productMatches: List<ProductMatche>
)