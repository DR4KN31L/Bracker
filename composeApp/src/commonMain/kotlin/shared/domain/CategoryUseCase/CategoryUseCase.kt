package shared.domain.CategoryUseCase

import kotlinx.coroutines.flow.Flow
import shared.data.model.Category
import shared.data.repository.CategoryRepository

class CategoryUseCase(private val repository: CategoryRepository) {

    suspend fun addCategory(category: Category) {
        repository.insertCategory(category)
    }

    fun getCategoriesByUser(userId: Int): Flow<List<Category>> {
        return repository.getCategoryByUserID(userId)
    }
}