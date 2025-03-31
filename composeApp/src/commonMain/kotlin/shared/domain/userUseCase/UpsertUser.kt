package shared.domain.userUseCase

import shared.data.model.User
import shared.data.repository.UserRepository

class UpsertUser(private val userRepository: UserRepository) {
    suspend operator fun invoke(user:User){
        userRepository.upsertUser(user = user)
    }
}