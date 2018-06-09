package personal.rowan.canteenlearning.training.recycler

import android.arch.lifecycle.Observer
import android.content.res.ColorStateList
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.listitem_training.view.*
import personal.rowan.canteenlearning.R

/**
 * Created by Rowan Hall
 */
class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var selectionDisposables = CompositeDisposable()
    private var selectionObserver: Observer<TrainingItemSelection>? = null

    fun bind(viewState: TrainingItemViewState) {
        val context = itemView.context
        itemView.training_item_name_text.text = viewState.name()
        itemView.training_item_address_text.text = viewState.address()
        itemView.training_item_cuisines_text.text = viewState.cuisines(context)
        itemView.training_item_cost_text.text = viewState.cost(context)

        fun bindButtonColors(selection: TrainingItemSelection, button: Button, targetSelection: TrainingItemSelection, @ColorRes selectedColor: Int) {
            button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context,
                    if (selection == targetSelection) selectedColor else R.color.gray))
        }

        selectionObserver?.let {
            viewState.selection.removeObserver(selectionObserver!!)
        }
        selectionObserver = Observer {
            it?.let {
                bindButtonColors(it, itemView.training_item_negative_button, TrainingItemSelection.NEGATIVE, android.R.color.holo_red_light)
                bindButtonColors(it, itemView.training_item_neutral_button, TrainingItemSelection.NEUTRAL, android.R.color.holo_blue_light)
                bindButtonColors(it, itemView.training_item_positive_button, TrainingItemSelection.POSITIVE, android.R.color.holo_green_light)
            }
        }
        viewState.selection.observeForever(selectionObserver!!)

        selectionDisposables.dispose()
        selectionDisposables = CompositeDisposable()
        selectionDisposables.addAll(
                RxView.clicks(itemView.training_item_negative_button).subscribe {
                    viewState.selection.value = TrainingItemSelection.NEGATIVE
                },
                RxView.clicks(itemView.training_item_neutral_button).subscribe {
                    viewState.selection.value = TrainingItemSelection.NEUTRAL
                },
                RxView.clicks(itemView.training_item_positive_button).subscribe {
                    viewState.selection.value = TrainingItemSelection.POSITIVE
                }
        )
    }

}