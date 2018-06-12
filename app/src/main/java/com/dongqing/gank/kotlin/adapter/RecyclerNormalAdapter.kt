package com.dongqing.gank.kotlin.ui.adapter

import android.content.Context
import android.content.Intent
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.gank.DataBean
import com.dongqing.gank.kotlin.ui.activity.WebViewActivity
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

class RecyclerNormalAdapter : CommonAdapter<DataBean> {
    constructor(context: Context?, datas: List<DataBean>) : super(context, R.layout.item_recycler_normal, datas)

    override fun convert(holder: ViewHolder?, t: DataBean?, position: Int) {
        holder!!.setText(R.id.text, t!!.desc)
        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context, WebViewActivity::class.java)
            intent.putExtra("name", t.desc)
            intent.putExtra("url", t.url)
            holder.itemView.context.startActivity(intent)
        }
    }
}