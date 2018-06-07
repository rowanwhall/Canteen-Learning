package personal.rowan.canteenlearning.training.dagger

import dagger.Component
import personal.rowan.canteenlearning.application.App
import personal.rowan.canteenlearning.application.dagger.component.AppComponent
import personal.rowan.canteenlearning.application.dagger.component.ZomatoApiModule
import personal.rowan.canteenlearning.training.TrainingFragment

/**
 * Created by Rowan Hall
 */

@TrainingScope
@Component(modules = arrayOf(TrainingModule::class, ZomatoApiModule::class), dependencies = arrayOf(AppComponent::class))
interface TrainingComponent {

    fun inject(fragment: TrainingFragment)

    companion object {
        fun inject(fragment: TrainingFragment, lat: Double, lng: Double) {
            DaggerTrainingComponent.builder()
                    .appComponent(App.applicationComponent(fragment.context!!))
                    .trainingModule(TrainingModule(Pair(lat, lng)))
                    .build()
                    .inject(fragment)
        }
    }

}