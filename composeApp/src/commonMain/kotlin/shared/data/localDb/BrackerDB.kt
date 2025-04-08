package shared.data.localDb

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import shared.data.dao.AuthDAO
import shared.data.dao.CategoryDAO
import shared.data.dao.ItemDAO
import shared.data.dao.UserDAO
import shared.data.model.Category
import shared.data.model.Item
import shared.data.model.User
import shared.utils.Converters


const val DATABASE_NAME = "bracker.db"


@Database(
    entities = [User::class, Category::class, Item::class],
    version = 6,
    exportSchema = true
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class BrackerDB : RoomDatabase() {
    abstract fun itemsDao(): ItemDAO
    abstract fun usersDAO(): UserDAO
    abstract fun categoriesDAO(): CategoryDAO
    abstract fun authDAO(): AuthDAO
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<BrackerDB> {
    override fun initialize(): BrackerDB
}