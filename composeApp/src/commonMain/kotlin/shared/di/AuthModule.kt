package shared.di

import org.koin.dsl.module
import shared.auth.AuthManager
import shared.data.repository.AuthManagerRepository

val authModule = module {
    single { AuthManager() }
    single { AuthManagerRepository(get())}
}