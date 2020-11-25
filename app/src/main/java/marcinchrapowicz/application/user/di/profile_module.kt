package marcinchrapowicz.application.user.di

import marcinchrapowicz.application.data.datasource.createOkHttpClient
import marcinchrapowicz.application.data.datasource.createRetrofit
import marcinchrapowicz.application.data.datasource.createRetrofitService
import marcinchrapowicz.application.data.datasource.user.UserWebService
import marcinchrapowicz.application.data.user.UserRepository
import marcinchrapowicz.application.data.user.UserRepositoryImpl
import marcinchrapowicz.application.domain.transformer.user.UserTransformer
import marcinchrapowicz.application.domain.usecase.GetUser
import marcinchrapowicz.application.user.viewmodel.UserProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val user_profile_module = module {
    viewModel {
        UserProfileViewModel(get())
    }
}

val usecase_module = module {
    factory { GetUser(get()) }
}

val repository_module = module {
    single<UserRepository> { UserRepositoryImpl(get(), UserTransformer()) }
}

val datasource_module = module {
    single { createOkHttpClient() }

    // Retrofit
    single { createRetrofit(get(), "https://gist.githubusercontent.com/MarcinChrapowicz/581bb2395e6f72248751c28eca45a055/raw/c62930726af1dd2449668a03b9c428576c9c737d/") }

    // Retrofit Services
    single<UserWebService> { createRetrofitService(get()) }
}