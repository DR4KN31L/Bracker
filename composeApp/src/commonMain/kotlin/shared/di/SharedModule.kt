package shared.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import shared.data.localDb.BrackerDB
import shared.data.localDb.CreateDatabase
import shared.data.repository.UserRepository
import shared.viewModel.LoginViewModel

val sharedModule = module {
    single<BrackerDB> { CreateDatabase(get()).getDatabase() }
    single<CoroutineDispatcher> {Dispatchers.IO}
    // Repository singletons
    singleOf(::UserRepository)
    
}