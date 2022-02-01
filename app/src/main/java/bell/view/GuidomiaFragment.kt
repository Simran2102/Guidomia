package bell.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bell.repositories.VehicleRepository
import bell.viewmodel.VehicleDetailViewModelFactory
import bell.viewmodel.GuidomiaViewModel
import com.bell.sample.app.guidomia.R
import com.bell.sample.app.guidomia.databinding.GuidomiaActivityMainBinding

class GuidomiaFragment : Fragment() {

    private var viewModel: GuidomiaViewModel ?= null
    private var factory: VehicleDetailViewModelFactory? = null

    private var _binding: GuidomiaActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GuidomiaActivityMainBinding.inflate(inflater, container, false)
        val repository = VehicleRepository(requireContext())
        factory = VehicleDetailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory!!).get(GuidomiaViewModel::class.java)
        viewModel!!.getVehicleDetails()


        viewModel!!.vehicleDetails.observe(viewLifecycleOwner, Observer { vehicleDetails ->
            binding.mainListView.recyclerViewVehicleDetails.also {
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


        return binding.root
    }


}