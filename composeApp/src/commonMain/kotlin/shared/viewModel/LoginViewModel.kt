package shared.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import shared.data.model.LoggedInUser
import shared.domain.AuthUseCase.AuthUseCase
import shared.utils.Sanitizer
import java.util.concurrent.StructuredTaskScope.ShutdownOnSuccess


class LoginViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    var username  = MutableStateFlow("")
        private set

    var password = MutableStateFlow("")
        private set

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

    fun logIn(onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            val data = authUseCase.loggedInUser(username.value, password.value)
            onResult(data != null)
        }
    }
}
