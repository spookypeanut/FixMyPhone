package uk.co.spookypeanut.fixmyphone;

import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class SleepActivity extends DeviceAdminReceiver {
    static final int ADMIN_ENABLED = 27;
    
    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "Sample Device Admin: enabled");
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, "Sample Device Admin: disabled");
    }

    public static class TurnOff extends Activity {
        DevicePolicyManager mDPM;
        ComponentName mDeviceAdminSample;
        
        @Override
        protected void onCreate(Bundle icicle) {
            Log.e("Sleep", "onCreate");
            super.onCreate(icicle);
            mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
            mDeviceAdminSample = new ComponentName(TurnOff.this, SleepActivity.class);
        }
        
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
                case ADMIN_ENABLED:
                    if (resultCode == Activity.RESULT_OK) {
                        Log.i("DeviceAdminSample", "Admin enabled!");
                    } else {
                        Log.i("DeviceAdminSample", "Admin enable FAILED!");
                    }
                    return;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
        
        @Override
        protected void onResume() {
            Log.e("Sleep", "onResume");
            super.onResume();
            boolean active = mDPM.isAdminActive(mDeviceAdminSample);
            if (!active) {
                // Launch the activity to have the user enable our admin.
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                        mDeviceAdminSample);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                        "Additional text explaining why this needs to be added.");
                startActivityForResult(intent, ADMIN_ENABLED);
            }
            Log.e("Sleep", "locking now");
            mDPM.lockNow();
            this.finish();
        }
    }
}