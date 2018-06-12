package com.dongqing.gank.kotlin.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import cn.sharesdk.onekeyshare.OnekeyShare
import com.dongqing.gank.kotlin.GlideApp
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.com.dongqing.gank.kotlin.ui.present.PhotoViewPresent
import com.dongqing.gank.kotlin.com.dongqing.gank.kotlin.ui.view.IPhotoView
import com.tbruyelle.rxpermissions2.Permission
import kotlinx.android.synthetic.main.activity_photo_view.*

class PhotoViewActivity : BaseMvpActivity<PhotoViewPresent, IPhotoView>(), View.OnClickListener, IPhotoView {
    override fun getLayoutId(): Int {
        return R.layout.activity_photo_view
    }

    private lateinit var urlPath: String
    private lateinit var savePath: String

    override fun permissions(p: Permission) {
        when {
            p.granted -> {
                mPresenter!!.downloadPicFromNet(urlPath, savePath)
            }
            p.shouldShowRequestPermissionRationale -> {
                toast("请授予存储权限")
            }
            else -> {
                toast("请到应用详情页权限管理中授予权限！")
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.fromParts("package", packageName, null)
                startActivity(intent)
            }
        }
    }

    override fun initView() {
        back.setOnClickListener(this)
        share.setOnClickListener(this)
        save.setOnClickListener(this)
        urlPath = intent.getStringExtra("url")
        savePath = Environment.getExternalStorageDirectory().path + "/" + packageName + "/down"
        GlideApp.with(mContext).load(intent.getStringExtra("url")).into(photo_view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.back -> finish()
            R.id.share -> {
                showShare()
            }
            R.id.save -> mPresenter!!.requestPermission()
        }
    }

    override fun showShare() {
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("干货Gank")
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn")
        // text是分享文本，所有平台都需要这个字段
        oks.text = "每天好妹子"
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(intent.getStringExtra("url"))
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本")
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK")
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        //oks.setSiteUrl("http://sharesdk.cn")

        // 启动分享GUI
        oks.show(this)
    }
}
