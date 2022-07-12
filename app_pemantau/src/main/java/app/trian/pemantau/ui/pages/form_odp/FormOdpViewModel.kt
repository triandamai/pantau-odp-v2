package app.trian.pemantau.ui.pages.form_odp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.data.repository.design.OdpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormOdpViewModel @Inject constructor(
    private val odpRepository: OdpRepository
) : ViewModel() {

    fun submitOdp(
        name:String,
        religion:String,
        nik:String,
        dateOfBirth:String,
        placeOfBirth:String,
        address:String,
        rt:String,
        rw:String,
        bloodType:String,
        profession:String,
        phoneNUmber:String,
        tripHistory:String,
        placeOfTrip:String,
        isolation:Boolean,
        safetyNet:Boolean,
        behavior:Boolean,
        condition:String,
        gender:String,
        cb:(Boolean,String)->Unit
    )=viewModelScope.launch {
        odpRepository
            .saveOdp(
                name,
                religion,
                nik,
                dateOfBirth,
                placeOfBirth,
                address,
                rt,
                rw,
                bloodType,
                profession,
                phoneNUmber,
                tripHistory,
                placeOfTrip,
                isolation,
                safetyNet,
                behavior,
                condition,
                gender
            )
            .catch {
                cb(false,"${it.message}")
            }
            .onEach {
                cb(it.first,it.second)
            }
            .collect()
    }
}