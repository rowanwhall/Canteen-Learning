package personal.rowan.canteenlearning.training

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import personal.rowan.canteenlearning.training.dagger.TrainingScope
import javax.inject.Inject

/**
 * Created by Rowan Hall
 */

@TrainingScope
class TrainingViewModel @Inject constructor(var repository: TrainingRepository) : ViewModel() {

    private val data: MutableLiveData<TrainingViewState> = MutableLiveData()

    init {
        data.value = TrainingViewState.starting()
    }

    fun state(): LiveData<TrainingViewState> {
        return data
    }

    fun loadData(clear: Boolean) {
        val viewState = data.value!!
        if (!viewState.canPaginate()) return
        repository.search(if (clear) 0 else viewState.offset)
                .map { viewState.fromResponse(it, clear) }
                .startWith(viewState.progress())
                .subscribe({ data.value = it }, { data.value = viewState.failure(it) })
    }

}