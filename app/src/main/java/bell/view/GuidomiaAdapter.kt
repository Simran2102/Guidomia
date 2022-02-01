package bell.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import bell.models.CarListModelItem
import com.bell.sample.app.guidomia.R
import com.bell.sample.app.guidomia.databinding.CarParentBinding

class GuidomiaAdapter(
    private val carList: List<CarListModelItem>,
    private val context: Context
) : RecyclerView.Adapter<GuidomiaAdapter.GuidomiaViewHolder>(), Filterable {

    var carListFilter = ArrayList<CarListModelItem>()

    override fun getItemCount() = carListFilter.size

    init {
        carListFilter = carList as ArrayList<CarListModelItem>
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    carListFilter = carList as ArrayList<CarListModelItem>
                } else {
                    val resultList = ArrayList<CarListModelItem>()
                    for (row in carList) {
                        if (row.make.toLowerCase().contains(constraint.toString().toLowerCase()) ||
                            row.model.toLowerCase().contains(constraint.toString().toLowerCase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    carListFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = carListFilter
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                carListFilter = results?.values as ArrayList<CarListModelItem>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GuidomiaViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.car_parent,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: GuidomiaViewHolder, position: Int) {

        carListFilter[position].carImg =
            context.resources.obtainTypedArray(R.array.list_of_cars).getResourceId(position, 0)

        holder.recyclerviewMovieBinding.vehicle = carListFilter[position]

        var prosConsListAdapter = ProsConsListAdapter(carListFilter[position].prosList)
        holder.recyclerviewMovieBinding.prosList.adapter = prosConsListAdapter

        prosConsListAdapter = ProsConsListAdapter(carListFilter[position].consList)
        holder.recyclerviewMovieBinding.consList.adapter = prosConsListAdapter

        val isVisible: Boolean = carListFilter[position].visibility
        holder.recyclerviewMovieBinding.expandedView.visibility =
            if (isVisible) View.VISIBLE else View.GONE

        holder.recyclerviewMovieBinding.itemLayout.setOnClickListener {
            carListFilter[position].visibility = !carList[position].visibility
            notifyItemChanged(position)
        }
    }

    inner class GuidomiaViewHolder(
        val recyclerviewMovieBinding: CarParentBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)

}
