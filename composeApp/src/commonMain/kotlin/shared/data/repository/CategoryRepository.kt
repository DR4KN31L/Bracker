package shared.data.repository

import kotlinx.coroutines.flow.Flow
import shared.data.dao.CategoryDAO
import shared.data.model.Category

class CategoryRepository(private val dao : CategoryDAO){

    suspend fun insertCategory(category: Category){
        dao.insertCategory(category)
    }

    fun getCategoryByUserID(userId: Int): Flow<List<Category>>{
        return dao.getCategoryById(userId)
    }
}