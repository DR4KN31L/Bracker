package shared.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import shared.data.dao.AuthDAO
import shared.data.dao.CategoryDAO
import shared.data.dao.ItemDAO
import shared.data.dao.UserDAO
import shared.data.localDb.BrackerDB
import shared.data.localDb.CreateDatabase
import shared.data.repository.UserRepository
import shared.data.repository.CategoryRepository
import shared.data.repository.ItemRepository
import shared.domain.UserUseCase.UserUseCase
import shared.domain.CategoryUseCase.CategoryUseCase
import shared.viewModel.UserViewModel
import shared.viewModel.CategoryViewModel

val sharedModule = module {
    single<BrackerDB> { CreateDatabase(get()).getDatabase() }
    single<CoroutineDispatcher> {Dispatchers.IO}

    // DB-DAOs
    single<UserDAO> { get<BrackerDB>().usersDAO() }
    single<ItemDAO> { get<BrackerDB>().itemsDao() }
    single<CategoryDAO> { get<BrackerDB>().categoriesDAO() }
    single<AuthDAO> { get<BrackerDB>().authDAO() }

    // Repository singletons
    singleOf(::UserRepository)
    singleOf(::CategoryRepository)
    singleOf(::ItemRepository)

    // UseCases
    singleOf(::UserUseCase)
    singleOf(::CategoryUseCase)

    // ViewModels
    viewModelOf(::UserViewModel)
    viewModelOf(::CategoryViewModel)
}