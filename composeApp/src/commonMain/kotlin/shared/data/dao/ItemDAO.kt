package shared.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import shared.data.model.Item

@Dao
interface ItemDAO {
    @Upsert
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM items WHERE categoryId = :categoryId")
    suspend fun getItemsByCategory(categoryId: String) : Item
}