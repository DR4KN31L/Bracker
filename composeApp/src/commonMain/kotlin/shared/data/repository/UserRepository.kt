package shared.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import shared.data.localDb.BrackerDB
import shared.data.model.User
import shared.utils.OperationResult

class UserRepository(private val database: BrackerDB) {

    private val dispatcher = Dispatchers.IO

    suspend fun upsertUser(user: User, allowUpdate: Boolean = true): OperationResult {
        val result = withContext(dispatcher) {
            if (!allowUpdate) {
                try {
                    database.usersDAO().insertUser(user)
                } catch (e: SQLiteException) {
                    return@withContext -1L
                }
            } else {
                database.usersDAO().upsertUser(user)
            }
        }

        return when {
            result > 0 -> if (!allowUpdate) OperationResult.Created else OperationResult.Updated
            result == -1L -> OperationResult.Failed
            else -> OperationResult.Failed
        }
    }

    fun getAllUser(): Flow<List<User>?> {
        return database.usersDAO().getUsers()
    }

    suspend fun doesUserExist(username: String): Boolean {
        return database.usersDAO().doesUserExist(username) > 0
    }
}