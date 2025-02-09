package kz.tz.data.network.mapper

import kz.tz.data.network.response.Address
import kz.tz.domain.models.AddressModel

class AddressMapper {
    fun fromRemoteToDomain(address: Address): AddressModel {
        return AddressModel(
            house = address.house,
            street = address.street,
            town = address.town
        )
    }
}