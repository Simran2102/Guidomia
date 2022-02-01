package bell.view

import android.view.View
import bell.data.models.CarListModelItem

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, carListModelItem: CarListModelItem, position:Int)
}