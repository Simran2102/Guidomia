package bell.util

import android.content.Context
import android.content.res.TypedArray
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}


fun mapCarMakeModelAndImages(carList: List<TypedArray>, carMakeAndModel: List<String>): Map<String,TypedArray> {
    val map: Map<String, TypedArray> = carMakeAndModel.zip(carList).toMap()
    return map
}