package shared.domain.userUseCase

import kotlinx.coroutines.flow.Flow
import shared.data.model.User
import shared.data.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun createUser(user:User) {
        userRepository.upsertUser(user)
    }

    suspend fun doesUserExists(username: String): Boolean {
        return userRepository.doesUserExist(username)
    }

    fun getAllUsers(): Flow<List<User>?>{
        return userRepository.getAllUser()
    }
}