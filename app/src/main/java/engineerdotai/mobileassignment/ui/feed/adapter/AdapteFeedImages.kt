package engineerdotai.mobileassignment.ui.feed.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import engineerdotai.mobileassignment.R
import engineerdotai.mobileassignment.base.BaseListAdapterDiffCallBack
import engineerdotai.mobileassignment.callbacks.AdapterViewClickListener
import kotlinx.android.synthetic.main.adp_view_feed_image.view.*

class AdapteFeedImages(private val adapterViewClickListener: AdapterViewClickListener<String>) :
    ListAdapter<String, AdapteFeedImages.ViewHolder>(BaseListAdapterDiffCallBack<String>()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutId = R.layout.adp_view_feed_image
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), getItemViewType(position), adapterViewClickListener)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            result: String?,
            itemViewType: Int,
            adapterViewClickListener: AdapterViewClickListener<String>
        ) {

            result?.let {

                itemView.sv_feed_image?.setImageURI(it)
            }



            itemView.setOnClickListener {
                adapterViewClickListener.onClickAdapterView(result, 0, adapterPosition)
            }

        }


    }


}