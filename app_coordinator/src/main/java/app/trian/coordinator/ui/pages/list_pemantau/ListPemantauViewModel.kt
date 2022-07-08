package app.trian.coordinator.ui.pages.list_pemantau

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.component.screen.ListPemantauUIState
import com.trian.data.repository.design.OfficerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPemantauViewModel @Inject constructor(
    private val officerRepository: OfficerRepository
) : ViewModel() {
    private var _listPemantau = MutableLiveData<ListPemantauUIState>()
    val listPemantau get() = _listPemantau

    init {
        getListPemantau()
    }

    fun getListPemantau()=viewModelScope.launch {
        officerRepository.getListPemantau()
            .onEach {
                _listPemantau.postValue(
                    ListPemantauUIState(
                        loading = false,
                        error = false,
                        data = it
                    )
                )
            }
            .catch {
                _listPemantau.postValue(
                    ListPemantauUIState(
                        loading = false,
                        error = true,
                        errorMessage = "${it.message}"
                    )
                )
            }
            .collect()
    }
}