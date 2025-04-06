package shared.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shared.data.model.User
import shared.domain.UserUseCase.UserUseCase
import shared.utils.OperationResult
import shared.utils.Sanitizer

class UserViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    var name = MutableStateFlow("")
        private set
    var lastName = MutableStateFlow("")
        private set
    var username = MutableStateFlow("")
        private set
    var password = MutableStateFlow("")
        private set

    private val _snackbarChannel = Channel<Pair<String, Color>>(Channel.BUFFERED)
    val snackbarMessages = _snackbarChannel.receiveAsFlow()


    fun onNameChanged(newValue: String) {
        if (Sanitizer.validateInputLength(newValue)) {
            name.value = newValue
        }
    }

    fun onLastNameChanged(newValue: String) {
        if (Sanitizer.validateInputLength(newValue)) {
            lastName.value = newValue
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

    fun createUser(onResult: (Boolean) -> Unit) {

        val validationError = Sanitizer.validateAllInputs(
            name = name.value,
            lastname = lastName.value,
            username = username.value,
            password = password.value
        )

        if(validationError != null){
            viewModelScope.launch {
                _snackbarChannel.send(validationError to Color.Red)
            }
            onResult(false)
            return
        }

        val user = User(
            name = name.value,
            lastname = lastName.value,
            username = username.value,
            password = password.value
        )

        viewModelScope.launch {
            val userExist = userUseCase.doesUserExists(username.value)

            val success = if (userExist) {
                _snackbarChannel.send("Cuenta no válida" to Color.Red)
                false
            } else {
                when (userUseCase.createUser(user)) {
                    OperationResult.Created -> {
                        _snackbarChannel.send("Cuenta Creada" to Color.Green)
                        true
                    }
                    else -> {
                        _snackbarChannel.send("No es posible realizar la acción" to Color.Red)
                        false
                    }
                }
            }
            delay(500)
            onResult(success) // Llamar el callback con el resultado
        }
    }
}
