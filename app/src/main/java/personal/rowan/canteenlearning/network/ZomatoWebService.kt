package personal.rowan.canteenlearning.network

import io.reactivex.Observable
import personal.rowan.canteenlearning.BuildConfig
import personal.rowan.canteenlearning.network.model.ZomatoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Rowan Hall
 */
interface ZomatoWebService {

    @GET("search")
    fun searchRestaurants(@Query("lat") lat: Double,
                          @Query("lon") lon: Double,
                          @Query("start") start: Int,
                          @Query("sort") sort: String = "real_distance",
                          @Query("order") order: String = "asc"): Observable<ZomatoSearchResponse>

    companion object {
        val BASE_URL = "https://developers.zomato.com/api/v2.1/"
        val API_KEY = BuildConfig.ZOMATO_API_KEY
    }
}