package engineerdotai.mobileassignment.network


import engineerdotai.mobileassignment.base.model.BaseResponseModelGeneric
import engineerdotai.mobileassignment.network.model.response.ResponseDataUserFeed
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


class AppRestService(
    internal val appRestApiFast: AppRestApiFast
) {


    fun getFeed(
        offset: Int
    ): Observable<Response<BaseResponseModelGeneric<ResponseDataUserFeed>>> {
        val apiCall = appRestApiFast.searchUsersFilter(offset = offset)
        return apiCall
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
