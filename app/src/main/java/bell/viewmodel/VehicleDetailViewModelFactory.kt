package bell.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bell.data.repositories.VehicleRepository

class VehicleDetailViewModelFactory(private val repository: VehicleRepository) :
    ViewModelProvider.NewInstanceFactory(){


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GuidomiaViewModel(repository) as T
    }
}