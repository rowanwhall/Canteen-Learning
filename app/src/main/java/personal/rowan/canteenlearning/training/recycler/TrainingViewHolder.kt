package personal.rowan.canteenlearning.training.recycler

import android.content.res.ColorStateList
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

import kotlinx.android.synthetic.main.listitem_training.view.*
import personal.rowan.canteenlearning.R

/**
 * Created by Rowan Hall
 */
class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var selectionDisposables = CompositeDisposable()

    fun bind(selectionSubject: PublishSubject<TrainingSelectionClickEvent>, viewState: TrainingItemViewState) {
        val context = itemView.context
        itemView.training_item_name_text.text = viewState.name()
        itemView.training_item_address_text.text = viewState.address()
        itemView.training_item_cuisines_text.text = viewState.cuisines(context)
        itemView.training_item_cost_text.text = viewState.cost(context)

        fun bindButtonColors(button: Button, targetSelection: TrainingItemSelection, @ColorRes selectedColor: Int) {
            button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context,
                    if (viewState.selection == targetSelection) selectedColor else R.color.gray))
        }

        bindButtonColors(itemView.training_item_negative_button, TrainingItemSelection.NEGATIVE, android.R.color.holo_red_light)
        bindButtonColors(itemView.training_item_neutral_button, TrainingItemSelection.NEUTRAL, android.R.color.holo_blue_light)
        bindButtonColors(itemView.training_item_positive_button, TrainingItemSelection.POSITIVE, android.R.color.holo_green_light)
        itemView.training_item_negative_button.isSelected = viewState.selection == TrainingItemSelection.NEGATIVE
        itemView.training_item_neutral_button.isSelected = viewState.selection == TrainingItemSelection.NEUTRAL
        itemView.training_item_positive_button.isSelected = viewState.selection == TrainingItemSelection.POSITIVE

        val adapterPosition = getAdapterPosition()
        selectionDisposables.dispose()
        selectionDisposables = CompositeDisposable()
        selectionDisposables.addAll(
                RxView.clicks(itemView.training_item_negative_button).subscribe {
                    selectionSubject.onNext(TrainingSelectionClickEvent(adapterPosition, TrainingItemSelection.NEGATIVE))
                },
                RxView.clicks(itemView.training_item_neutral_button).subscribe {
                    selectionSubject.onNext(TrainingSelectionClickEvent(adapterPosition, TrainingItemSelection.NEUTRAL))
                },
                RxView.clicks(itemView.training_item_positive_button).subscribe {
                    selectionSubject.onNext(TrainingSelectionClickEvent(adapterPosition, TrainingItemSelection.POSITIVE))
                }
        )
    }

}

data class TrainingSelectionClickEvent(val index: Int, val selection: TrainingItemSelection)