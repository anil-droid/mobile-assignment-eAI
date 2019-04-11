package engineerdotai.mobileassignment.network

import engineerdotai.mobileassignment.base.model.BaseResponseModelGeneric
import engineerdotai.mobileassignment.constants.Network
import engineerdotai.mobileassignment.network.model.response.ResponseDataUserFeed
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AppRestApiFast {


    @GET("/api/users?")
    fun searchUsersFilter(
        @Query("limit") limit: Int = Network.APIResult.BASE_LIMIT,
        @Query("offset") offset: Int = Network.APIResult.BASE_OFF_SET
    ): Observable<Response<BaseResponseModelGeneric<ResponseDataUserFeed>>>


}

