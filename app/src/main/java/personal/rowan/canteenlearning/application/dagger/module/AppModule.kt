package personal.rowan.canteenlearning.application.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Rowan Hall
 */

@Module
class AppModule(private val mContext: Context) {

    @Provides
    fun providesContext(): Context {
        return mContext
    }

}