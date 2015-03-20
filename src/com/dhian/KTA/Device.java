package com.dhian.KTA;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class Device
extends TextView {
    String dvc;
    TextView device;

    public Device(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.device = (TextView)this.findViewById(getID("device","id"));
        this.dvc = context.getSharedPreferences("EvoPrefsFile", 0).getString("deviceKTA", "Unknown");
        this.device.setText((CharSequence)this.dvc);
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

									 public void onReceive(Context context2, Intent intent) {
										 Device.this.dvc = intent.getStringExtra("DEVICE");
										 Device.this.device.setText((CharSequence)Device.this.dvc);
										 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
										 editor.putString("deviceKTA", Device.this.dvc);
										 editor.commit();
									 }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_DEVICE_KTA"));
    }
	public int getID(String name, String Type) {
		return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
	}
}
