package engineerdotai.mobileassignment.base;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity() : AppCompatActivity() {


    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())


    }


}