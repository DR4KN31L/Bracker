package shared.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import shared.data.dao.UserDAO
import shared.data.model.User
import shared.utils.OperationResult

class UserRepository(private val userDAO: UserDAO) {

    private val dispatcher = Dispatchers.IO

    suspend fun upsertUser(user: User, allowUpdate: Boolean = true): OperationResult {
        val result = withContext(dispatcher) {
            if (!allowUpdate) {
                try {
                    userDAO.insertUser(user)
                } catch (e: SQLiteException) {
                    return@withContext -1L
                }
            } else {
                userDAO.upsertUser(user)
            }
        }

        return when {
            result > 0 -> if (!allowUpdate) OperationResult.Created else OperationResult.Updated
            result == -1L -> OperationResult.Failed
            else -> OperationResult.Failed
        }
    }

    suspend fun doesUserExist(username: String): Boolean {
        return userDAO.doesUserExist(username) > 0
    }

}