package kz.tz.data.network.mockAPI

import kz.tz.data.network.response.GetInformationResponse

interface MockPlaceholderService {

    //GET() mock get, I did not include Retrofit
    suspend fun getInformation(): GetInformationResponse
}