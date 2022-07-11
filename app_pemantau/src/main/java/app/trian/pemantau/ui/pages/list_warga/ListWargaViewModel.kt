package app.trian.pemantau.ui.pages.list_warga

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.screen.ListOdpUIState
import com.trian.component.screen.OdpUIState
import com.trian.data.repository.design.OdpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListWargaViewModel @Inject constructor(
    private val odpRepository: OdpRepository
) : ViewModel() {

    private var _listOdp = MutableLiveData<ListOdpUIState>()
    val listOdp get() = _listOdp


    init {
        getListOdp()
    }

    fun getListOdp()=viewModelScope.launch {
        odpRepository
            .getListOdpByVillage()
            .catch {
                _listOdp.postValue(
                    ListOdpUIState(
                        loading = false,
                        error = true,
                        errorMessage = "${it.message}"
                    )
                )
            }
            .onEach {
                _listOdp.postValue(
                    ListOdpUIState(
                        loading = false,
                        error = false,
                        data = it.map {
                            OdpUIState(
                                name = it.name,
                                phone = it.phoneNumber,
                                nik = it.identityNumber,
                                id = it.uid
                            )
                        }
                    )
                )
            }
            .collect()
    }
}