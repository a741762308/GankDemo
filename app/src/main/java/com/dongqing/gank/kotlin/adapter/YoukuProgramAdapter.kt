package com.dongqing.gank.kotlin.adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.dongqing.gank.kotlin.GlideApp
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.youku.YKProgram
import com.dongqing.gank.kotlin.ui.activity.YoukuPlayerActivity
import com.dongqing.gank.kotlin.utils.DateUtil
import com.dongqing.gank.kotlin.utils.StringUtils
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

class YoukuProgramAdapter : CommonAdapter<YKProgram.ShowsBean> {
    constructor(context: Context?, datas: List<YKProgram.ShowsBean>) : super(context, R.layout.item_recycler_yk_program, datas)

    override fun convert(holder: ViewHolder?, t: YKProgram.ShowsBean?, position: Int) {
        var image = holder!!.getView<ImageView>(R.id.yk_image)
        GlideApp.with(mContext).load(t!!.poster).into(image)
        holder.setText(R.id.tv_name, t.name)
        if ("电影" == t.category) {
            holder.setVisible(R.id.layout_length, false)
        } else {
            holder.setVisible(R.id.layout_length, true)
        }
        if (StringUtils.toInt(t.completed) === 1) {
            holder.setText(R.id.tv_length, t.episode_count + "集全")
        } else {
            if (StringUtils.toInt(t.episode_count) === 0) {
                if ("综艺" == t.category) {
                    holder.setText(R.id.tv_length, "更新至" + if(DateUtil.isDate(t.episode_updated, "yyyyMMdd")) t.episode_updated else t.episode_updated + "期")
                } else {
                    holder.setText(R.id.tv_length, "更新至" + t.episode_updated + "集")
                }
            } else {
                if (StringUtils.toInt(t.episode_updated) === StringUtils.toInt(t.episode_count)) {
                    holder.setText(R.id.tv_length, t.episode_count + "集全")
                } else {
                    holder.setText(R.id.tv_length, "更新至" + t.episode_updated + "集")
                }
            }
        }
        if ("综艺" == t.category || "体育" == t.category || "纪录片" == t.category) {
            holder.setText(R.id.tv_comment, "播放：" + StringUtils.toNumber(t.view_count))
        } else {
            holder.setText(R.id.tv_comment, "评分：" + t.score)
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(mContext, YoukuPlayerActivity::class.java)
            intent.putExtra("id", t.id)
            intent.putExtra("url", t.play_link)
            mContext.startActivity(intent)
        }
    }
}