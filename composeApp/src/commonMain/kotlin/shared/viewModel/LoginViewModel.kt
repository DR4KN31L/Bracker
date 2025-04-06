package shared.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shared.data.model.LoggedInUser
import shared.domain.AuthUseCase.AuthUseCase
import shared.utils.Sanitizer
import shared.utils.Sanitizer.Companion.validateLogin


class LoginViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    var username  = MutableStateFlow("")
        private set

    var password = MutableStateFlow("")
        private set

    private val _snackbarChannel = Channel<Pair<String, Color>>(Channel.BUFFERED)
    val snackbarMessages = _snackbarChannel.receiveAsFlow()

    fun setInitialUsername(value: String?) {
        if (!value.isNullOrBlank() && Sanitizer.validateInputLength(value)) {
            username.value = value
        }
    }

    fun onUsernameChanged(newValue: String) {
        if (Sanitizer.validateInputLength(newValue)) {
            username.value = newValue
        }
    }

    fun onPasswordChanged(newValue: String) {
        if (Sanitizer.validateInputLength(newValue)) {
            password.value = newValue
        }
    }

    fun logIn(onResult: (Boolean,LoggedInUser?) -> Unit){

        viewModelScope.launch {

            val validateLoginError = validateLogin(username.value,password.value)
            println(validateLoginError)
            if (validateLoginError != null){
                _snackbarChannel.send(validateLoginError to Color.Red)
                onResult(false,null)
                return@launch
            }

            val loggedUser = authUseCase.loggedInUser(username.value, password.value)

            if (loggedUser == null){
                _snackbarChannel.send("Usuario/contraseña incorrectas" to Color.Red)
            }
            onResult(loggedUser != null,loggedUser)
        }
    }
}
