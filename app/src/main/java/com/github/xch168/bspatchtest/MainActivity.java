package com.github.xch168.bspatchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final File destApk = new File(Environment.getExternalStorageDirectory(), "dest.apk");
    private final File patch = new File(Environment.getExternalStorageDirectory(), "1.0.0-to-1.0.1.patch");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView versionText = findViewById(R.id.tv_version);
        versionText.setText("App版本：v" + getVersionName());

        getApkPath();

        Log.i("asdf", "patch:" + patch.getAbsolutePath());
    }

    public void update(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BsPatchUtil.patch(getApkPath(), destApk.getAbsolutePath(), patch.getAbsolutePath());

                if (destApk.exists()) {
                    install(MainActivity.this, destApk);
                }
            }
        }).start();
    }

    private void install(Context context, File apk) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }


    private String getApkPath() {
        ApplicationInfo applicationInfo = getApplicationInfo();
        Log.i("asdf", "apk:" + applicationInfo.sourceDir);
        return applicationInfo.sourceDir;
    }

    private String getVersionName() {
        String versionName = "";
        try {
            PackageInfo packageInfo = getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
