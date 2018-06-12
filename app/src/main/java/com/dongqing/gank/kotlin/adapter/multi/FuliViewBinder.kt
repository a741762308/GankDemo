package com.dongqing.gank.kotlin.ui.adapter.multi

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dongqing.gank.kotlin.GlideApp
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.gank.FuliBean
import com.dongqing.gank.kotlin.ui.activity.PhotoViewActivity
import kotlinx.android.synthetic.main.item_fuli.view.*
import me.drakeet.multitype.ItemViewBinder
import java.text.SimpleDateFormat


class FuliViewBinder : ItemViewBinder<FuliBean, FuliViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_fuli, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, fuliBean: FuliBean) {
        GlideApp.with(holder.itemView.context).load(fuliBean.url).centerCrop().into(holder.image)
        val df = SimpleDateFormat("yyyy/MM/dd")
        holder.time.text = df.format(fuliBean.publishedAt)
        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context, PhotoViewActivity::class.java)
            intent.putExtra("url", fuliBean.url)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.image!!
        val time = itemView.text!!
    }
}
