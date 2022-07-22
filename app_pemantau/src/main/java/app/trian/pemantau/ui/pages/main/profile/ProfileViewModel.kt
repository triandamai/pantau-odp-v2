package app.trian.pemantau.ui.pages.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.screen.main.ProfileUIState
import com.trian.data.repository.design.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _profileState = MutableLiveData<ProfileUIState>()
    val profileState get() = _profileState
    init {
        getProfile()
    }

    fun getProfile()=viewModelScope.launch {
        userRepository
            .getProfileOfficer()
            .catch {
                _profileState.postValue(
                    ProfileUIState(
                        loading = false,
                        error = true,
                        errorMessage = "${it.message}"
                    )
                )
            }
            .onEach {
                _profileState.postValue(
                    ProfileUIState(
                        loading = false,
                        error = false,
                        email = "${it.first.email}",
                        nip = it.second.nip,
                        opd = it.second.opd,
                        name=it.second.name,
                        placeOfAssignment = it.second.villageName
                    )
                )
            }
            .collect()
    }

    fun signOut()=viewModelScope.launch{
        userRepository.signOut()
    }
}