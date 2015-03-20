package com.dhian.KTA;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class Nama
extends TextView {
    String name;
    TextView nama;

    public Nama(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.nama = (TextView)this.findViewById(getID("nama","id"));
        this.name = context.getSharedPreferences("EvoPrefsFile", 0).getString("namaKTA", "Unknown");
        this.nama.setText((CharSequence)this.name);
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

									 public void onReceive(Context context2, Intent intent) {
										 Nama.this.name = intent.getStringExtra("NAMA");
										 Nama.this.nama.setText((CharSequence)Nama.this.name);
										 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
										 editor.putString("namaKTA", Nama.this.name);
										 editor.commit();
									 }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_NAME_KTA"));
    }
	public int getID(String name, String Type) {
		return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
	}
}
