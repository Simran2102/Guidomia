package bell.data.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bell.view.GuidomiaViewModel

class VehicleDetailViewModelFactory(private val repository: VehicleRepository) :
    ViewModelProvider.NewInstanceFactory(){


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GuidomiaViewModel(repository) as T
    }
}