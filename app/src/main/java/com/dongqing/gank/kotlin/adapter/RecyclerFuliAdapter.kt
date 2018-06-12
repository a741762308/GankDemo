package com.dongqing.gank.kotlin.ui.adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.dongqing.gank.kotlin.GlideApp
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.gank.DataBean
import com.dongqing.gank.kotlin.ui.activity.PhotoViewActivity
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

class RecyclerFuliAdapter : CommonAdapter<DataBean> {
    constructor(context: Context?, datas: List<DataBean>) : super(context, R.layout.item_recycler_fuli, datas)

    override fun convert(holder: ViewHolder?, t: DataBean?, position: Int) {
        var image = holder!!.getView<ImageView>(R.id.image)
        GlideApp.with(mContext).load(t!!.url).into(image)
        holder.itemView.setOnClickListener {
            var intent = Intent(mContext, PhotoViewActivity::class.java)
            intent.putExtra("url", t.url)
            mContext.startActivity(intent)
        }
    }
}