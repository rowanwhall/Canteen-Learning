package personal.rowan.canteenlearning.training.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

import kotlinx.android.synthetic.main.listitem_training.view.*

/**
 * Created by Rowan Hall
 */
class TrainingViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(name: String) {
        itemView.training_item_name_text.text=name
    }

}