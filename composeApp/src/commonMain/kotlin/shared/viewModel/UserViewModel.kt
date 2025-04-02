package shared.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shared.data.model.User
import shared.domain.userUseCase.UserUseCase
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

    private val _users = MutableStateFlow<List<User>?>(null)
    val users: StateFlow<List<User>?> = _users

    fun createUser() {
        val user = User(
            name = name.value,
            lastname = lastName.value,
            username = username.value,
            password = password.value
        )
        viewModelScope.launch {
            println("Create User Launched...")
            val userExist = userUseCase.doesUserExists(username.value)

            val message = if (userExist) {
                "Cuenta no válida" to Color.Red
            }else {
                userUseCase.createUser(user)
                "Cuenta creada" to Color.Green
            }
            _snackbarChannel.send(message)
        }
    }
}