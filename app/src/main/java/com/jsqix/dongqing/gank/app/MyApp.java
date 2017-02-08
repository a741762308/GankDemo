package com.jsqix.dongqing.gank.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.KeyEvent;

import com.jsqix.dongqing.gank.utils.ACache;
import com.jsqix.dongqing.gank.utils.ErrorHandler;
import com.jsqix.dongqing.gank.utils.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dongqing on 2016/12/13.
 */

public class MyApp extends Application {
    private static ErrorHandler crashHandler = null;
    private static MyApp mInstance;
    private static LinkedList<Activity> mList = new LinkedList<Activity>();
    long exitTime = 0;
    public static HashMap<String, String> REQUEST_HEADER = new HashMap<String, String>();

    public boolean closeAppByBack(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - this.exitTime) > 2000) // System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Utils.makeToast(this, "再按一次退出程序");
                this.exitTime = System.currentTimeMillis();
            } else {
                // finish();
                MyApp.exitApp();
            }
            return true;
        }
        return false;
    }

    public LinkedList<Activity> getActivityList() {
        return mList;
    }

    public ACache getFileCache() {
        return ACache.get(this);
    }

    // public static String weblogid="";
    // public static String sourceId = "";

    public static void addToActivityList(final Activity act) {
        MyApp.mList.add(act);
    }

    public static void delFromActivityList(final Activity act) {
        if (MyApp.mList.indexOf(act) != -1) {
            MyApp.mList.remove(act);
        }
    }

    public static void clearActivityStack() {
        for (int i = MyApp.mList.size() - 1; i >= 0; i--) {
            final Activity activity = MyApp.mList.get(i);
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 退出应用程序
     */
    public static void exitApp() {
        try {
            for (int i = MyApp.mList.size() - 1; i >= 0; i--) {
                final Activity activity = MyApp.mList.get(i);
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (final Exception e) {
        } finally {
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    public static MyApp getInstance() {
        return MyApp.mInstance;
    }

    public static void setCrashHandler(final Context c) {
        MyApp.crashHandler.setToErrorHandler(c);
    }


    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            RunningAppProcessInfo info = (RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    public boolean isAppOnForeground() {
        final ActivityManager activityManager = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        final String packageName = this.getApplicationContext().getPackageName();
        final List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (final RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND)) {
                return true;
            }
        }
        return false;
    }

    public void onCreate() {
        super.onCreate();
        MyApp.mInstance = this;
        MyApp.crashHandler = ErrorHandler.getInstance();
        MyApp.crashHandler.setDebug(false);
        //分享 sdk
        ShareSDK.initSDK(getApplicationContext());
        //优酷 sdk
//        YoukuPlayerConfig.setLog(true);
//        YoukuPlayerConfig.setClientIdAndSecret("b2a61f04339f47b1","1b4dcae75f393a1b67596bd6ad9bae37");
//        YoukuPlayerConfig.onInitial(getApplicationContext(), "");
    }
}
