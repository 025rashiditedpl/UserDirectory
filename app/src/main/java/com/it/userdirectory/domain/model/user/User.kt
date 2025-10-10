package com.it.userdirectory.domain.model.user

import com.it.userdirectory.data.model.users.Address
import com.it.userdirectory.data.model.users.Company
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("username")
    val username: String,
    @SerialName("website")
    val website: String,
    @SerialName("address")
    val address: Address?,
    @SerialName("company")
    val company: Company?
)