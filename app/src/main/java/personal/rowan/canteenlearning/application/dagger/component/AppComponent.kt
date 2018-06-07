package personal.rowan.canteenlearning.application.dagger.component

import android.content.Context
import dagger.Component
import personal.rowan.canteenlearning.application.dagger.module.AppModule

/**
 * Created by Rowan Hall
 */

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context(): Context

}