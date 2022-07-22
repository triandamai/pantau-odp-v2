package app.trian.pemantau.ui.pages.change_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.data.repository.design.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * author Trian Damai
 * created_at 22/07/22 - 10.30
 * site https://trian.app
 */
@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun changePassword(newPassword:String,cb:(Boolean,String)->Unit)=viewModelScope.launch {
        userRepository.changePassword(newPassword)
            .catch {
                cb(false,"${it.message}")
            }
            .onEach {
                cb(it.first,it.second)
            }
            .collect()
    }
}