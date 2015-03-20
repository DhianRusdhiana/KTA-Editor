package com.dhian.KTA;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class Usia
extends TextView {
    String umur;
    TextView usia;

    public Usia(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.usia = (TextView)this.findViewById(getID("usia","id"));
        this.umur = context.getSharedPreferences("EvoPrefsFile", 0).getString("usiaKTA", "Unknown");
        this.usia.setText((CharSequence)this.umur);
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

									 public void onReceive(Context context2, Intent intent) {
										 Usia.this.umur = intent.getStringExtra("USIA");
										 Usia.this.usia.setText((CharSequence)Usia.this.umur);
										 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
										 editor.putString("usiaKTA", Usia.this.umur);
										 editor.commit();
									 }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_USIA_KTA"));
    }
	public int getID(String umur, String Type) {
		return getContext().getResources().getIdentifier(umur, Type, getContext().getPackageName());
	}
}
