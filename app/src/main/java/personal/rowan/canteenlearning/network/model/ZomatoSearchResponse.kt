package personal.rowan.canteenlearning.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rowan Hall
 */
data class ZomatoSearchResponse(@SerializedName("results_found") val resultsFound: Int,
                                @SerializedName("results_start") val resultsStart: Int,
                                @SerializedName("results_shown") val resultsShown: Int,
                                val restaurants: List<Restaurant>)

data class Restaurant(@SerializedName("restaurant") val restaurantItem: RestaurantItem)

data class RestaurantItem(val id: String,
                          val name: String,
                          val location: RestaurantLocation,
                          val cuisines: String,
                          @SerializedName("average_cost_for_two") val averageCostForTwo: String,
                          val currency: String,
                          val thumb: String,
                          @SerializedName("user_rating") val userRating: RestaurantUserRating,
                          @SerializedName("featured_image") val featuredImage: String)

data class RestaurantLocation(val address: String,
                              val locality: String,
                              val city: String,
                              val latitude: String,
                              val longitude: String)

data class RestaurantUserRating(@SerializedName("aggregate_rating") val aggregateRating: String,
                                @SerializedName("rating_text") val ratingText: String,
                                @SerializedName("rating_color") val ratingColor: String,
                                val votes: String)