package com.dhian.KTA;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class Asal
extends TextView {
    String usul;
    TextView asal;

    public Asal(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.asal = (TextView)this.findViewById(getID("asal","id"));
        this.usul = context.getSharedPreferences("EvoPrefsFile", 0).getString("asalKTA", "Unknown");
        this.asal.setText((CharSequence)this.usul);
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

									 public void onReceive(Context context2, Intent intent) {
										 Asal.this.usul = intent.getStringExtra("ASAL");
										 Asal.this.asal.setText((CharSequence)Asal.this.usul);
										 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
										 editor.putString("asalKTA", Asal.this.usul);
										 editor.commit();
									 }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_ASAL_KTA"));
    }
	public int getID(String usul, String Type) {
		return getContext().getResources().getIdentifier(usul, Type, getContext().getPackageName());
	}
}
