package personal.rowan.canteenlearning.training.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import personal.rowan.canteenlearning.R

/**
 * Created by Rowan Hall
 */
class TrainingAdapter: RecyclerView.Adapter<TrainingViewHolder>() {

    private var restaurants = ArrayList<TrainingItemViewState>()
    private var oldSize = 0

    fun paginateData(restaurants: ArrayList<TrainingItemViewState>) {
        val newSize = restaurants.size
        if (oldSize >= newSize) return
        this.restaurants = restaurants
        notifyItemRangeInserted(oldSize, newSize - oldSize)
        oldSize = newSize
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        return TrainingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem_training, parent, false))
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

}