package shared.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import shared.data.repository.AuthManagerRepository
import shared.utils.Sanitizer


class LoginViewModel(private val loginRepository: AuthManagerRepository) : ViewModel() {

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun onUsernameChange(newUsername: String) {
        if(Sanitizer.validateInputLength(newUsername)) username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        if(Sanitizer.validateInputLength(newPassword)) password = newPassword
    }

    fun login() {

    }
}
