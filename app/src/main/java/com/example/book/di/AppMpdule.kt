package com.example.book.di

import com.example.book.api.service.BookService
import com.example.book.repository.BookRepository
import com.example.book.ui.account.login.LoginViewModel
import com.example.book.ui.main.book.BookViewModel
import com.example.book.ui.main.home.HomeViewModel
import com.example.book.ui.main.home.ProfileViewModel
import com.example.book.ui.register.RegisterViewModel
import com.example.book.utils.Constants
import com.example.book.utils.Preferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 30 * 1000

val netWorkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .header("accept", "application/json")
                .header(
                    "Authorization",
                    "Bearer ${
                        get<Preferences>(Preferences::class).getToken()
                    }"
                )
                .build()
            chain.proceed(newRequest)
        }.connectTimeout(
            CONNECTION_TIMEOUT.toLong(),
            TimeUnit.MINUTES
        ).readTimeout(1, TimeUnit.MINUTES).build()
    }
    single<Retrofit>(qualifier = named("Retrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


}

val viaCepApi = module {
    single<Retrofit>(qualifier = named("viaCepRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}


val databaseModule = module {

    single {
        Preferences(androidContext())
    }
//    single {
//        DatabaseHelper(androidContext())
//    }
}
val serviceModule = module {
    single {
        get<Retrofit>(qualifier = named("Retrofit")).create(BookService::class.java)
    }

}
val repositoryModule = module {

    single<CoroutineDispatcher> { Dispatchers.Default }

    single {
        BookRepository(get())
    }
//    single {
//        OrderRepository(get(), get())
//    }

}
val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        LoginViewModel(get(), get())
    }
    viewModel {
        BookViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }
    viewModel {
        ProfileViewModel(get())
    }

}


val listModules =
    listOf(netWorkModule, databaseModule, serviceModule, repositoryModule, viewModelModule, viaCepApi)
