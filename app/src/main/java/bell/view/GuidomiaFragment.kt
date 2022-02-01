package bell.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bell.repositories.VehicleRepository
import bell.viewmodel.GuidomiaViewModel
import bell.viewmodel.VehicleDetailViewModelFactory
import com.bell.sample.app.guidomia.R
import kotlinx.android.synthetic.main.guidomia_list_fragment.*

class GuidomiaFragment : Fragment() {

    private var viewModel: GuidomiaViewModel ?= null
    private var factory: VehicleDetailViewModelFactory?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.guidomia_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val repository = VehicleRepository(requireContext())
        factory = VehicleDetailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory!!).get(GuidomiaViewModel::class.java)
        viewModel!!.getVehicleDetails()

        viewModel!!.vehicleDetails.observe(viewLifecycleOwner, Observer { vehicleDetails ->
            recycler_view_vehicle_details.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = GuidomiaAdapter(vehicleDetails, requireContext())

                (view!!.findViewById(R.id.seacrh_make) as SearchView?)?.setOnQueryTextListener(
                    object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            (it.adapter as GuidomiaAdapter).filter.filter(query)
                            return true
                        }
                    })


                (view!!.findViewById(R.id.seacr_model) as SearchView?)?.setOnQueryTextListener(
                    object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            (it.adapter as GuidomiaAdapter).filter.filter(query)
                            return true
                        }
                    })

            }
        })
    }
}