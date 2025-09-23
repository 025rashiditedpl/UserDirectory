package com.it.userdirectory.domain.model.users


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseItem(
    @SerialName("address")
    val address: Address?,
    @SerialName("company")
    val company: Company?,
    @SerialName("email")
    val email: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("phone")
    val phone: String?,
    @SerialName("username")
    val username: String?,
    @SerialName("website")
    val website: String?
)