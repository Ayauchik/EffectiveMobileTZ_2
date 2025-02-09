package kz.tz.data.network.mockAPI

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kz.tz.data.network.response.GetInformationResponse
import org.json.JSONObject
import java.io.IOException

class PlaceholderServiceImpl(
    private val context: Context
) : MockPlaceholderService {
    override suspend fun getInformation(): GetInformationResponse {
        Log.e("placeholder", "getting vacancy model")

        val jsonString = loadJSONFromAsset(context, "mock.json")
        Log.e("placeholder", "after getting json")
        if (jsonString != null) {
            Log.e("placeholder", jsonString)
        }

        val gson = Gson()
        val responseType = object : TypeToken<GetInformationResponse>() {}.type
        val response: GetInformationResponse = gson.fromJson(jsonString, responseType)

       // Log.e("placeholder", response.offers[1].title)

        return response
    }
}

fun loadJSONFromAsset(context: Context, fileName: String): String? {
    return try {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        Log.e("placeholder", "JSON String: $jsonString")
        jsonString
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

