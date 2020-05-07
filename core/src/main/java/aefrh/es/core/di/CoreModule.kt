package aefrh.es.core.di

import aefrh.es.core.BuildConfig
import aefrh.es.core.data.noticias.NoticiasApi
import aefrh.es.core.data.noticias.NoticiasRepository
import aefrh.es.core.data.noticias.NoticiasRepositoryImpl
import aefrh.es.core.data.parse.ParseApi
import aefrh.es.core.data.parse.ParseRepository
import aefrh.es.core.data.parse.ParseRepositoryImpl
import aefrh.es.core.usecases.EpocasUseCase
import aefrh.es.core.usecases.FiestasUseCase
import aefrh.es.core.usecases.InternoUseCase
import aefrh.es.core.usecases.NoticiasUseCase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single {
        createWebService<ParseApi>(
            okHttpClient = createParseHttpClient(),
            baseUrl = BuildConfig.PARSE_API_URL
        )
    }
    single {
        createWordpressClient<NoticiasApi>(
            baseUrl = BuildConfig.WORDPRESS_URL
        )
    }
}

val repositoryModule = module {
    factory<ParseRepository> { ParseRepositoryImpl(parseApi = get()) }
    factory<NoticiasRepository> { NoticiasRepositoryImpl(noticiasApi = get()) }
}

val useCaseModule = module {
    single { EpocasUseCase(parseRepository = get()) }
    single { FiestasUseCase(parseRepository = get()) }
    single { InternoUseCase(parseRepository = get()) }
    single { NoticiasUseCase(noticiasRepository = get()) }
}

/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createParseHttpClient(): OkHttpClient {
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

inline fun <reified T> createWordpressClient(
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(OkHttpClient.Builder().build())
        .build()
    return retrofit.create(T::class.java)
}