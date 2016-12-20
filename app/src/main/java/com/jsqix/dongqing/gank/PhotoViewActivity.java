package com.jsqix.dongqing.gank;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsqix.dongqing.gank.api.Api;
import com.jsqix.utils.BaseActivity;
import com.jsqix.utils.FrameApplication;
import com.jsqix.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;

import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.ResponseBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoView;

@RuntimePermissions
public class PhotoViewActivity extends BaseActivity implements View.OnClickListener {

    private PhotoView photoView;
    private ImageView back, share, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        initView();
    }

    private void initView() {
        photoView = (PhotoView) findViewById(R.id.photo_view);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        share = (ImageView) findViewById(R.id.share);
        share.setOnClickListener(this);
        save = (ImageView) findViewById(R.id.save);
        save.setOnClickListener(this);
        Glide.with(this).load(getIntent().getStringExtra("url")).crossFade().into(photoView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                showShare();
                break;
            case R.id.save:
                PhotoViewActivityPermissionsDispatcher.onAllowWithCheck(this);
                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(getIntent().getStringExtra("url"));
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

    private void savePic2Dic(final String url) {
        Api.getInstance().service.downloadPicFromNet(url)
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        String path = Environment.getExternalStorageDirectory().getPath() + "/" + FrameApplication.getInstance().getPackageName() + "/down";
                        File picFileDir = new File(path);
                        if (!picFileDir.exists()) {
                            picFileDir.mkdirs();
                        }
                        String name = url.substring(url.lastIndexOf("/") + 1);
                        try {
                            File picFile = new File(picFileDir, name);
                            FileOutputStream fos = new FileOutputStream(picFile);
                            fos.write(responseBody.bytes());
                            fos.flush();
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return name;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.makeToast(PhotoViewActivity.this, "下载失败");
                    }

                    @Override
                    public void onNext(String s) {
                        Utils.makeToast(PhotoViewActivity.this, "下载成功");
                    }
                });
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    protected void onAllow() {
        savePic2Dic(getIntent().getStringExtra("url"));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PhotoViewActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    protected void onDeny() {
        Utils.makeToast(this, "请授予内存卡读写权限");
    }


    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    protected void requestPermission(final PermissionRequest request) {
        request.proceed();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void onAsk() {
        Utils.makeToast(this, "请到应用详情页权限管理中授予权限！");
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}
