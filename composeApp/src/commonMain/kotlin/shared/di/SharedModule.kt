package shared.di

import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import shared.data.localDb.BrackerDB
import shared.data.localDb.CreateDatabase
import shared.data.repository.UserRepository
import shared.viewModel.LoginViewModel
import shared.viewModel.UserViewModel
import shared.domain.userUseCase.UserUseCase

val sharedModule = module {
    single<BrackerDB> { CreateDatabase(get()).getDatabase() }
    single<CoroutineDispatcher> {Dispatchers.IO}

    // Repository singletons
    singleOf(::UserRepository)

    // UseCases
    singleOf(::UserUseCase)

    // ViewModels
    viewModelOf(::UserViewModel)
    viewModelOf(::LoginViewModel)
}