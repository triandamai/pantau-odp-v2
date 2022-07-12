package app.trian.pemantau.ui.pages.form_success_odp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.okhttp.Route
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.SuccessOdpUIState
import com.trian.data.repository.design.OdpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormSuccessOdpViewModel @Inject constructor(
    private val odpRepository: OdpRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _odpState = MutableLiveData<SuccessOdpUIState>()
    val odpState get() = _odpState

    init {
        val uid = savedStateHandle.get<String>(Routes.SuccessFormOdp.argKey).orEmpty()

        getDetailOdp(uid)
    }

    fun getDetailOdp(uid:String) = viewModelScope.launch {
        odpRepository.getDetailOdp(uid)
            .catch {
                _odpState.postValue(
                    SuccessOdpUIState(
                        loading = false,
                        error = true,
                        errorMessage = "${it.message}"
                    )
                )
            }
            .onEach {
                _odpState.postValue(
                    SuccessOdpUIState(
                        loading = false,
                        error = false,
                        citizenName = it.first.name,
                        address = it.first.address,
                        dateOfBirth = it.first.dateOfBirth
                    )
                )
            }
            .collect()
    }
}