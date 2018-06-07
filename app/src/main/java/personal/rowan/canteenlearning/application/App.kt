package personal.rowan.canteenlearning.application

import android.app.Application
import android.content.Context
import personal.rowan.canteenlearning.application.dagger.component.AppComponent
import personal.rowan.canteenlearning.application.dagger.component.DaggerAppComponent
import personal.rowan.canteenlearning.application.dagger.module.AppModule

/**
 * Created by Rowan Hall
 */
class App : Application() {

    private lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    companion object {
        fun applicationComponent(context: Context): AppComponent {
            return (context.applicationContext as App).mAppComponent
        }
    }

}