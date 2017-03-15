package com.example.ym.flashlight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

import static com.example.ym.flashlight.MainActivity.CLOSE_FLASH;
import static com.example.ym.flashlight.MainActivity.isTorch;
import static com.example.ym.flashlight.MainActivity.mTorchButton;
import static com.example.ym.flashlight.MainActivity.notificationManager;


public class FlashClose extends BroadcastReceiver {
    private CameraManager mCamera ;
    private String cameraId;

    @Override
    public void onReceive(Context context, Intent intent) {

        mCamera = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
        try{
            cameraId = mCamera.getCameraIdList()[0];
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
        mTorchButton.setImageResource(R.drawable.light_off);
        notificationManager.cancel(1);
        isTorch = true;

        String action = intent.getAction();
        if (action.equals(CLOSE_FLASH)){
            try {
                mCamera.setTorchMode(cameraId,false);
            }catch (CameraAccessException e){
                e.printStackTrace();
            }
        }
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

    }
}
