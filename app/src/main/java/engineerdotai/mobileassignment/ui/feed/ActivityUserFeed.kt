package engineerdotai.mobileassignment.ui.feed

import android.arch.lifecycle.Observer
import android.os.Bundle
import engineerdotai.mobileassignment.base.BaseActivityRecyclerView
import engineerdotai.mobileassignment.network.RestResponse
import engineerdotai.mobileassignment.network.model.response.ResponseDataUserFeed
import engineerdotai.mobileassignment.network.model.response.User
import engineerdotai.mobileassignment.ui.feed.adapter.AdapterUserFeed
import org.koin.android.architecture.ext.viewModel

class ActivityUserFeed :
    BaseActivityRecyclerView<ResponseDataUserFeed, User, AdapterUserFeed.ViewHolder, AdapterUserFeed>() {

    val viewModelUserFeed: ViewModelUserFeed by viewModel()


    override fun loadDataFromApi() {

        viewModelUserFeed.getUserFeed(dataList.size)

    }


    override fun onClickAdapterView(objectAtPosition: User?, viewType: Int, position: Int) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        needThisLoadMoreInApi = true
        super.onCreate(savedInstanceState)

    }

    override fun getAdapterFor(): AdapterUserFeed? {

        return AdapterUserFeed(this)
    }


    override fun subscribeUi() {

        viewModelUserFeed.eventGetUserFeed.observe(this, Observer {

            when (it?.status) {

                RestResponse.Status.LOADING -> {
                    if (dataList.isNullOrEmpty())
                        showLoadingView()
                }
                RestResponse.Status.SUCCESS -> {
                    hideProgressDialog()
                    val listFromServer = it.data?.data?.users

                    listFromServer?.let {


                        for (feed in listFromServer) {


                            if (feed != null) {
                                val listofImages = feed.items

                                if (!listofImages.isNullOrEmpty()) {


                                    if (listofImages.size % 2 != 0) {
                                        //Odd

                                        feed.firstImage = listofImages[0]
                                        listofImages.removeAt(0)
                                    }


                                }

                            }

                        }

                    }

                    onSuccessOfGetList(listFromServer)

                }
                RestResponse.Status.ERROR -> {
                    hideProgressDialog()
                    showErrorView(it)

                }
                RestResponse.Status.LOGIN -> {
                }
                null -> {
                }
            }
        })

    }


}