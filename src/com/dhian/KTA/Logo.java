package com.dhian.KTA;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class Logo
extends ImageView {
    String logoUri;
    ImageView logoKTA;
    String uri;

    public Logo(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.logoKTA = (ImageView)this.findViewById(getID("logo","id"));
        this.logoUri = context.getSharedPreferences("Vicha", 0).getString("logoKTA", "null");
        if (this.logoUri == "null") {
            this.logoKTA.setImageResource(getID("default_logo","drawable"));
        } else {
            this.logoKTA.setImageURI(Uri.parse((String)this.logoUri));
        }
        BroadcastReceiver mReceiver = new BroadcastReceiver(){

            public void onReceive(Context context2, Intent intent) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("Vicha", 0);
                Logo.this.logoUri = sharedPreferences.getString("logoKTA", "null");
                if (Logo.this.logoUri == "null") {
                    Logo.this.logoKTA.setImageResource(getID("default_logo","drawable"));
                    return;
                }
                Logo.this.logoKTA.setImageURI(Uri.parse((String)Logo.this.logoUri));
            }
        };
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

									 public void onReceive(Context context2, Intent intent) {
										 Logo.this.uri = intent.getStringExtra("LOGO");
										 Logo.this.logoKTA.setImageURI(Uri.parse((String)Logo.this.uri));
										 SharedPreferences.Editor editor = context.getSharedPreferences("Vicha", 0).edit();
										 editor.putString("logoKTA", Logo.this.uri);
										 editor.commit();
									 }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_LOGO_KTA"));
        IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_SCANNER_FINISHED");
        intentFilter.addDataScheme("file");
        context.registerReceiver((BroadcastReceiver)mReceiver, intentFilter);
    }
	public int getID(String name, String Type) {
		return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
	}

}

