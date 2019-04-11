package engineerdotai.mobileassignment.ui.feed

import android.arch.lifecycle.MutableLiveData
import engineerdotai.mobileassignment.base.BaseViewModel
import engineerdotai.mobileassignment.base.model.BaseResponseModelGeneric
import engineerdotai.mobileassignment.network.RestResponse
import engineerdotai.mobileassignment.network.callbacks.APIResponseCallback
import engineerdotai.mobileassignment.network.model.response.ResponseDataUserFeed

class ViewModelUserFeed(repository: RepositoryUserFeed) : BaseViewModel<RepositoryUserFeed>(repository) {


    var eventGetUserFeed = MutableLiveData<RestResponse<BaseResponseModelGeneric<ResponseDataUserFeed>>>()


    fun getUserFeed(offset: Int) {
        eventGetUserFeed.postValue(RestResponse())

        repository.getUserFeed(offset,

            APIResponseCallback {

                eventGetUserFeed.postValue(it)

            })

    }


}