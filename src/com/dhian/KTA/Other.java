package com.dhian.KTA;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class Other
extends TextView {
    String lain, lain2;
    TextView other, other2;

    public Other(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
		if(getId() == getID("other1","id")){
        this.other = (TextView)this.findViewById(getID("other1","id"));
        this.lain = context.getSharedPreferences("EvoPrefsFile", 0).getString("other1KTA", "Unknown");
        this.other.setText((CharSequence)this.lain);
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

									 public void onReceive(Context context2, Intent intent) {
										 Other.this.lain = intent.getStringExtra("OTHER1");
										 Other.this.other.setText((CharSequence)Other.this.lain);
										 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
										 editor.putString("other1KTA", Other.this.lain);
										 editor.commit();
									 }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_OTHER1_KTA"));
    }
	if(getId() == getID("other2","id")){
        this.other2 = (TextView)this.findViewById(getID("other2","id"));
        this.lain2 = context.getSharedPreferences("EvoPrefsFile", 0).getString("other2KTA", "Unknown");
        this.other2.setText((CharSequence)this.lain2);
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

									 public void onReceive(Context context2, Intent intent) {
										 Other.this.lain2 = intent.getStringExtra("OTHER2");
										 Other.this.other2.setText((CharSequence)Other.this.lain2);
										 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
										 editor.putString("other2KTA", Other.this.lain2);
										 editor.commit();
									 }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_OTHER2_KTA"));
    }
		if(getId() == getID("other3","id")){
			this.other = (TextView)this.findViewById(getID("other3","id"));
			this.lain = context.getSharedPreferences("EvoPrefsFile", 0).getString("other3KTA", "Unknown");
			this.other.setText((CharSequence)this.lain);
			context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

										 public void onReceive(Context context2, Intent intent) {
											 Other.this.lain = intent.getStringExtra("OTHER3");
											 Other.this.other.setText((CharSequence)Other.this.lain);
											 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor.putString("other3KTA", Other.this.lain);
											 editor.commit();
										 }
									 }, new IntentFilter("com.dhian.KTA.CHANGE_OTHER3_KTA"));
		}
		if(getId() == getID("other4","id")){
			this.other = (TextView)this.findViewById(getID("other4","id"));
			this.lain = context.getSharedPreferences("EvoPrefsFile", 0).getString("other4KTA", "Unknown");
			this.other.setText((CharSequence)this.lain);
			context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

										 public void onReceive(Context context2, Intent intent) {
											 Other.this.lain = intent.getStringExtra("OTHER4");
											 Other.this.other.setText((CharSequence)Other.this.lain);
											 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor.putString("other4KTA", Other.this.lain);
											 editor.commit();
										 }
									 }, new IntentFilter("com.dhian.KTA.CHANGE_OTHER4_KTA"));
		}
		if(getId() == getID("other5","id")){
			this.other = (TextView)this.findViewById(getID("other5","id"));
			this.lain = context.getSharedPreferences("EvoPrefsFile", 0).getString("other5KTA", "Unknown");
			this.other.setText((CharSequence)this.lain);
			context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

										 public void onReceive(Context context2, Intent intent) {
											 Other.this.lain = intent.getStringExtra("OTHER5");
											 Other.this.other.setText((CharSequence)Other.this.lain);
											 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor.putString("other5KTA", Other.this.lain);
											 editor.commit();
										 }
									 }, new IntentFilter("com.dhian.KTA.CHANGE_OTHER5_KTA"));
		}
		if(getId() == getID("other6","id")){
			this.other = (TextView)this.findViewById(getID("other6","id"));
			this.lain = context.getSharedPreferences("EvoPrefsFile", 0).getString("other6KTA", "Unknown");
			this.other.setText((CharSequence)this.lain);
			context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

										 public void onReceive(Context context2, Intent intent) {
											 Other.this.lain = intent.getStringExtra("OTHER6");
											 Other.this.other.setText((CharSequence)Other.this.lain);
											 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor.putString("other6KTA", Other.this.lain);
											 editor.commit();
										 }
									 }, new IntentFilter("com.dhian.KTA.CHANGE_OTHER6_KTA"));
		}
		if(getId() == getID("other7","id")){
			this.other = (TextView)this.findViewById(getID("other7","id"));
			this.lain = context.getSharedPreferences("EvoPrefsFile", 0).getString("other7KTA", "Unknown");
			this.other.setText((CharSequence)this.lain);
			context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

										 public void onReceive(Context context2, Intent intent) {
											 Other.this.lain = intent.getStringExtra("OTHER7");
											 Other.this.other.setText((CharSequence)Other.this.lain);
											 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor.putString("other7KTA", Other.this.lain);
											 editor.commit();
										 }
									 }, new IntentFilter("com.dhian.KTA.CHANGE_OTHER7_KTA"));
		}
		if(getId() == getID("other8","id")){
			this.other = (TextView)this.findViewById(getID("other8","id"));
			this.lain = context.getSharedPreferences("EvoPrefsFile", 0).getString("other8KTA", "Unknown");
			this.other.setText((CharSequence)this.lain);
			context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

										 public void onReceive(Context context2, Intent intent) {
											 Other.this.lain = intent.getStringExtra("OTHER8");
											 Other.this.other.setText((CharSequence)Other.this.lain);
											 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor.putString("other8KTA", Other.this.lain);
											 editor.commit();
										 }
									 }, new IntentFilter("com.dhian.KTA.CHANGE_OTHER8_KTA"));
		}
	}
	public int getID(String name, String Type) {
		return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
	}
}
