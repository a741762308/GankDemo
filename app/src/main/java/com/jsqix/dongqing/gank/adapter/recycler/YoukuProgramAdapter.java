package com.jsqix.dongqing.gank.adapter.recycler;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.bean.youku.YKProgram;
import com.jsqix.dongqing.gank.utils.DateUtil;
import com.jsqix.dongqing.gank.utils.StringUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by dongqing on 2017/1/16.
 */

public class YoukuProgramAdapter extends CommonAdapter<YKProgram.ShowsBean> {
    public YoukuProgramAdapter(Context context, int layoutId, List<YKProgram.ShowsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, YKProgram.ShowsBean showsBean, int position) {
        ImageView image = holder.getView(R.id.yk_image);
        Glide.with(mContext).load(showsBean.getPoster()).crossFade().into(image);
        holder.setText(R.id.tv_name, showsBean.getName());
        if ("电影".equals(showsBean.getCategory())) {
            holder.setVisible(R.id.layout_length, false);
        } else {
            holder.setVisible(R.id.layout_length, true);
        }
        if (StringUtils.toInt(showsBean.getCompleted()) == 1) {
            holder.setText(R.id.tv_length, showsBean.getEpisode_count() + "集全");
        } else {
            if (StringUtils.toInt(showsBean.getEpisode_count()) == 0) {
                if ("综艺".equals(showsBean.getCategory())) {
                    holder.setText(R.id.tv_length, "更新至" + (DateUtil.isDate(showsBean.getEpisode_updated(), "yyyyMMdd") ? showsBean.getEpisode_updated() : showsBean.getEpisode_updated() + "期"));
                } else {
                    holder.setText(R.id.tv_length, "更新至" + showsBean.getEpisode_updated() + "集");
                }
            } else {
                if (StringUtils.toInt(showsBean.getEpisode_updated()) == StringUtils.toInt(showsBean.getEpisode_count())) {
                    holder.setText(R.id.tv_length, showsBean.getEpisode_count() + "集全");
                } else {
                    holder.setText(R.id.tv_length, "更新至" + showsBean.getEpisode_updated() + "集");
                }
            }
        }
        if ("综艺".equals(showsBean.getCategory()) || "体育".equals(showsBean.getCategory()) || "纪录片".equals(showsBean.getCategory())) {
            holder.setText(R.id.tv_comment, "播放：" + StringUtils.toNumber(showsBean.getView_count()));
        } else {
            holder.setText(R.id.tv_comment, "评分：" + showsBean.getScore());
        }

    }
}
