package com.example.zyfx_.mystudy.utils;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.zyfx_.mystudy.MyApplication;

/**
 * @Author zhangxin
 * @date 2017/7/3 15:33
 * @description 自定义toast
 **/
public class ToastUtil {

    private static Toast mToast = null;

    /**
     * 非延时toast
     *
     * @param
     * @param msg
     */
    public static void showTextToast(@NonNull String msg) {
        try {
            if ("".equals(msg.trim()) || msg.equalsIgnoreCase("null")) {
                return;
            }
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(MyApplication.getInstance(), msg + "", Toast.LENGTH_LONG);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showTextToast(@StringRes int stringId) {
        try {
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(MyApplication.getInstance(), MyApplication.getInstance().getResources().getString(stringId) + "", Toast.LENGTH_LONG);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
