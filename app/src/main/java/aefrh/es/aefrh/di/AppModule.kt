package aefrh.es.aefrh.di

import aefrh.es.aefrh.BuildConfig
import aefrh.es.aefrh.data.EpocaApi
import aefrh.es.aefrh.data.EpocaDataSource
import aefrh.es.aefrh.data.EpocaRepository
import aefrh.es.aefrh.presentation.main.MainViewModel
import aefrh.es.aefrh.usecases.GetEpocas
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {

    // Retrofit
    single {
        createWebService<EpocaApi>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = BuildConfig.PARSE_API_URL
        )
    }

    // Tells Koin how to create an instance of EpocaRepository
    factory<EpocaDataSource> { EpocaRepository(epocaApi = get()) }

    // Create Use Case
    single { GetEpocas(epocaRepository = get()) }

    // Specific viewModel pattern to tell Koin how to build MainViewModel
    viewModel { MainViewModel(epocaUseCase = get()) }

}

/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()

        val requestBuilder = original.newBuilder()
        requestBuilder.addHeader("X-Parse-Application-Id", BuildConfig.PARSE_API_ID)
        requestBuilder.addHeader("X-Parse-REST-API-Key", BuildConfig.PARSE_REST_API_ID)

        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}