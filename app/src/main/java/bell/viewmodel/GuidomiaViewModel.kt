package bell.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bell.data.models.CarListModelItem
import bell.data.repositories.VehicleRepository
import bell.util.Coroutines
import kotlinx.coroutines.Job

class GuidomiaViewModel(private val repository: VehicleRepository) : ViewModel() {

    private val _vehicleDetails = MutableLiveData<List<CarListModelItem>>()
    val vehicleDetails: LiveData<List<CarListModelItem>> = _vehicleDetails

    private lateinit var job: Job

    fun getVehicleDetails() {
        job = Coroutines.ioThenMain(
            { repository.getVehicleList() },
            { _vehicleDetails.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}