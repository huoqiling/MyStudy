package com.example.zyfx_.mystudy.view.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.zyfx_.mystudy.R;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnDownLoad)
    Button btnDownLoad;

    @BindView(R.id.progress)
    NumberProgressBar numberProgress;


    private String apkUrl = "http://down.pre.im/01/ea/01eaba5a7ddef36191ef9bbaa9ba4fa6.apk?OSSAccessKeyId=QoA0RoJkVznFZAxs&Expires=1498208879&Signature=k6LpJiBCVb1zv%2BQZT2tbmiH42w0%3D";

    private static final String SAVE_PATH = Environment.getExternalStorageDirectory().getPath() + "/custom/myapp";
    private static final String SAVE_NAME = "goldenWorld_1.0.4.apk";

    private static final int NotificationID = 0x10000;
    private NotificationManager mNotificationManager = null;
    private Notification notification; //下载通知进度提示
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNotification();
    }


    //初始化通知
    private void initNotification() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.mipmap.ic_launcher) //设置通知的小图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)) //设置通知的大图标
                .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        notification = builder.build();//构建通知对象
    }

    @OnClick(R.id.btnDownLoad)
    public void onViewClicked() {
        EasyHttp.downLoad(apkUrl)
                .savePath(SAVE_PATH)
                .saveName(SAVE_NAME)
                .execute(new DownloadProgressCallBack<String>() {

                    @Override
                    public void onStart() {
                        showToast("下载开始");
                        btnDownLoad.setEnabled(false);
                        notification = builder.build();
                        mNotificationManager.notify(NotificationID, notification);
                    }

                    @Override
                    public void onError(ApiException e) {
                        showToast("下载失败");
                        btnDownLoad.setEnabled(true);
                        mNotificationManager.cancel(NotificationID);
                    }

                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead * 100 / contentLength);
                        numberProgress.setProgress(progress);
                        builder.setProgress(100, progress, false);
                        builder.setContentText("下载进度：" + progress + "%");
                        notification = builder.build();
                        mNotificationManager.notify(NotificationID, notification);
                    }

                    @Override
                    public void onComplete(String path) {
                        showToast("下载完成");
                        btnDownLoad.setEnabled(true);
                        Intent installIntent = new Intent(Intent.ACTION_VIEW);
                        Uri uri = Uri.fromFile(new File(SAVE_PATH + "/" + SAVE_NAME));
                        installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, installIntent, 0);
                        builder.setContentText("下载完成,请点击安装");
                        builder.setContentIntent(mPendingIntent);
                        startActivity(installIntent);// 下载完成之后自动弹出安装界面
                        mNotificationManager.cancel(NotificationID);
                    }
                });
    }

    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
