package bell.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bell.sample.app.guidomia.R

class ProsConsListAdapter() :
    RecyclerView.Adapter<ProsConsListAdapter.ViewHolder>() {

    private var pList: List<String>?= null

    constructor(prosList: List<String>) : this() {
        pList = prosList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pros_cons_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = pList?.get(position)

        if (itemsViewModel != null) {
            if (itemsViewModel.isNotEmpty())
                holder.textView.text = itemsViewModel
        }
    }

    override fun getItemCount(): Int {
        return pList?.size ?: 0
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.pros_cons_text)
    }
}
