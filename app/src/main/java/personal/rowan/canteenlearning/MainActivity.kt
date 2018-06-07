package personal.rowan.canteenlearning

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import personal.rowan.canteenlearning.training.TrainingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, TrainingFragment.newInstance(33.7819856, -84.3818974))
                    .commit()
        }
    }
}
