package com.example.authentication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;

@RequiresApi(api = Build.VERSION_CODES.P)
public class MainActivity extends AppCompatActivity {
    String[] permisson={Manifest.permission.USE_BIOMETRIC};
    int requestcode = 143;
    BiomatricCallBacks biomatricCallBacks;
    BiometricPrompt biometricPrompt;
    CancellationSignal cancellationSignal;
    CommonFunction commonFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commonFunctions =new CommonFunction(getApplicationContext());
        biomatricCallBacks = new BiomatricCallBacks(getApplicationContext());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.USE_BIOMETRIC)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(permisson,requestcode);
            }else {

                biometricPrompt = new BiometricPrompt.Builder(this)
                        .setTitle("context data")
                        .setSubtitle("Authentication required")
                        .setDescription("We will use your fingerprint only for your Verification")
                        .setNegativeButton("Cancel", getMainExecutor(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                commonFunctions.notifyuser("cancel by user");
                            }
                        }).build();

                biometricPrompt.authenticate(getcanclesingle(),getMainExecutor(),biomatricCallBacks);
            }
        }
    }
    public CancellationSignal getcanclesingle(){
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                commonFunctions.notifyuser("Cancel signal activitated");
            }
        });
        return cancellationSignal;
    }
}

