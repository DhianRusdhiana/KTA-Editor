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

public class Photo
extends ImageView {
    String photoUri;
    ImageView photoKTA;
    String uri;

    public Photo(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.photoKTA = (ImageView)this.findViewById(getID("nia_profile_kta","id"));
        this.photoUri = context.getSharedPreferences("EvoPrefsFile", 0).getString("photoKTA", "null");
        if (this.photoUri == "null") {
            this.photoKTA.setImageResource(getID("nia_default_kta","drawable"));
        } else {
            this.photoKTA.setImageURI(Uri.parse((String)this.photoUri));
        }
        BroadcastReceiver mReceiver = new BroadcastReceiver(){

            public void onReceive(Context context2, Intent intent) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile", 0);
                Photo.this.photoUri = sharedPreferences.getString("photoKTA", "null");
                if (Photo.this.photoUri == "null") {
                    Photo.this.photoKTA.setImageResource(getID("nia_default_kta","drawable"));
                    return;
                }
                Photo.this.photoKTA.setImageURI(Uri.parse((String)Photo.this.photoUri));
            }
        };
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

            public void onReceive(Context context2, Intent intent) {
                Photo.this.uri = intent.getStringExtra("KTAkey");
                Photo.this.photoKTA.setImageURI(Uri.parse((String)Photo.this.uri));
                SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
                editor.putString("photoKTA", Photo.this.uri);
                editor.commit();
            }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_PHOTO_KTA"));
        IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_SCANNER_FINISHED");
        intentFilter.addDataScheme("file");
        context.registerReceiver((BroadcastReceiver)mReceiver, intentFilter);
    }
	public int getID(String name, String Type) {
		return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
	}

}

