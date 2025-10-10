package com.it.userdirectory.data.model.users


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerialName("bs")
    val bs: String?,
    @SerialName("catchPhrase")
    val catchPhrase: String?,
    @SerialName("name")
    val name: String?
)