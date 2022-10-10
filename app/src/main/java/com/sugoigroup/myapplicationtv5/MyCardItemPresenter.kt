package com.sugoigroup.myapplicationtv5

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide

class MyCardItemPresenter : Presenter() {
    private lateinit var defaultPosterImage: Drawable

    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent?.context)
                .inflate(
                    R.layout.item_cardbox,
                    parent,
                    false
                )
        defaultPosterImage = parent?.context?.getDrawable(R.drawable.movie)!!
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        (viewHolder as MyViewHolder).apply {
            val data = (item as CardItemData)
            setTitleText(data.title)
            setContentText(data.content)
            Glide.with(viewHolder.view.context)
                .load(data.imageUrl)
                .centerCrop()
                .error(defaultPosterImage)
                .into(getMainImageView())
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        TODO("Not yet implemented")
    }

    inner class MyViewHolder(view: View) : ViewHolder(view) {
        private var imageCardView: ImageCardView

        init {
            imageCardView = (view as ImageCardView)
            imageCardView.setMainImageDimensions(280, 418)
        }

        fun setTitleText(inputText: String?) {
            imageCardView.titleText = inputText
        }

        fun setContentText(inputText: String?) {
            imageCardView.contentText = inputText
        }

        fun getMainImageView() = imageCardView.mainImageView
    }
}