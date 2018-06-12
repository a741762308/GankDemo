package com.dongqing.gank.kotlin.adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.dongqing.gank.kotlin.GlideApp
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.douban.DoubanFilm
import com.dongqing.gank.kotlin.ui.activity.WebViewActivity
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

class DoubanFilmAdapter : CommonAdapter<DoubanFilm.SubjectsBean> {

    constructor(context: Context?, datas: List<DoubanFilm.SubjectsBean>) : super(context, R.layout.item_recycler_douban_film, datas)

    override fun convert(holder: ViewHolder?, t: DoubanFilm.SubjectsBean?, position: Int) {
        val image = holder!!.getView<ImageView>(R.id.film_image)
        GlideApp.with(mContext).load(t!!.images.large).into(image)
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("url", t.alt)
            intent.putExtra("name", t.title)
            intent.putExtra("imgUrl", t.images.small)
            mContext.startActivity(intent)
        }
    }
}