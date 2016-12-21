package com.jsqix.dongqing.gank.theme;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.jsqix.dongqing.gank.utils.ACache;
import com.jsqix.dongqing.gank.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ThemeUtils {
    /**
     * 获取当主题颜色
     * @param context
     * @param attrRes
     * @return
     */
    public static int getThemeColor(Context context, int attrRes) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{attrRes});
        int color = typedArray.getColor(0, 0xffffff);
        typedArray.recycle();
        return color;
    }

    /**
     * 从cache中读取当前主题
     * @param context
     * @return
     */
    public static Theme getCurrentTheme(Context context) {
        String themeName = ACache.get(context).getAsString("app_theme");
        themeName = StringUtils.isEmpty(themeName) ? Theme.Default.name() : themeName;
        return Theme.valueOf(themeName);
    }

    /**
     * 设置当前主题到cache
     * @param context
     * @param currentTheme
     */
    public static void setCurrentTheme(Context context, Theme currentTheme) {
        ACache.get(context).put("app_theme", currentTheme.name());
    }
    /**
     * 切换应用主题
     *
     * @param rootView
     */
    public static void changeTheme(View rootView, Resources.Theme theme) {
        if (rootView instanceof ColorUiInterface) {
            ((ColorUiInterface) rootView).setTheme(theme);
            if (rootView instanceof ViewGroup) {
                int count = ((ViewGroup) rootView).getChildCount();
                for (int i = 0; i < count; i++) {
                    changeTheme(((ViewGroup) rootView).getChildAt(i), theme);
                }
            }
            if (rootView instanceof AbsListView) {
                try {
                    Field localField = AbsListView.class.getDeclaredField("mRecycler");
                    localField.setAccessible(true);
                    Method localMethod = Class.forName("android.widget.AbsListView$RecycleBin").getDeclaredMethod("clear");
                    localMethod.setAccessible(true);
                    localMethod.invoke(localField.get(rootView));
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                } catch (InvocationTargetException e5) {
                    e5.printStackTrace();
                }
            }
        } else {
            if (rootView instanceof ViewGroup) {
                int count = ((ViewGroup) rootView).getChildCount();
                for (int i = 0; i < count; i++) {
                    changeTheme(((ViewGroup) rootView).getChildAt(i), theme);
                }
            }
            if (rootView instanceof AbsListView) {
                try {
                    Field localField = AbsListView.class.getDeclaredField("mRecycler");
                    localField.setAccessible(true);
                    Method localMethod = Class.forName("android.widget.AbsListView$RecycleBin").getDeclaredMethod("clear");
                    localMethod.setAccessible(true);
                    localMethod.invoke(localField.get(rootView));
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                } catch (InvocationTargetException e5) {
                    e5.printStackTrace();
                }
            }
        }
    }

}