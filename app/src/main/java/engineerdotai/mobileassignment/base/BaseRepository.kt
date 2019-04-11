package engineerdotai.mobileassignment.base

import engineerdotai.mobileassignment.network.APICallAndResponseHelper
import engineerdotai.mobileassignment.network.AppRestService
import engineerdotai.mobileassignment.network.callbacks.APIResponseCallback
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Response

open class BaseRepository : KoinComponent {

    internal val appRestService: AppRestService by inject()

    private var compositeDisposable = CompositeDisposable()

    fun onCleared() {
        compositeDisposable.clear()
    }

    fun <T> sendApiCall(observable: Observable<Response<T>>, callback: APIResponseCallback<T>?) {
        APICallAndResponseHelper.call(observable, callback, compositeDisposable)
    }

}