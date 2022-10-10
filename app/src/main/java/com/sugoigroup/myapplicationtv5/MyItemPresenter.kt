package com.sugoigroup.myapplicationtv5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter

class MyItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent?.context)
                .inflate(
                    R.layout.item_box,
                    parent,
                    false
                )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        (viewHolder!! as MyViewHolder).setText(item as String?)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        TODO("Not yet implemented")
    }

    inner class MyViewHolder(view: View) : ViewHolder(view) {
        private  var textView: TextView

        init {
            textView = view.findViewById(R.id.textView)
        }

        fun setText(inputText:String?) {
            textView.text = inputText
        }
    }
}