package app.trian.coordinator.ui.pages.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.data.repository.design.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun resetPassword(
        email:String,
        callback:(Boolean,String)->Unit
    ) = viewModelScope.launch {
        userRepository
            .resetPassword(email)
            .catch {
                callback(false,"${it.message}")
            }
            .onEach {
                callback(it.first,it.second)
            }
            .collect()
    }
}