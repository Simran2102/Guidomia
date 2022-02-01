package bell.models

class CarListModel : ArrayList<CarListModelItem>()

data class CarListModelItem(
    val consList: List<String>,
    val customerPrice: Double,
    val make: String,
    val marketPrice: Double,
    val model: String,
    val prosList: List<String>,
    val rating: Int,
    var visibility: Boolean = false,
    var carImg : Int
)