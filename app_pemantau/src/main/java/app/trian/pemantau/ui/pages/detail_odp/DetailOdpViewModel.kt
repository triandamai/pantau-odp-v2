package app.trian.pemantau.ui.pages.detail_odp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.DetailOdpUIState
import com.trian.data.repository.design.OdpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailOdpViewModel @Inject constructor(
    private val odpRepository: OdpRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _odpState = MutableLiveData<DetailOdpUIState>()
    val odpState get() = _odpState

    init {
        var id =savedStateHandle.get<String>(Routes.DetailOdp.argKey).orEmpty()
        getDetailOdp(id)

    }

    fun getDetailOdp(id:String)=viewModelScope.launch {
        odpRepository.getDetailOdp(id)
            .catch {
                _odpState.postValue(DetailOdpUIState(
                    loading = false,
                    error = true,
                    errorMessage = "${it.message}"
                ))
            }
            .onEach {
                _odpState.postValue(
                    DetailOdpUIState(
                        loading = false,
                        error = false,
                        name = it.first.name,
                        address = it.first.address,
                        phone = it.first.phoneNumber,
                        placeOfBirth = it.first.placeOfBirth,
                        dateOfBirth = it.first.dateOfBirth,
                        alreadyHasAssesment = it.second,
                        uid = it.first.uid
                    )
                )
            }
            .collect()
    }

    fun deleteOdp(uid:String,cb:(Boolean,String)->Unit)=viewModelScope.launch {
        odpRepository.deleteOdp(uid)
            .catch {
                cb(false,"${it.message}")
            }
            .onEach {
                cb(it.first,it.second)
            }
            .collect()
    }

}