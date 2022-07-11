package app.trian.coordinator.ui.pages.form_pemantau

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.dialog.ItemAddress
import com.trian.component.dialog.PickDistrictOrVillageUIState
import com.trian.data.repository.design.OfficerRepository
import com.trian.data.repository.design.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormPemantauViewModel @Inject constructor(
    private val officerRepository: OfficerRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _listVillage = MutableLiveData<PickDistrictOrVillageUIState>()
    val listVillage get() = _listVillage

    init {
        getListVillage()
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
        email:String,
        opd:String,
        nip:String,
        level:String,
        name:String,
        villageId:String,
        villageName:String,
        callback:(Boolean,String)->Unit
    )=viewModelScope.launch {
        officerRepository
            .saveOfficerPemantau(
                email=email,
                opd=opd,
                nip=nip,
                level=level,
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