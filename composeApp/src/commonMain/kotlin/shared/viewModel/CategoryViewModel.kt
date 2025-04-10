package shared.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shared.data.model.Category
import shared.domain.CategoryUseCase.CategoryUseCase

class CategoryViewModel(private val categoryUseCase: CategoryUseCase) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    fun loadCategories(userId: Int) {
        viewModelScope.launch {

            categoryUseCase.getCategoriesByUser(userId)
                .collect { categoryList ->
                    _categories.value = categoryList
                }
        }
    }

    fun upsertCategory(name: String, userId: Int) {
        viewModelScope.launch {
            val category = Category(name = name, userId = userId)
            categoryUseCase.addCategory(category)
            loadCategories(userId)
        }
    }

}