package engineerdotai.mobileassignment.ui.feed.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import engineerdotai.mobileassignment.R
import engineerdotai.mobileassignment.base.BaseListAdapterDiffCallBack
import engineerdotai.mobileassignment.callbacks.AdapterViewClickListener
import engineerdotai.mobileassignment.network.model.response.User
import kotlinx.android.synthetic.main.adp_view_feed.view.*


class AdapterUserFeed(private val adapterViewClickListener: AdapterViewClickListener<User>) :
    ListAdapter<User, AdapterUserFeed.ViewHolder>(BaseListAdapterDiffCallBack<User>()),
    AdapterViewClickListener<String> {
    override fun onClickAdapterView(objectAtPosition: String?, viewType: Int, position: Int) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutId = R.layout.adp_view_feed
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), getItemViewType(position), adapterViewClickListener)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            result: User?,
            itemViewType: Int,
            adapterViewClickListener: AdapterViewClickListener<User>
        ) {

            result?.let {

                itemView.tv_user_name?.text = it.name
                itemView.iv_user_thumb.setImageURI(it.image)



                if (it.firstImage.isNullOrBlank()) {
                    //EVEN
                    itemView.sv_feed_image_first?.visibility = View.GONE

                } else {
                    //ODD
                    itemView.sv_feed_image_first?.visibility = View.VISIBLE

                }

                itemView.sv_feed_image_first.setImageURI(it.firstImage)

                val feedImages = it.items
                if (!feedImages.isNullOrEmpty()) {

                    itemView.rv_feed_images?.visibility= View.VISIBLE
                    val adapteFeedImages = AdapteFeedImages(this@AdapterUserFeed)
                    itemView.rv_feed_images?.adapter = adapteFeedImages
                    adapteFeedImages.submitList(feedImages)

                }else{

                    itemView.rv_feed_images?.visibility= View.GONE

                }


            }



            itemView.setOnClickListener {
                adapterViewClickListener.onClickAdapterView(result, 0, adapterPosition)
            }

        }


    }


}