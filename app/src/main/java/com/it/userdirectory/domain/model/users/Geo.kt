package com.it.userdirectory.domain.model.users


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    @SerialName("lat")
    val lat: String?,
    @SerialName("lng")
    val lng: String?
)