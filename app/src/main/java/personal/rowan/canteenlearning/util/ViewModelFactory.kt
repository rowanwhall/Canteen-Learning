package personal.rowan.canteenlearning.util

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment

/**
 * Created by Rowan Hall
 */

fun <VM : ViewModel> Fragment.restoreViewModel(vm: VM): VM {
    return ViewModelProviders.of(this,
            object: ViewModelProvider.AndroidViewModelFactory(context!!.applicationContext as Application) {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return vm as T
                }
            })
            .get(vm.javaClass)
}