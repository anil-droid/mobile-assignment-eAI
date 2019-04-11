package engineerdotai.mobileassignment.ui.feed

import engineerdotai.mobileassignment.base.BaseRepository
import engineerdotai.mobileassignment.base.model.BaseResponseModelGeneric
import engineerdotai.mobileassignment.network.callbacks.APIResponseCallback
import engineerdotai.mobileassignment.network.model.response.ResponseDataUserFeed

class RepositoryUserFeed : BaseRepository() {


    fun getUserFeed(
        offset: Int,
        callback: APIResponseCallback<BaseResponseModelGeneric<ResponseDataUserFeed>>
    ) {
        sendApiCall(appRestService.getFeed(offset), callback)
    }


}
