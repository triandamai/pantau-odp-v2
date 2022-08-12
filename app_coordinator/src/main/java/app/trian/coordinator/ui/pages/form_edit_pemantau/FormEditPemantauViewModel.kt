package app.trian.coordinator.ui.pages.form_edit_pemantau

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.Routes
import com.trian.component.dialog.ItemAddress
import com.trian.component.dialog.PickDistrictOrVillageUIState
import com.trian.component.screen.user.DetailOfficerUIState
import com.trian.data.repository.design.OfficerRepository
import com.trian.data.repository.design.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormEditPemantauViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val officerRepository: OfficerRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _listVillage = MutableLiveData<PickDistrictOrVillageUIState>()
    val listVillage get() = _listVillage

    private var _detailOfficerState = MutableLiveData<DetailOfficerUIState>()
    val detailOfficerState get() = _detailOfficerState


    init {
        getListVillage()
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

    fun getListVillage()=viewModelScope.launch {
        userRepository
            .getListVillage()
            .catch {
                _listVillage.postValue(PickDistrictOrVillageUIState(
                    loading = false,
                    error = true,
                    errorMessage = "${it.message}"
                ))
            }
            .onEach {
                _listVillage.postValue(PickDistrictOrVillageUIState(
                    loading = false,
                    error = false,
                    data = it.map {
                        ItemAddress(
                            id = it.id,
                            name = it.village
                        )
                    }
                ))
            }
            .collect()
    }

    fun saveOfficer(
        opd:String,
        nip:String,
        name:String,
        villageId:String,
        villageName:String,
        callback:(Boolean,String)->Unit
    )=viewModelScope.launch {
        val uid = savedStateHandle.get<String>(Routes.FormEditUser.argKey).orEmpty()
        if(uid.isEmpty()){
            callback(false,"User tidak dapat ditemukan!")
        }else{
            officerRepository
                .updatePemantau(
                    uid=uid,
                    opd=opd,
                    nip=nip,
                    name=name,
                    villageId=villageId,
                    villageName=villageName
                )
                .catch {
                    callback(false,"${it.message}")
                }
                .onEach {
                    callback(it.first,it.second)
                }
                .collect()
        }

    }
}