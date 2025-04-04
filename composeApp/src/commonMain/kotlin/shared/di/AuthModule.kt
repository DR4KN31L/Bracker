package shared.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import shared.viewModel.LoginViewModel
import shared.domain.AuthUseCase.AuthUseCase
import shared.data.repository.AuthManagerRepository

/**
 * Only Auth module and related here...
 *
 * */
val authModule = module {
    //Repository
    singleOf(::AuthManagerRepository)
    //UseCase
    singleOf(::AuthUseCase)
    // ViewModel
    viewModelOf(::LoginViewModel)
}