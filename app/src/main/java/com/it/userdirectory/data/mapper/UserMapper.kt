package com.it.userdirectory.data.mapper

import com.it.userdirectory.data.model.users.Address
import com.it.userdirectory.data.model.users.Company
import com.it.userdirectory.data.model.users.Geo
import com.it.userdirectory.data.model.users.UsersResponseItem
import com.it.userdirectory.domain.model.user.User

fun UsersResponseItem.toDomain(): User {
    return User(
        id = id ?: 0,
        name = name.orEmpty(),
        email = email.orEmpty(),
        phone = phone.orEmpty(),
        username = username.orEmpty(),
        website = website.orEmpty(),
        address = address?.let {
            Address(
                street = it.street.orEmpty(),
                city = it.city.orEmpty(),
                zipcode = it.zipcode.orEmpty(),
                geo = it.geo.let {
                    Geo(
                        lat = it?.lat.orEmpty(),
                        lng = it?.lng.orEmpty()
                    )
                },
                suite = it.suite.orEmpty()
            )
        },
        company = company?.let {
            Company(
                name = it.name.orEmpty(),
                bs = it.bs.orEmpty(),
                catchPhrase = it.catchPhrase.orEmpty()

            )
        }
    )
}