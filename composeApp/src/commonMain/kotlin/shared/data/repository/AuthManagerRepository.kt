package shared.data.repository

import shared.data.localDb.BrackerDB
import shared.data.model.LoggedInUser

class AuthManagerRepository(private val database: BrackerDB) {
    suspend fun loginUser(username: String,password:String):LoggedInUser?{
        return database.authDAO().loginUser(username,password)
    }
}