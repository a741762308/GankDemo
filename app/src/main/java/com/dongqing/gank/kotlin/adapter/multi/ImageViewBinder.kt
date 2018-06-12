package com.dongqing.gank.kotlin.ui.adapter.multi

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dongqing.gank.kotlin.GlideApp
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.gank.ImgDataBean
import com.dongqing.gank.kotlin.ui.activity.WebViewActivity
import kotlinx.android.synthetic.main.item_image.view.*
import me.drakeet.multitype.ItemViewBinder

class ImageViewBinder : ItemViewBinder<ImgDataBean, ImageViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, image: ImgDataBean) {
        holder.content.text = image.desc
        if (image.images != null && image.images.size > 0) {
            holder.image.visibility = View.VISIBLE
            GlideApp.with(holder.itemView.context).load(image.images[0]).centerCrop().into(holder.image)
        } else {
            holder.image.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            var intent=Intent(holder.itemView.context,WebViewActivity::class.java)
            intent.putExtra("name", image.desc)
            intent.putExtra("url",image.url)
            holder.itemView.context.startActivity(intent) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.image!!
        var content = itemView.text!!
    }
}
