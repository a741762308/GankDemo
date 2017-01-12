package com.jsqix.dongqing.gank.adapter.recycler;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.bean.douban.DoubanFilm;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by dongqing on 2017/1/12.
 */

public class DoubanFilmAdapter extends CommonAdapter<DoubanFilm.SubjectsBean>{
    public DoubanFilmAdapter(Context context, int layoutId, List<DoubanFilm.SubjectsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, DoubanFilm.SubjectsBean subjectsBean, int position) {
        ImageView image = holder.getView(R.id.film_image);
        Glide.with(mContext).load(subjectsBean.getImages().getLarge()).crossFade().into(image);
//        holder.setText(R.id.film_name,subjectsBean.getTitle());
//        holder.setText(R.id.film_date，subjectsBean.)
    }
}
