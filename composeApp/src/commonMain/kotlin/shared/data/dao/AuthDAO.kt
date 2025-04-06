package shared.data.dao

import androidx.room.Dao
import androidx.room.Query
import shared.data.model.UserHelper

@Dao
interface AuthDAO {

    @Query("SELECT id,name,lastname,password FROM users WHERE username = :username LIMIT 1")
    suspend fun loginUser(username : String): UserHelper?
}