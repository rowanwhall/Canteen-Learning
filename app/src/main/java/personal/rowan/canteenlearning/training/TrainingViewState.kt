package personal.rowan.canteenlearning.training

import personal.rowan.canteenlearning.network.model.RestaurantItem
import personal.rowan.canteenlearning.network.model.ZomatoSearchResponse

/**
 * Created by Rowan Hall
 */
class TrainingViewState private constructor(
        var restaurants: ArrayList<RestaurantItem> = ArrayList(),
        var offset: Int = 0,
        var totalSize: Int = -1,
        var showProgress: Boolean = false,
        var showError: Boolean = false) {

    companion object {
        fun starting(): TrainingViewState {
            return TrainingViewState()
        }
    }

    fun fromResponse(response: ZomatoSearchResponse, clear: Boolean): TrainingViewState {
        val restaurants = if (clear) ArrayList() else this.restaurants
        response.restaurants.forEach { restaurants.add(it.restaurantItem) }
        this.restaurants = restaurants
        this.offset += response.resultsShown
        this.totalSize = response.resultsFound
        this.showProgress = false
        this.showError = false
        return this
    }

    fun progress(): TrainingViewState {
        this.showProgress = true
        return this
    }

    fun failure(@Suppress("UNUSED_PARAMETER") e: Throwable): TrainingViewState {
        this.showProgress = false
        this.showError = true
        return this
    }

    fun canPaginate(): Boolean {
        return !showProgress && (totalSize < 0 || this.totalSize > restaurants.size)
    }

}