package personal.rowan.canteenlearning.application.dagger.component

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import personal.rowan.canteenlearning.network.ZomatoWebService
import personal.rowan.canteenlearning.network.createRetrofitService

/**
 * Created by Rowan Hall
 */

@Module
class ZomatoApiModule {

    private val httpClient = OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(it.request().newBuilder()
                        .addHeader("user-key", ZomatoWebService.API_KEY).build())
            }
            .build()

    @Provides
    fun provideZomatoService(): ZomatoWebService {
        return createRetrofitService(ZomatoWebService::class.java, ZomatoWebService.BASE_URL, httpClient)
    }

}