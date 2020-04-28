package aefrh.es.aefrh.di

import aefrh.es.aefrh.BuildConfig
import aefrh.es.aefrh.data.noticias.NoticiasApi
import aefrh.es.aefrh.data.noticias.NoticiasRepository
import aefrh.es.aefrh.data.noticias.NoticiasRepositoryImpl
import aefrh.es.aefrh.data.parse.ParseApi
import aefrh.es.aefrh.data.parse.ParseRepository
import aefrh.es.aefrh.data.parse.ParseRepositoryImpl
import aefrh.es.aefrh.usecases.EpocasUseCase
import aefrh.es.aefrh.usecases.FiestasUseCase
import aefrh.es.aefrh.usecases.InternoUseCase
import aefrh.es.aefrh.usecases.NoticiasUseCase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single {
        createWebService<ParseApi>(
            okHttpClient = createParseHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = BuildConfig.PARSE_API_URL
        )
    }
    single {
        createWordpressClient<NoticiasApi>(
            factory = RxJava2CallAdapterFactory.create(),
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
    baseUrl: String,
    factory: CallAdapter.Factory
): T {

//    val tikxml = TikXml.Builder().exceptionOnUnreadXml(false).build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
//        .addConverterFactory(JaxbConverterFactory.create())
//        .addConverterFactory(TikXmlConverterFactory.create(tikxml))
//        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .addCallAdapterFactory(factory)
        .client(OkHttpClient.Builder().build())
        .build()

    return retrofit.create(T::class.java)
}