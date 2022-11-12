package com.example.authentication;

import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.P)
public class BiomatricCallBacks extends BiometricPrompt.AuthenticationCallback{
    Context context;
    CommonFunction commonFunctions;
    public BiomatricCallBacks(Context context) {
        this.context = context;
        commonFunctions = new CommonFunction(context);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        commonFunctions.notifyuser("error"+errorCode);
    }

    @Override
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        commonFunctions.notifyuser("Successfully verified");
        Intent intent = new Intent(context.getApplicationContext(),SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        commonFunctions.notifyuser("Wrong Information");
    }

}
