package com.sugoigroup.myapplicationtv5

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.app.DetailsSupportFragmentBackgroundController
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import androidx.leanback.widget.Action
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ClassPresenterSelector
import androidx.leanback.widget.DetailsOverviewRow
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnActionClickedListener
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * A wrapper fragment for leanback details screens.
 * It shows a detailed view of video and its metadata plus related videos.
 */
class VideoDetailsFragment : DetailsSupportFragment() {
    private lateinit var mDetailsBackground: DetailsSupportFragmentBackgroundController
    private lateinit var mPresenterSelector: ClassPresenterSelector
    private lateinit var mAdapter: ArrayObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Test"

        mPresenterSelector = ClassPresenterSelector()
        mAdapter = ArrayObjectAdapter(mPresenterSelector)

        mDetailsBackground = DetailsSupportFragmentBackgroundController(this)
        Glide.with(requireActivity())
            .asBitmap()
            .centerCrop()
            .error(R.drawable.default_background)
            .load("https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    bitmap: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    mDetailsBackground.enableParallax()
                    mDetailsBackground.coverBitmap = bitmap
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
        setupDetailsOverviewRow()
        setupRelatedMovieListRow()
        onItemViewClickedListener = MyItemViewClickedListener()

        adapter = mAdapter
    }

    private fun convertDpToPixel(context: Context, dp: Int): Int {
        val density = context.applicationContext.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }
    private fun setupDetailsOverviewRow() {
        val cardItemData = CardItemData(
            title = "title",
            content = "content",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BMjYzMGU0MzUtMTUxYy00MzE4LWFiMjctOTgwODg1ZjkwNTc0XkEyXkFqcGdeQXVyMTAyMjQ3NzQ1._V1_UX140_CR0,0,140,209_AL_.jpg"
        )

        val row = DetailsOverviewRow(cardItemData)
        row.imageDrawable = ContextCompat.getDrawable(requireActivity(), R.drawable.default_background)
        val width =  convertDpToPixel(requireActivity(), 274)
        val height = convertDpToPixel(requireActivity(), 274)
        Glide.with(requireActivity())
            .load(cardItemData.imageUrl)
            .centerCrop()
            .error(R.drawable.default_background)
            .into(object : CustomTarget<Drawable>(width, height) {
                override fun onResourceReady(drawable: Drawable,
                    transition: Transition<in Drawable>?) {
                    row.imageDrawable = drawable
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })


        val actionAdapter = ArrayObjectAdapter()

        actionAdapter.add(
            Action(
                1,
                "액션1",
                "액션라벨1")
        )
        actionAdapter.add(
            Action(
                2,
                "액션2",
                "액션라벨2")
        )
        actionAdapter.add(
            Action(
                3,
                "액션3",
                "액션라벨3")
        )
        row.actionsAdapter = actionAdapter
        mAdapter.add(row)

        val detailsPresenter = FullWidthDetailsOverviewRowPresenter(MyDetailsDescriptionPresenter())

        mPresenterSelector.addClassPresenter(DetailsOverviewRow::class.java, detailsPresenter)

        val shadowDisabledRowPresenter = ListRowPresenter()
        shadowDisabledRowPresenter.shadowEnabled = false

        setupActionButtonListeners(detailsPresenter)
    }

    private fun setupRelatedMovieListRow() {
        val subcategories = arrayOf("관련영상")

        val listRowAdapter = ArrayObjectAdapter(MyCardItemPresenter())
        listRowAdapter.add(
            CardItemData(
                title = "타이틀1",
                content = "컨텐츠1",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjYzMGU0MzUtMTUxYy00MzE4LWFiMjctOTgwODg1ZjkwNTc0XkEyXkFqcGdeQXVyMTAyMjQ3NzQ1._V1_UX140_CR0,0,140,209_AL_.jpg"
            )
        )
        listRowAdapter.add(
            CardItemData(
                title = "타이틀2",
                content = "컨텐츠2",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMWUwOThjYTAtZWYyYy00YjllLTkxYjEtNTJmNTI5N2M1NjkxXkEyXkFqcGdeQXVyOTU0NjY1MDM@._V1_UX140_CR0,0,140,209_AL_.jpg"
            )
        )

        val header = HeaderItem(0, subcategories[0])
        mAdapter.add(ListRow(header, listRowAdapter))
        mPresenterSelector.addClassPresenter(ListRow::class.java, ListRowPresenter())
    }

    private fun setupActionButtonListeners(detailsPresenter: FullWidthDetailsOverviewRowPresenter) {
        detailsPresenter.onActionClickedListener = OnActionClickedListener { action ->
            if (action.id == 1L) {
                 val intent = Intent(requireActivity(), PlaybackActivity::class.java)
                 startActivity(intent)
            } else {
                Toast.makeText(requireActivity(), action.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupActionButtons(row:DetailsOverviewRow) {


    }

    class MyDetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {

        override fun onBindDescription(
            viewHolder: ViewHolder,
            item: Any
        ) {
            val cardItemData = item as CardItemData

            viewHolder.title.text = cardItemData.title
            viewHolder.body.text = cardItemData.content
        }
    }


    private inner class MyItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder,
            item: Any,
            rowViewHolder: RowPresenter.ViewHolder,
            row: Row
        ) {
            when(item) {
                is CardItemData -> {
                    val intent = Intent(activity!!, DetailsActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}