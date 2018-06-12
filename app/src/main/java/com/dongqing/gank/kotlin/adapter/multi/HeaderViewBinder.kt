package com.dongqing.gank.kotlin.ui.adapter.multi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.gank.HeaderBean
import kotlinx.android.synthetic.main.item_header.view.*

import me.drakeet.multitype.ItemViewBinder

class HeaderViewBinder : ItemViewBinder<HeaderBean, HeaderViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_header, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, headerBean: HeaderBean) {
        holder.content.text = headerBean.title
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content = itemView.text!!
    }
}
