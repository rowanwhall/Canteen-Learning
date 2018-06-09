package personal.rowan.canteenlearning.training.recycler

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import personal.rowan.canteenlearning.R
import personal.rowan.canteenlearning.network.model.RestaurantItem

/**
 * Created by Rowan Hall
 */
class TrainingItemViewState(private val restaurantItem: RestaurantItem, selection: TrainingItemSelection) {

    val selection: MutableLiveData<TrainingItemSelection> = MutableLiveData()

    init {
        this.selection.value = selection
    }

    fun name(): String {
        return this.restaurantItem.name
    }

    fun address(): String {
        return this.restaurantItem.location.address
    }

    fun cuisines(context: Context): String {
        return context.getString(R.string.training_item_cuisines, this.restaurantItem.cuisines)
    }

    @SuppressLint("StringFormatMatches")
    fun cost(context: Context): String {
        val restaurantItem = this.restaurantItem
        return context.getString(R.string.training_item_cost, restaurantItem.currency, restaurantItem.averageCostForTwo)
    }
}

enum class TrainingItemSelection {
    NONE, NEGATIVE, NEUTRAL, POSITIVE
}