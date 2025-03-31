package shared.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import shared.data.model.Category

@Dao
interface CategoryDAO {

    @Upsert
    suspend fun insertCategory(category: Category)

    // Categorias del usuario
    @Query("SELECT * FROM categories WHERE userId = :userId")
    fun getCategoryById(userId: Int): Flow<List<Category>>

}