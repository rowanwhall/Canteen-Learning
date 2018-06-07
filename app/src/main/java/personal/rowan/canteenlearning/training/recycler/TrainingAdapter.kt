package personal.rowan.canteenlearning.training.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import personal.rowan.canteenlearning.R
import personal.rowan.canteenlearning.network.model.RestaurantItem

/**
 * Created by Rowan Hall
 */
class TrainingAdapter: RecyclerView.Adapter<TrainingViewHolder>() {

    private var restaurants = ArrayList<RestaurantItem>()

    fun paginateData(restaurants: ArrayList<RestaurantItem>) {
        val oldSize = this.restaurants.size
        val newSize = restaurants.size
        if (oldSize >= newSize) return

        @Suppress("UNCHECKED_CAST")
        this.restaurants = restaurants.clone() as ArrayList<RestaurantItem>
        notifyItemRangeInserted(oldSize, newSize - oldSize)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        return TrainingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem_training, parent, false))
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(restaurants[position].name)
    }

}