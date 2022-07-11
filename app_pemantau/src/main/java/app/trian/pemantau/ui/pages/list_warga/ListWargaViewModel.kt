package app.trian.pemantau.ui.pages.list_warga

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trian.component.screen.ListOdpUIState
import com.trian.data.repository.design.OdpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListWargaViewModel @Inject constructor(
    private val odpRepository: OdpRepository
) : ViewModel() {

    private var _listOdp = MutableLiveData<ListOdpUIState>()
    val listOdp get() = _listOdp

    init {

    }
}