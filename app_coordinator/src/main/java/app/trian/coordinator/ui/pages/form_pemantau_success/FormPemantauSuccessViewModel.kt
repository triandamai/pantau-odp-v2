package app.trian.coordinator.ui.pages.form_pemantau_success

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.Routes
import com.trian.component.screen.user.SuccessOfficerUIState
import com.trian.data.repository.design.OfficerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import logcat.LogPriority
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class FormPemantauSuccessViewModel @Inject constructor(
    private val officerRepository: OfficerRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _officerState = MutableLiveData<SuccessOfficerUIState>()
    val officerState get() = _officerState

    init {

        val uid = savedStateHandle.get<String>(Routes.SuccessFormPemantau.argKey).orEmpty()

        logcat(tag = "tag1", priority = LogPriority.ERROR) { uid }
        getDetailOfficer(uid)
    }

    fun getDetailOfficer(uid:String)=viewModelScope.launch {
        delay(1000)

        officerRepository
            .getDetailPemantau(
                uid
            )
            .catch {
                logcat(tag = "tag2", priority = LogPriority.ERROR) { "${it.message}" }
                _officerState.postValue(
                    SuccessOfficerUIState(
                        loading = false,
                        error = true,
                        errorMessage ="${it.message}"
                    )
                )
            }
            .onEach {
                logcat(tag = "tag2", priority = LogPriority.ERROR) { "${it.name}" }
                _officerState.postValue(
                    SuccessOfficerUIState(
                        loading = false,
                        error = false,
                        name = it.name,
                        email = it.email,
                        placeOfAssignment = it.villageName,
                        nip = it.nip,
                        opd = it.opd,
                        level = it.level
                    )
                )
            }
            .collect()
    }
}