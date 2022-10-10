package com.sugoigroup.myapplicationtv5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter

/**
 * Loads a grid of cards with movies to browse.
 */
class MainFragment : BrowseSupportFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUIElements()

        initHeaderAndRows()

        onItemViewClickedListener = MyItemViewClickedListener()
    }

    private fun setupUIElements() {
        title = getString(R.string.browse_title)
        // over title
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // set fastLane (or headers) background color
        brandColor = ContextCompat.getColor(requireActivity(), R.color.fastlane_background)
    }

    private fun initHeaderAndRows() {
        val myRowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // 헤더 추가
        val header1 = HeaderItem(1, "첫번째 메뉴")
        val header2 = HeaderItem(2, "두번째 메뉴")

        // 리스트 목록 추가

        val listRowAdapter1 = ArrayObjectAdapter(MyItemPresenter())
        listRowAdapter1.add("첫째메뉴아이템1")
        listRowAdapter1.add("첫째메뉴아이템2")
        listRowAdapter1.add("첫째메뉴아이템3")

        val listRowAdapter2 = ArrayObjectAdapter(MyCardItemPresenter())
        listRowAdapter2.add(
            CardItemData(
                title = "타이틀1",
                content = "컨텐츠1",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjYzMGU0MzUtMTUxYy00MzE4LWFiMjctOTgwODg1ZjkwNTc0XkEyXkFqcGdeQXVyMTAyMjQ3NzQ1._V1_UX140_CR0,0,140,209_AL_.jpg"
            )
        )
        listRowAdapter2.add(
            CardItemData(
                title = "타이틀2",
                content = "컨텐츠2",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMWUwOThjYTAtZWYyYy00YjllLTkxYjEtNTJmNTI5N2M1NjkxXkEyXkFqcGdeQXVyOTU0NjY1MDM@._V1_UX140_CR0,0,140,209_AL_.jpg"
            )
        )
        listRowAdapter2.add(
            CardItemData(
                title = "타이틀3",
                content = "컨텐츠3",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMDU2ZmM2OTYtNzIxYy00NjM5LTliNGQtN2JmOWQzYTBmZWUzXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UY209_CR0,0,140,209_AL_.jpg"
            )
        )


        myRowsAdapter.add(ListRow(header1, listRowAdapter1))
        myRowsAdapter.add(ListRow(header2, listRowAdapter2))

        adapter = myRowsAdapter
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
                is String -> {
                    val intent = Intent(activity!!, BrowseErrorActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}