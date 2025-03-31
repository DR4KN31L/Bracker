package shared.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import shared.data.repository.AuthManagerRepository


class LoginViewModel(private val loginRepository: AuthManagerRepository) : ViewModel() {
    val maxLenght = 15

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun onUsernameChange(newUsername: String) {
        if(username.length < maxLenght ) username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        if(password.length < maxLenght ) password = newPassword
    }

    fun login() {

    }
}
