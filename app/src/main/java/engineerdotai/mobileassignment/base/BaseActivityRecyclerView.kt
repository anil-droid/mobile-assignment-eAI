package engineerdotai.mobileassignment.base

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import engineerdotai.mobileassignment.R
import engineerdotai.mobileassignment.base.model.BaseResponseModelGeneric
import engineerdotai.mobileassignment.callbacks.AdapterViewClickListener
import engineerdotai.mobileassignment.constants.Network
import engineerdotai.mobileassignment.network.RestResponse
import engineerdotai.mobileassignment.utils.AndroidUtils
import engineerdotai.mobileassignment.utils.UiUtils
import engineerdotai.mobileassignment.utils.UiUtils.Companion.showProgressDialog
import kotlinx.android.synthetic.main.common_list_view.*

abstract class BaseActivityRecyclerView<T, E, VH : RecyclerView.ViewHolder?, A : ListAdapter<E, VH>> : BaseActivity(),
    AdapterViewClickListener<E> {

    var isLoading = false
    var isLoadMoreNeeded = false
    var needThisLoadMoreInApi = true


    val dataList: ArrayList<E?> = arrayListOf()

    var adapter: A? = null

    override fun getLayoutId(): Int {
        return R.layout.common_list_view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = getAdapterFor()

        adapter?.let {

            recyclerView.adapter = adapter
            it.submitList(dataList)

        }

        initScrollListener()

        subscribeUi()

        loadDataFromApi()
    }


    abstract fun subscribeUi()

    fun hideProgressDialog() {
        UiUtils.dismissProgressDialog()

    }

    fun showErrorView(it: RestResponse<BaseResponseModelGeneric<T>>) {
        hideProgressDialog()
        showSnackbar(it.getErrorMessage(), false)
    }

    private fun showSnackbar(errorMessage: String, b: Boolean) {
        UiUtils.showSnackBar(this, errorMessage, b)

    }

    fun showLoadingView() {
        showProgressDialog(this, AndroidUtils.getString(R.string.loading), null, true)

    }


    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (!isLoading && isLoadMoreNeeded && needThisLoadMoreInApi) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == dataList.size - 1) {
                        //bottom of list!
                        isLoading = true
                        loadDataFromApi()
                    }
                }
            }
        })


    }


    open fun onSuccessOfGetList(listFromServer: ArrayList<E?>?) {

        isLoading = true

        if (!listFromServer.isNullOrEmpty()) {

            isLoadMoreNeeded = listFromServer.size >= Network.APIResult.BASE_LIMIT

            dataList.addAll(listFromServer)
            adapter?.notifyDataSetChanged()


        } else {

            isLoadMoreNeeded = false

        }

        isLoading = false


    }

    abstract fun loadDataFromApi()


    abstract fun getAdapterFor(): A?


}