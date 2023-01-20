package com.henil.scanit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.zxing.Result;
import com.henil.scanit.constant.Constants;
import com.henil.scanit.utils.SharedPreferencesUtils;
import com.henil.scanit.views.ZXingScannerView;

public class QRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, Constants {

    private boolean isAutoFocus = true;
    private boolean isSquare = false;
    private ZXingScannerView mScannerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesUtils.initSharedReferences(QRActivity.this);
        mScannerView = new ZXingScannerView(this);
        setContentView(R.layout.activity_test_camera);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isAutoFocus = bundle.getBoolean(IS_AUTO_FOCUS);
            isSquare = bundle.getBoolean(IS_SQUARE);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_main_toolbar_bg));
        toolbar.setTitle(getString(R.string.app_name_label));
        setSupportActionBar(toolbar);
        initView();
    }


    private void initView() {
        mScannerView = (ZXingScannerView) findViewById(R.id.zxing_scanner_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        startScanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopScanner();
    }

    @Override
    public void handleResult(Result rawResult) {

        stopScanner();
    }

    public void startScanner() {
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        mScannerView.setAutoFocus(isAutoFocus);
        mScannerView.setSquareViewFinder(isSquare);
    }

    public void stopScanner() {
        mScannerView.stopCamera();
    }

}