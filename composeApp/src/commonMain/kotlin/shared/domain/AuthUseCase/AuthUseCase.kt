package shared.domain.AuthUseCase

import shared.data.model.LoggedInUser
import shared.data.repository.AuthManagerRepository

class AuthUseCase(private val authManagerRepository: AuthManagerRepository) {

    suspend fun loggedInUser(username: String, password : String): LoggedInUser? {
        return authManagerRepository.loginUser(username,password)
    }

}