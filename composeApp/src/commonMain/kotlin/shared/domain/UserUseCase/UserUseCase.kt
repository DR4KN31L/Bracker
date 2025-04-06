package shared.domain.UserUseCase

import shared.data.model.User
import shared.data.repository.UserRepository
import shared.utils.OperationResult
import shared.utils.PasswordHasher

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun createUser(user:User): OperationResult {
        val hashedPasswd = PasswordHasher.hash(user.password)
        val userToSave = user.copy(password = hashedPasswd)
        return userRepository.upsertUser(userToSave, allowUpdate = false)
    }

    suspend fun doesUserExists(username: String): Boolean {
        return userRepository.doesUserExist(username)
    }

}