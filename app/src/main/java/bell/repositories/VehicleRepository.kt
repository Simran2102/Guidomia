package bell.repositories

import android.content.Context
import com.google.gson.Gson
import bell.models.CarListModel
import bell.models.CarListModelItem
import bell.util.getJsonDataFromAsset

class VehicleRepository(private var context: Context) {

    fun getVehicleList(): ArrayList<CarListModelItem> {
        val jsonFileString = getJsonDataFromAsset(context, "car_list.json")
        var gson = Gson()
        return gson.fromJson(jsonFileString, CarListModel::class.java)
    }
}