package personal.rowan.canteenlearning.training.dagger

import dagger.Module
import dagger.Provides

/**
 * Created by Rowan Hall
 */

@Module
class TrainingModule(val latLng: Pair<Double, Double>) {

    @Provides
    fun provideLatLng(): Pair<Double, Double> {
        return latLng
    }

}