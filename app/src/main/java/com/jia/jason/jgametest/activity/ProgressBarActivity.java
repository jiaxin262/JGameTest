package com.jia.jason.jgametest.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/8/7
 */
public class ProgressBarActivity extends BaseActivity {

    ProgressBar progressBar;
    Button btnIncrease;
    Button btnDecrease;
    Button btnReset;
    TextView tvFirstPro;
    TextView tvSecPro;
    Button btnDialogPro;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.progress_bar_layout);

        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        setProgress(1000);

        init();

    }

    private void init() {
        progressBar = (ProgressBar) findViewById(R.id.h_progress_bar);
        btnIncrease = (Button) findViewById(R.id.btn_incress);
        btnDecrease = (Button) findViewById(R.id.btn_decrease);
        btnReset = (Button) findViewById(R.id.btn_reset);
        tvFirstPro = (TextView) findViewById(R.id.tv_first_pro);
        tvSecPro = (TextView) findViewById(R.id.tv_sec_pro);
        btnDialogPro = (Button) findViewById(R.id.btn_dialog_progress);

        setProgressPercentage();
        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnDialogPro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_incress:
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(12);
                break;
            case R.id.btn_decrease:
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-8);
                break;
            case R.id.btn_reset:
                progressBar.setProgress(1);
                progressBar.setSecondaryProgress(10);
                break;
            case R.id.btn_dialog_progress:
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMax(100);
                progressDialog.incrementProgressBy(30);
                progressDialog.incrementSecondaryProgressBy(50);
                progressDialog.setIndeterminate(false);
                progressDialog.setTitle("Jason");
                progressDialog.setMessage("jason...");
                progressDialog.setIcon(R.drawable.app_lunar_lander);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProgressBarActivity.this, "jasonjason", Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
        }
        setProgressPercentage();
    }

    private void setProgressPercentage() {
        int firstPercent = (int)(progressBar.getProgress()/(float)progressBar.getMax()*100);
        int secPercent = (int)(progressBar.getSecondaryProgress()/(float)progressBar.getMax()*100);
        tvFirstPro.setText(getString(R.string.progress_desc,
                new Object[]{getResources().getString(R.string.first), firstPercent}));
        tvSecPro.setText(getString(R.string.progress_desc,
                new Object[]{getResources().getString(R.string.second), secPercent}));
    }
}
