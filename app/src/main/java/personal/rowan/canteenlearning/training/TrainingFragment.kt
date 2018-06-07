package personal.rowan.canteenlearning.training

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import personal.rowan.canteenlearning.R
import personal.rowan.canteenlearning.training.dagger.TrainingComponent
import personal.rowan.canteenlearning.util.restoreViewModel
import javax.inject.Inject

import kotlinx.android.synthetic.main.fragment_training.*
import personal.rowan.canteenlearning.training.recycler.TrainingAdapter

/**
 * Created by Rowan Hall
 */
class TrainingFragment : Fragment() {

    companion object {

        const val ARG_LAT = "LAT"
        const val ARG_LNG = "LNG"

        fun newInstance(lat: Double, lng: Double): TrainingFragment {
            val args = Bundle()
            args.putDouble(ARG_LAT, lat)
            args.putDouble(ARG_LNG, lng)
            val fragment = TrainingFragment()
            fragment.arguments = args
            return fragment
        }

    }

    @Inject
    lateinit var viewModel: TrainingViewModel

    private val adapter: TrainingAdapter = TrainingAdapter()
    private val disposables = CompositeDisposable()
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments!!
        TrainingComponent.inject(this, args.getDouble(ARG_LAT), args.getDouble(ARG_LNG))
        viewModel = restoreViewModel(viewModel)
        viewModel.state().observe(this, Observer<TrainingViewState> {
            it?.let {
                training_refresh.isRefreshing = it.showProgress
                adapter.paginateData(it.restaurants)
                if (it.showError) {
                    errorSnackbar = Snackbar.make(training_recycler, R.string.training_restaurant_fetch_error, Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.training_restaurant_retry, {
                                errorSnackbar?.dismiss()
                                viewModel.loadData(false)
                            })
                } else {
                    errorSnackbar?.dismiss()
                }
            }
        })
        viewModel.loadData(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        training_recycler.layoutManager = layoutManager
        LinearSnapHelper().attachToRecyclerView(training_recycler)
        training_recycler.adapter = adapter
        disposables.add(RxRecyclerView.scrollEvents(training_recycler)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (layoutManager.findLastVisibleItemPosition() >= adapter.itemCount - 1) {
                        viewModel.loadData(false)
                    }
                }
        )

        training_refresh.isEnabled = false // We do not allow for refreshing this screen
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.dispose()
    }

}