package app.trian.pemantau.ui.pages.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.screen.main.MonitoringUIState
import com.trian.component.screen.main.ProfileUIState
import com.trian.data.repository.design.OdpRepository
import com.trian.data.repository.design.UserRepository
import com.trian.data.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val odpRepository: OdpRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _monitoringState = MutableLiveData<MonitoringUIState>()
    val monitoringState get() = _monitoringState
    private var _userName = MutableLiveData<String>()
    val userName get() = _userName

    init {
        getProfile()
        getMonitoring()
    }

    fun getMonitoring()=viewModelScope.launch {
        odpRepository
            .getMonitoring()
            .catch {  }
            .onEach {
                _monitoringState.postValue(
                    when(it){
                        is DataState.onData -> MonitoringUIState(
                            loading = false,
                            error = false,

                            odp=it.data.odp,
                            odpFinish = it.data.odpSelesaiPemantauan,
                            odpOnMonitoring = it.data.odpDalamPemantauan,

                            pdp=it.data.pdp,
                            pdpLabNegative=it.data.pdpHasilLabNegatif,
                            pdpDied = it.data.pdpMeninggalDunia,
                            pdpWaiting = it.data.pdpMenungguHasilLab,


                            positive = it.data.positifCorona,
                            positiveOnTreated = it.data.positifDirawat,
                            positiveCured = it.data.positifSembuh,
                            positiveDied = it.data.positifMeninggal
                        )
                        is DataState.onFailure -> MonitoringUIState(
                            loading = false,
                            error = true,
                            errorMessage = it.message
                        )
                        DataState.onLoading -> MonitoringUIState(
                            loading = true,
                            error = false
                        )
                    }
                )
            }
            .collect()
    }
    fun getProfile()=viewModelScope.launch {
        userRepository
            .getProfileOfficer()
            .catch {
                _userName.postValue(
                    "Tidak diketahui"
                )
            }
            .onEach {
                _userName.postValue(it.second.name)
            }
            .collect()
    }


    fun signOut()=viewModelScope.launch{
        userRepository.signOut()
    }
}