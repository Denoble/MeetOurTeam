package com.gevcorst.s_g_coffeemeetsbagel.adapter

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gevcorst.s_g_coffeemeetsbagel.R
import com.gevcorst.s_g_coffeemeetsbagel.model.TeamMember
import com.gevcorst.s_g_coffeemeetsbagel.viewModel.TeamJsonPassingStatus

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<TeamMember>?) {
    val adapter = recyclerView.adapter as HomeFragmentRecyclerViewAdapter
    adapter.submitList(data)
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
    }
}

/**
 * This binding adapter displays the [TeamJsonPassingStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("teamMemberLoadingStatus")
fun bindStatus(statusImageView: ImageView, status: TeamJsonPassingStatus?) {
    when (status) {
        TeamJsonPassingStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        TeamJsonPassingStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        TeamJsonPassingStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
