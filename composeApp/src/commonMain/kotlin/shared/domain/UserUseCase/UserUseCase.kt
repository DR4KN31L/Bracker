package shared.domain.UserUseCase

import kotlinx.coroutines.flow.Flow
import shared.data.model.User
import shared.data.repository.UserRepository
import shared.utils.OperationResult

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun createUser(user:User): OperationResult {
        return userRepository.upsertUser(user, allowUpdate = false)
    }

    suspend fun doesUserExists(username: String): Boolean {
        return userRepository.doesUserExist(username)
    }

    fun getAllUsers(): Flow<List<User>?>{
        return userRepository.getAllUser()
    }

}