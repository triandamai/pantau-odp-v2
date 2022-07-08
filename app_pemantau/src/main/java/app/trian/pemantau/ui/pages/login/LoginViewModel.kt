package app.trian.pemantau.ui.pages.login

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
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun signIn(
        email:String,
        password:String,
        callback:(Boolean,String)->Unit
    )=viewModelScope.launch {
        userRepository.signIn(
            email,
            password,
            "PEMANTAU"
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