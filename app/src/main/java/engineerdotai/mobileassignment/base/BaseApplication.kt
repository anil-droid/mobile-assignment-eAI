package engineerdotai.mobileassignment.base

import android.app.Application
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import engineerdotai.mobileassignment.di.appModule
import engineerdotai.mobileassignment.di.networkModule
import engineerdotai.mobileassignment.manager.ActivityLifecycleManager
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin


class BaseApplication : Application() {

    val activityLifecycleManager: ActivityLifecycleManager? by inject()

    var deepLinkBundle: Bundle? = null

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        startKoin(this, listOf(appModule, networkModule))

        Fresco.initialize(this)


        registerActivityLifecycleCallbacks(activityLifecycleManager)


    }

    companion object {

        private lateinit var sInstance: BaseApplication

        fun getInstance() = sInstance
    }
}