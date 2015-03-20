package com.dhian.KTA;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.content.*;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.widget.*;
import android.text.Editable;

public class KTAEditor
extends Activity {

	Button done;
	Bitmap bitmap;
    String abUri;
    ImageView photoKTA;
    Uri uri;
	File dir;
	final int CROP_PHOTO = 2;
	public static final String picKTA = "KTAkey";
	private SharedPreferences prefs;
	
	EditText nama, device, asal, usia, quote;
	String namaKTA, deviceKTA, asalKTA, usiaKTA, quoteKTA;


	private void photoCrop() {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(this.uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 100);
            intent.putExtra("outputY", 100);
            intent.putExtra("return-data", true);
            this.startActivityForResult(intent, 2);
            return;
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText((Context)this, (CharSequence)"Sorry.. Your device not suport crop picture", (int)1).show();
            return;
        }
	}

    /*
     * Enabled aggressive block sorting
     */
    protected void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1 && intent != null) {
			this.uri = intent.getData();
			this.photoCrop();
			return;
		}
		else {
			if (n != 2 || intent == null)
				return;
			{
				Bitmap bitmap = (Bitmap)intent.getExtras().getParcelable("data");
				this.photoKTA.setImageBitmap(bitmap);
				this.setPhotoKTA();
				return;
			}									
		}
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(getID("dhian_kta_editor","layout"));
		this.photoKTA = (ImageView)this.findViewById(getID("photo","id"));
		this.nama = (EditText)this.findViewById(getID("nama","id"));
		this.device = (EditText)this.findViewById(getID("device","id"));
		this.asal = (EditText)this.findViewById(getID("asal","id"));
		this.usia = (EditText)this.findViewById(getID("usia","id"));
		this.quote = (EditText)this.findViewById(getID("quote","id"));
		this.done = (Button)this.findViewById(getID("save","id"));
	    SharedPreferences sharedPreferences2 = this.getSharedPreferences("EvoPrefsFile", 1);
		this.namaKTA = sharedPreferences2.getString("namaKTA", "");
		this.abUri = sharedPreferences2.getString("photoKTA", "null");
        if (this.abUri == "null") {
            this.photoKTA.setImageResource(getID("nia_default_kta","drawable"));
        } else {
            this.photoKTA.setImageURI(Uri.parse((String)this.abUri));
        }
        this.nama.setText((CharSequence)this.namaKTA);
		this.deviceKTA = sharedPreferences2.getString("deviceKTA","");
		this.device.setText((CharSequence)this.deviceKTA);
		this.asalKTA = sharedPreferences2.getString("asalKTA","");
		this.asal.setText((CharSequence)this.asalKTA);
		this.usiaKTA = sharedPreferences2.getString("usiaKTA","");
		this.usia.setText((CharSequence)this.usiaKTA);
		this.quoteKTA = sharedPreferences2.getString("quoteKTA","");
		this.quote.setText((CharSequence)this.quoteKTA);
		this.photoKTA.setOnClickListener((View.OnClickListener)new View.OnClickListener(){

											  public void onClick(View view) {
												  Intent intent = new Intent();
												  intent.setType("image/*");
												  intent.setAction("android.intent.action.GET_CONTENT");
												  KTAEditor.this.startActivityForResult(Intent.createChooser((Intent)intent, (CharSequence)"Select Picture"), 1);
											  }
										  });
		this.done.setOnClickListener((View.OnClickListener)new View.OnClickListener(){

										 public void onClick(View view) {
											 Editable editable = KTAEditor.this.nama.getText();
											 Intent intent = new Intent();
											 intent.setAction("com.dhian.KTA.CHANGE_NAME_KTA");
											 intent.putExtra("NAMA", editable.toString());
											 KTAEditor.this.sendBroadcast(intent);
											 SharedPreferences.Editor editor = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor.putString("namaKTA", editable.toString());
											 editor.commit();
											 
											 Editable editable2 = KTAEditor.this.asal.getText();
											 Intent intent2 = new Intent();
											 intent2.setAction("com.dhian.KTA.CHANGE_ASAL_KTA");
											 intent2.putExtra("ASAL", editable2.toString());
											 KTAEditor.this.sendBroadcast(intent2);
											 SharedPreferences.Editor editor2 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor2.putString("asalKTA", editable2.toString());
											 editor2.commit();
											 
											 Editable editable3 = KTAEditor.this.device.getText();
											 Intent intent3 = new Intent();
											 intent3.setAction("com.dhian.KTA.CHANGE_DEVICE_KTA");
											 intent3.putExtra("DEVICE", editable3.toString());
											 KTAEditor.this.sendBroadcast(intent3);
											 SharedPreferences.Editor editor3 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor3.putString("deviceKTA", editable3.toString());
											 editor3.commit();
											 
											 Editable editable4 = KTAEditor.this.usia.getText();
											 Intent intent4 = new Intent();
											 intent4.setAction("com.dhian.KTA.CHANGE_USIA_KTA");
											 intent4.putExtra("USIA", editable4.toString());
											 KTAEditor.this.sendBroadcast(intent4);
											 SharedPreferences.Editor editor4 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor4.putString("usiaKTA", editable4.toString());
											 editor4.commit();
											 
											 Editable editable5 = KTAEditor.this.quote.getText();
											 Intent intent5 = new Intent();
											 intent5.setAction("com.dhian.KTA.CHANGE_QUOTE_KTA");
											 intent5.putExtra("QUOTE", editable5.toString());
											 KTAEditor.this.sendBroadcast(intent5);
											 SharedPreferences.Editor editor5 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor5.putString("quoteKTA", editable5.toString());
											 editor5.commit();
											 
											 KTAEditor.this.finish();
										 }
									 });
		
	}

	private void setPhotoKTA() {
	    bitmap = ((BitmapDrawable)KTAEditor.this.photoKTA.getDrawable()).getBitmap();
		File dir = new File(KTAEditor.this.getApplicationContext().getExternalCacheDir(), "photoKTA.png");
		try {
	        FileOutputStream fileOutputStream = new FileOutputStream(dir);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
		}
		catch (IOException e) {
		}
		do {
			Uri uri = Uri.parse((String)("file://" + (Object)dir));
			SharedPreferences.Editor editor = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 1).edit();
			editor.putString("photoKTA", uri.toString());
			editor.commit();
			Intent intent6 = new Intent();
			intent6.setAction("com.dhian.KTA.CHANGE_PHOTO_KTA");
			intent6.putExtra("KTAkey", uri.toString());
			KTAEditor.this.sendBroadcast(intent6);
			return;
		} while (true);
	}
	public void backto(View view) {
        this.finish();
	}	
	
	public int getID(String name, String Type) {
		return getBaseContext().getResources().getIdentifier(name, Type, getBaseContext().getPackageName());
	}




}

