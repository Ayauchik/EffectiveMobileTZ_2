package kz.tz.data.network.mapper

import kz.tz.data.network.response.Button

class ButtonMapper {
    fun fromRemoteToDomain(button: Button): String{
        return button.text
    }
}