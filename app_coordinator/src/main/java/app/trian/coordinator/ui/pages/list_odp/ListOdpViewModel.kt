package app.trian.coordinator.ui.pages.list_odp

import androidx.lifecycle.ViewModel
import com.trian.data.repository.design.OdpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListOdpViewModel @Inject constructor(
    private val odpRepository: OdpRepository
) : ViewModel() {

}