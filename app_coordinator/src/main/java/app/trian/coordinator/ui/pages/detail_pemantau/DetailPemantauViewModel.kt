package app.trian.coordinator.ui.pages.detail_pemantau

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.Routes
import com.trian.component.screen.user.DetailOfficerUIState
import com.trian.data.repository.design.OfficerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPemantauViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val officerRepository: OfficerRepository
) : ViewModel() {

    private var _detailOfficerState = MutableLiveData<DetailOfficerUIState>()
    val detailOfficerState get() = _detailOfficerState

    init {
        val uid = savedStateHandle.get<String>(Routes.DetailUser.argKey).orEmpty()
        getDetailPemantau(uid)
    }

    fun getDetailPemantau(uid:String) = viewModelScope.launch {
        officerRepository
            .getDetailPemantau(uid)
            .catch {
                _detailOfficerState.postValue(
                    DetailOfficerUIState(
                        loading = false,
                        error = true,
                        errorMessage = "${it.message}"
                    )
                )
            }
            .onEach {
                _detailOfficerState.postValue(
                    DetailOfficerUIState(
                        loading = false,
                        error = false,
                        officer = it
                    )
                )
            }
            .collect()
    }
}