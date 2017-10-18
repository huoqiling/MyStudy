package com.example.zyfx_.mystudy.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zyfx_.mystudy.R;
import com.example.zyfx_.mystudy.utils.ToastUtil;
import com.example.zyfx_.mystudy.view.entitys.OperationObject;
import com.example.zyfx_.mystudy.view.view.CustomTitleBar;
import com.example.zyfx_.mystudy.view.view.DrawBoard;
import com.gyf.barlibrary.ImmersionBar;
import com.zhouyou.http.https.HttpsUtils;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author zhangxin
 * @date 2017/10/17 15:19
 * @description 手写签名
 **/
public class SignatureActivity extends BaseActivity implements CustomTitleBar.OnCustomTitleBarListener{

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    @BindView(R.id.drawBoard)
    DrawBoard drawBoard;

    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    @BindView(R.id.btnCancel)
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_board);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        titleBar.setOnCustomTitleBarListener(this);
        ImmersionBar.with(this).statusBarColor(R.color.colorAccent).fitsSystemWindows(true).init();
    }


    @OnClick({R.id.btnConfirm, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnConfirm:
                break;
            case R.id.btnCancel:
                break;
        }
    }

    private void saveSignature(){
        try {
            drawBoard.buildDrawingCache();
            Bitmap bitmap = drawBoard.getDrawingCache();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, os);

            Thread thread = new Thread(new SignatureRunnable(os.toByteArray()));
            thread.start();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class SignatureRunnable implements Runnable {

        private byte[] byteArray;

        public SignatureRunnable(byte[] byteArray) {
            this.byteArray = byteArray;
            ToastUtil.showTextToast("bitmap length:" + byteArray.length);
            Log.i("signature","bitmap length:" + byteArray.length);
        }

        @Override
        public void run() {
            //upLoadData(byteArray);
        }
    }



    @Override
    public void onLeftImageClick() {
        finish();
    }

    @Override
    public void onLeftTextClick() {

    }

    @Override
    public void onRightTextClick() {

    }

    @Override
    public void onRightImageClick() {

    }

    @Override
    public void onTitleClick() {

    }
}
