package app.trian.coordinator.ui.pages.form_pemantau

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.data.repository.design.OfficerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormPemantauViewModel @Inject constructor(
    private val officerRepository: OfficerRepository
) : ViewModel() {

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