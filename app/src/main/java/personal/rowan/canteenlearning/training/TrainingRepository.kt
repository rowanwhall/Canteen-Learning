package personal.rowan.canteenlearning.training

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import personal.rowan.canteenlearning.network.ZomatoWebService
import personal.rowan.canteenlearning.network.model.ZomatoSearchResponse
import personal.rowan.canteenlearning.training.dagger.TrainingScope
import javax.inject.Inject

/**
 * Created by Rowan Hall
 */

@TrainingScope
class TrainingRepository @Inject constructor(var webService: ZomatoWebService, var latLng: Pair<Double, Double>) {

    fun search(start: Int): Observable<ZomatoSearchResponse> {
        return webService.searchRestaurants(latLng.first, latLng.second, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}