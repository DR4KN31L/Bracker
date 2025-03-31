package shared.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import shared.data.localDb.BrackerDB
import shared.data.model.User

class UserRepository(private val database: BrackerDB) {

    private val dispatcher = Dispatchers.IO

    suspend fun upsertUser(user: User) {
        with(dispatcher) { database.usersDAO().upsertUser(user) }
    }

    fun getAllUser(): Flow<List<User>?> {
        return database.usersDAO().getUsers()
    }

}