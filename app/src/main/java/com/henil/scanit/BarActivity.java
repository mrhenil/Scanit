package com.henil.scanit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.henil.scanit.constant.Constants;
import com.henil.scanit.utils.SharedPreferencesUtils;
import com.henil.scanit.utils.ResultUtils;
import com.henil.scanit.views.ZBarScannerView;


public class BarActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler, Constants {

    private boolean isAutoFocus = true;
    private boolean isSquare = false;
    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesUtils.initSharedReferences(BarActivity.this);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isAutoFocus = bundle.getBoolean(IS_AUTO_FOCUS);
            isSquare = bundle.getBoolean(IS_SQUARE);
        }

        initView();
    }


    private void initView() {

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
    public void handleResult(ResultUtils rawResult) {
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