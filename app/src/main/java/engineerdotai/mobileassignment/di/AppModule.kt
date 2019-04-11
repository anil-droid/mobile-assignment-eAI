package engineerdotai.mobileassignment.di


import engineerdotai.mobileassignment.manager.ActivityLifecycleManager
import engineerdotai.mobileassignment.ui.feed.RepositoryUserFeed
import engineerdotai.mobileassignment.ui.feed.ViewModelUserFeed
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {


    bean { ActivityLifecycleManager() }

    /*ZodiNotification*/
    viewModel { ViewModelUserFeed(get()) }
    bean { RepositoryUserFeed() }
}