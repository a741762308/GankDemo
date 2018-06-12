package com.dongqing.gank.kotlin.ui.adapter.multi

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.gank.HeaderBean
import com.dongqing.gank.kotlin.bean.gank.ImgDataBean
import com.dongqing.gank.kotlin.bean.gank.RecentBean
import kotlinx.android.synthetic.main.item_cardview.view.*

import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

class RecentViewBinder : ItemViewBinder<RecentBean, RecentViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_cardview, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, recent: RecentBean) {
        val items = Items()
        items.add(recent.headerBean)
        items.addAll(recent.list)
        holder.setData(items)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var adapter = MultiTypeAdapter()
        var recyclerView = itemView.recyclerView!!

        init {
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            adapter = MultiTypeAdapter()
            adapter.register(HeaderBean::class.java, HeaderViewBinder())
            adapter.register(ImgDataBean::class.java, ImageViewBinder())
            recyclerView.adapter = adapter
            recyclerView.isFocusableInTouchMode = false

        }

        fun setData(items: Items) {
            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }
}
