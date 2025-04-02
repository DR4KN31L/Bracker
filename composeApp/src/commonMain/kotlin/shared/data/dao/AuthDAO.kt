package shared.data.dao

import androidx.room.Dao
import androidx.room.Query
import shared.data.model.LoggedInUser

@Dao
interface AuthDAO {

    @Query("SELECT id,name,lastname FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun loginUser(username : String, password : String): LoggedInUser
}