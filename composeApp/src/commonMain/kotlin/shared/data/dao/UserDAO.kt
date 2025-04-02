package shared.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import bracker.composeapp.generated.resources.Res
import kotlinx.coroutines.flow.Flow
import shared.data.model.User

@Dao
interface UserDAO {
    @Upsert
    suspend fun upsertUser(user: User)

    @Query("SELECT username FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): String?

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    suspend fun doesUserExist(username: String): Int

    @Delete
    suspend fun delete(user: User)
}