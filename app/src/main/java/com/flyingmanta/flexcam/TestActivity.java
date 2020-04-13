package com.flyingmanta.flexcam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.flyingmanta.encoder.R;

public class TestActivity extends AppCompatActivity {
    private int RC_PERMISSION = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if ((ContextCompat.checkSelfPermission(TestActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        //没有权限，申请权限
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                        //申请权限，其中RC_PERMISSION是权限申请码，用来标志权限申请的
                        ActivityCompat.requestPermissions(TestActivity.this, permissions, RC_PERMISSION);
                    }
                } else {
                    //拥有权限
                    startActivity(new Intent(TestActivity.this, MainActivity.class));
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_PERMISSION) {
            Toast.makeText(this, "权限申请成功", Toast.LENGTH_LONG).show();
            startActivity(new Intent(TestActivity.this, MainActivity.class));
        } else {
            Toast.makeText(this, "权限申请失败", Toast.LENGTH_LONG).show();
        }
    }
}
