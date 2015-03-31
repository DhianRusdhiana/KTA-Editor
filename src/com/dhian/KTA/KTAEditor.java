package com.dhian.KTA;

import android.app.Activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.dhian.KTA.GraphicsUtil;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

@SuppressLint(value={"CommitPrefEdits"})
public class KTAEditor
extends Activity {

	Button done, tabPhoto, tabLogo;
	Bitmap bitmap, bitmap2, bitmap3;
	Bundle bnd;
    String abUri ,cUri, rUri, logoUri;
    ImageView photoKTA, photoCircle, photoRounded, logo;
    Uri uri, crUri;
	File dir, dir2;
	final int CROP_PHOTO = 2;
	public static final String picKTA = "photoKTA";
	public static final String Photo = "photoKTAc";
    Animation animation;
	private Animation inAnim, outAnim;
	SharedPreferences.Editor editor;
	
    EditText nama, device, asal, usia, other1, other2, other3, other4, other5, other6, other7, other8, quote;
	String namaKTA, deviceKTA, asalKTA, usiaKTA, quoteKTA, other1KTA, other2KTA, other3KTA, other4KTA, other5KTA, other6KTA, other7KTA, other8KTA;
	
	private ViewFlipper tabFlipper;	
	
	

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
	private void photoCropC() {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(this.uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 100);
            intent.putExtra("outputY", 100);
            intent.putExtra("return-data", true);
            this.startActivityForResult(intent, 3);
            return;
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText((Context)this, (CharSequence)"Sorry.. Your device not suport crop picture", (int)1).show();
            return;
        }
	}
	private void photoCropR() {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(this.uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 100);
            intent.putExtra("outputY", 100);
            intent.putExtra("return-data", true);
            this.startActivityForResult(intent, 6);
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
		if (n == 4 && intent != null){
			this.uri = intent.getData();
			this.photoCropC();
			return;
		}
		if (n == 5 && intent != null){
			this.uri = intent.getData();
			this.photoCropR();
			return;
		}
		if (n == 7 && intent != null) {   
			this.uri = Uri.parse((String)intent.getDataString());
			this.logo.setImageURI(this.uri);
			SharedPreferences.Editor editor = this.getSharedPreferences("EvoPrefsFile", 0).edit();
			editor.putString("logoKTA", this.uri.toString());
			editor.commit();
			Intent intent2 = new Intent();
			intent2.setAction("com.dhian.KTA.CHANGE_LOGO_KTA");
			intent2.putExtra("LOGO", this.uri.toString());
			this.sendBroadcast(intent2);
			return;
			}
	    if (n == 2) {
            if (intent == null) return;
            {
				Bitmap bitmap = (Bitmap)intent.getExtras().getParcelable("data");
                this.bnd = new Bundle();
                this.bnd.putParcelable("BITMAP_1", (Parcelable)bitmap);
                this.photoKTA.setImageBitmap(bitmap);
				
                setPhotoKTA();
				return;
			}								
		}
            if (n == 3){
				if (intent == null) return;
            {
				Bitmap bitmap = (Bitmap)intent.getExtras().getParcelable("data");
				this.bnd = new Bundle();
				this.bnd.putParcelable("BITMAP_2", (Parcelable)bitmap);
				GraphicsUtil graphicsUtil = new GraphicsUtil();
                this.photoCircle.setImageBitmap(graphicsUtil.getCircleBitmap(bitmap, 16));
				
				setPhotoCircle();
				return;
	
		}
		}
			if (n != 6 || intent == null) return;
			{
				Bitmap bitmap = (Bitmap)intent.getExtras().getParcelable("data");
				this.bnd = new Bundle();
				this.bnd.putParcelable("BITMAP_3", (Parcelable)bitmap);
				RoundedUtils roundedUtil = new RoundedUtils();
				this.photoRounded.setImageBitmap(roundedUtil.getRoundedCornerBitmap(bitmap, 16));
				setPhotoRounded();
				return;
			}
			}
		
			
			
		
		
    

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(getID("dhian_kta_editor","layout"));
		this.photoKTA = (ImageView)this.findViewById(getID("photo","id"));
		this.photoCircle = (ImageView)this.findViewById(getID("photoCircle","id"));
		this.photoRounded = (ImageView)this.findViewById(getID("photoRounded","id"));
		this.logo = (ImageView)this.findViewById(getID("logo","id"));
		this.nama = (EditText)this.findViewById(getID("nama","id"));
		this.device = (EditText)this.findViewById(getID("device","id"));
		this.asal = (EditText)this.findViewById(getID("asal","id"));
		this.usia = (EditText)this.findViewById(getID("usia","id"));
		this.quote = (EditText)this.findViewById(getID("quote","id"));
		this.other1 = (EditText)this.findViewById(getID("other1","id"));
		this.other2 = (EditText)this.findViewById(getID("other2","id"));
		this.other3 = (EditText)this.findViewById(getID("other3","id"));
		this.other4 = (EditText)this.findViewById(getID("other4","id"));
		this.other5 = (EditText)this.findViewById(getID("other5","id"));
		this.other6 = (EditText)this.findViewById(getID("other6","id"));
		this.other7 = (EditText)this.findViewById(getID("other7","id"));
		this.other8 = (EditText)this.findViewById(getID("other8","id"));
		this.done = (Button)this.findViewById(getID("save","id"));
		this.tabPhoto = (Button)this.findViewById(getID("tab_photo","id"));
		this.tabLogo = (Button)this.findViewById(getID("tab_logo","id"));
		this.tabFlipper = (ViewFlipper)this.findViewById(getID("tab","id"));
	    SharedPreferences sharedPreferences2 = this.getSharedPreferences("EvoPrefsFile", 0);
		this.namaKTA = sharedPreferences2.getString("namaKTA", "");
		this.abUri = sharedPreferences2.getString("photoKTA", "null");
        if (this.abUri == "null") {
            this.photoKTA.setImageResource(getID("nia_default_kta","drawable"));
        } else {
            this.photoKTA.setImageURI(Uri.parse((String)this.abUri));
        }
		SharedPreferences sharedPreferences = this.getSharedPreferences("EvoPrefsFile",0);
		this.cUri = sharedPreferences.getString("photoKTAc","null");
		if (this.cUri == "null") {
            this.photoCircle.setImageResource(getID("default_circle","drawable"));
        } else {
            this.photoCircle.setImageURI(Uri.parse((String)this.cUri));
        }
		SharedPreferences sharedPreferences3 = this.getSharedPreferences("EvoPrefsFile",0);
		this.rUri = sharedPreferences3.getString("photoKTAr","null");
		if (this.rUri == "null"){
			this.photoRounded.setImageResource(getID("default_rounded","drawable"));
		} else {
			this.photoRounded.setImageURI(Uri.parse((String)this.rUri));
		}
		SharedPreferences sharedPreferences4 = this.getSharedPreferences("EvoPrefsFile",0);
		this.logoUri = sharedPreferences4.getString("logoKTA", "null");
		if (this.logoUri == "null"){
			this.logo.setImageResource(getID("default_logo","drawable"));
		} else {
			this.logo.setImageURI(Uri.parse((String)this.logoUri));
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
		this.other1KTA = sharedPreferences2.getString("other1KTA","");
		this.other1.setText((CharSequence)this.other1KTA);
		this.other2KTA = sharedPreferences2.getString("other2KTA","");
		this.other2.setText((CharSequence)this.other2KTA);
		this.other3KTA = sharedPreferences2.getString("other3KTA","");
		this.other3.setText((CharSequence)this.other3KTA);
		this.other4KTA = sharedPreferences2.getString("other4KTA","");
		this.other4.setText((CharSequence)this.other4KTA);
		this.other5KTA = sharedPreferences2.getString("other5KTA","");
		this.other5.setText((CharSequence)this.other5KTA);
		this.other6KTA = sharedPreferences2.getString("other6KTA","");
		this.other6.setText((CharSequence)this.other6KTA);
		this.other7KTA = sharedPreferences2.getString("other7KTA","");
		this.other7.setText((CharSequence)this.other7KTA);
		this.other8KTA = sharedPreferences2.getString("other8KTA","");
		this.other8.setText((CharSequence)this.other8KTA);
		this.photoKTA.setOnClickListener((View.OnClickListener)new View.OnClickListener(){

											  public void onClick(View view) {
												  Intent intent = new Intent();
												  intent.setType("image/*");
												  intent.setAction("android.intent.action.GET_CONTENT");
												  KTAEditor.this.startActivityForResult(Intent.createChooser((Intent)intent, (CharSequence)"Select Picture"), 1);
											  }
										  });
	    this.photoKTA.setOnLongClickListener((View.OnLongClickListener)new View.OnLongClickListener(){
			public boolean onLongClick(View view){
				setPhotoKTA();
				Toast.makeText(getApplicationContext(),
				"Foto SQUARE Telah Di Set Sebagai Foto KTA", 2000).show();
				return (true);
				
			}
		});
		this.photoCircle.setOnClickListener((View.OnClickListener)new View.OnClickListener(){

											 public void onClick(View view) {
												 Intent intent = new Intent();
												 intent.setType("image/*");
												 intent.setAction("android.intent.action.GET_CONTENT");
												 KTAEditor.this.startActivityForResult(Intent.createChooser((Intent)intent, (CharSequence)"Select Picture"), 4);
											 }
										 });
		this.photoCircle.setOnLongClickListener((View.OnLongClickListener)new View.OnLongClickListener(){
												 public boolean onLongClick(View view){
													 setPhotoCircle();
													 Toast.makeText(getApplicationContext(),
																	"Foto CIRCLE Telah Di Set Sebagai Foto KTA", 2000).show();
													 return (true);

												 }
											 });
		this.photoRounded.setOnClickListener((View.OnClickListener)new View.OnClickListener(){

												public void onClick(View view) {
													Intent intent = new Intent();
													intent.setType("image/*");
													intent.setAction("android.intent.action.GET_CONTENT");
													KTAEditor.this.startActivityForResult(Intent.createChooser((Intent)intent, (CharSequence)"Select Picture"), 5);
												}
											});
		this.photoRounded.setOnLongClickListener((View.OnLongClickListener)new View.OnLongClickListener(){
			public boolean onLongClick(View view){
				setPhotoRounded();
				Toast.makeText(getApplicationContext(),
				"Foto ROUNDED Telah Di Set Sebagai Foto KTA", 2000).show();
				return (true);
				
			}
		});
		this.logo.setOnClickListener((View.OnClickListener)new View.OnClickListener(){

												public void onClick(View view) {
													Intent intent = new Intent();
													intent.setType("image/*");
													intent.setAction("android.intent.action.GET_CONTENT");
													KTAEditor.this.startActivityForResult(Intent.createChooser((Intent)intent, (CharSequence)"Select Picture"), 7);
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
											 
											 Editable editable6 = KTAEditor.this.other1.getText();
											 Intent intent6 = new Intent();
											 intent6.setAction("com.dhian.KTA.CHANGE_OTHER1_KTA");
											 intent6.putExtra("OTHER1", editable6.toString());
											 KTAEditor.this.sendBroadcast(intent6);
											 SharedPreferences.Editor editor6 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor6.putString("other1KTA", editable6.toString());
											 editor6.commit();

											 Editable editable7 = KTAEditor.this.other2.getText();
											 Intent intent7 = new Intent();
											 intent7.setAction("com.dhian.KTA.CHANGE_OTHER2_KTA");
											 intent7.putExtra("OTHER2", editable7.toString());
											 KTAEditor.this.sendBroadcast(intent7);
											 SharedPreferences.Editor editor7 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor7.putString("other2KTA", editable7.toString());
											 editor7.commit();

											 Editable editable8 = KTAEditor.this.other3.getText();
											 Intent intent8 = new Intent();
											 intent8.setAction("com.dhian.KTA.CHANGE_OTHER3_KTA");
											 intent8.putExtra("OTHER3", editable8.toString());
											 KTAEditor.this.sendBroadcast(intent8);
											 SharedPreferences.Editor editor8 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor8.putString("other3KTA", editable8.toString());
											 editor8.commit();

											 Editable editable9 = KTAEditor.this.other4.getText();
											 Intent intent9 = new Intent();
											 intent9.setAction("com.dhian.KTA.CHANGE_OTHER4_KTA");
											 intent9.putExtra("OTHER4", editable9.toString());
											 KTAEditor.this.sendBroadcast(intent9);
											 SharedPreferences.Editor editor9 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor9.putString("other4KTA", editable9.toString());
											 editor9.commit();

											 Editable editable10 = KTAEditor.this.other5.getText();
											 Intent intent10 = new Intent();
											 intent10.setAction("com.dhian.KTA.CHANGE_OTHER5_KTA");
											 intent10.putExtra("OTHER5", editable10.toString());
											 KTAEditor.this.sendBroadcast(intent10);
											 SharedPreferences.Editor editor10 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor10.putString("other5KTA", editable10.toString());
											 editor10.commit();

											 Editable editable11 = KTAEditor.this.other6.getText();
											 Intent intent11 = new Intent();
											 intent11.setAction("com.dhian.KTA.CHANGE_OTHER6_KTA");
											 intent11.putExtra("OTHER6", editable11.toString());
											 KTAEditor.this.sendBroadcast(intent11);
											 SharedPreferences.Editor editor11 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor11.putString("other6KTA", editable11.toString());
											 editor11.commit();

											 Editable editable12 = KTAEditor.this.other7.getText();
											 Intent intent12 = new Intent();
											 intent12.setAction("com.dhian.KTA.CHANGE_OTHER7_KTA");
											 intent12.putExtra("OTHER7", editable12.toString());
											 KTAEditor.this.sendBroadcast(intent12);
											 SharedPreferences.Editor editor12 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor12.putString("other7KTA", editable12.toString());
											 editor12.commit();

											 Editable editable13 = KTAEditor.this.other8.getText();
											 Intent intent13 = new Intent();
											 intent13.setAction("com.dhian.KTA.CHANGE_OTHER8_KTA");
											 intent13.putExtra("OTHER8", editable13.toString());
											 KTAEditor.this.sendBroadcast(intent13);
											 SharedPreferences.Editor editor13 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
											 editor13.putString("other8KTA", editable13.toString());
											 editor13.commit();
											 
											 KTAEditor.this.finish();
										 }
									 });
		
		this.tabPhoto.setOnClickListener((View.OnClickListener)new View.OnClickListener(){
			public void onClick (View view){
			    
				tabPhoto.setSelected(true);
				tabLogo.setSelected(false);
				tabFlipper.setDisplayedChild(0);
				tabFlipper.setInAnimation(inAnim);
				tabFlipper.setOutAnimation(outAnim);
			}
		});
		this.tabLogo.setOnClickListener((View.OnClickListener)new View.OnClickListener(){
			public void onClick (View view){
			    
				tabPhoto.setSelected(false);
				tabLogo.setSelected(true);
				tabFlipper.setDisplayedChild(1);
				tabFlipper.setInAnimation(inAnim);
				tabFlipper.setOutAnimation(outAnim);
			}
		});
		inAnim = AnimationUtils.loadAnimation(this, getID("from_middle","anim"));
		outAnim = AnimationUtils.loadAnimation(this, getID("to_middle","anim"));
		
		
		List<String> list = new ArrayList<String>();
        list.add("Square");
        list.add("Circle");
        list.add("Rounded");
        
		Spinner sp = (Spinner)findViewById(getID("spinner1","id"));
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        sp.setAdapter(dataAdapter);
		SharedPreferences shp = this.getSharedPreferences("EvoPrefsFile", 0);
		
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
										   int arg2, long arg3) {
					final ViewFlipper vf = (ViewFlipper)findViewById(getID("viewFlipper","id"));
					vf.setInAnimation(inAnim);
					vf.setOutAnimation(outAnim);
					String s=((TextView)arg1).getText().toString();
					if(s.equals("Square"))
						vf.setDisplayedChild(0);
						
					if(s.equals("Circle"))
						vf.setDisplayedChild(1);
					
					if(s.equals("Rounded"))
						vf.setDisplayedChild(2);
						
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});
		
	}



		
	

	private void setPhotoKTA() {
	    bitmap = ((BitmapDrawable)KTAEditor.this.photoKTA.getDrawable()).getBitmap();
		File dir = new File(KTAEditor.this.getApplicationContext().getCacheDir(), "photo.png");
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
			SharedPreferences.Editor editor = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
			editor.putString("photoKTA", uri.toString());
			editor.commit();
			Intent intent = new Intent();
			intent.setAction("com.dhian.KTA.CHANGE_PHOTO_KTA");
			intent.putExtra("KTAkey", uri.toString());
			KTAEditor.this.sendBroadcast(intent);
			Toast.makeText(getApplicationContext(),
			"Foto SQUARE Telah Di Set Sebagai Foto KTA", 2000).show();
			
			return;
		
		} while (true);
	}
	private void setPhotoCircle() {
	    bitmap2 = ((BitmapDrawable)KTAEditor.this.photoCircle.getDrawable()).getBitmap();
		File dir2 = new File(KTAEditor.this.getApplicationContext().getCacheDir(), "photoc.png");
		try {
	        FileOutputStream fileOutputStream2 = new FileOutputStream(dir2);
			bitmap2.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)fileOutputStream2);
			fileOutputStream2.flush();
			fileOutputStream2.close();
		}
		catch (IOException a) {
		}
		do{
			Uri uri2 = Uri.parse((String)("file://" + (Object)dir2));
			SharedPreferences.Editor editor2 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
			editor2.putString("photoKTAc", uri2.toString());
			editor2.commit();
			Intent intent6 = new Intent();
			intent6.setAction("com.dhian.KTA.CHANGE_PHOTO_KTA");
			intent6.putExtra("KTAkey", uri2.toString());
			KTAEditor.this.sendBroadcast(intent6);
			Toast.makeText(getApplicationContext(),
						   "Foto CIRCLE Telah Di Set Sebagai Foto KTA", 2000).show();
			
			return;
		
		}while (true);
	}
	private void setPhotoRounded() {
	    bitmap2 = ((BitmapDrawable)KTAEditor.this.photoRounded.getDrawable()).getBitmap();
		File dir2 = new File(KTAEditor.this.getApplicationContext().getCacheDir(), "photor.png");
		try {
	        FileOutputStream fileOutputStream2 = new FileOutputStream(dir2);
			bitmap2.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)fileOutputStream2);
			fileOutputStream2.flush();
			fileOutputStream2.close();
		}
		catch (IOException a) {
		}
		do{
			Uri uri2 = Uri.parse((String)("file://" + (Object)dir2));
			SharedPreferences.Editor editor2 = KTAEditor.this.getSharedPreferences("EvoPrefsFile", 0).edit();
			editor2.putString("photoKTAr", uri2.toString());
			editor2.commit();
			Intent intent8 = new Intent();
			intent8.setAction("com.dhian.KTA.CHANGE_PHOTO_KTA");
			intent8.putExtra("KTAkey", uri2.toString());
			KTAEditor.this.sendBroadcast(intent8);
			Toast.makeText(getApplicationContext(),
						   "Foto ROUNDED Telah Di Set Sebagai Foto KTA", 2000).show();
			
			return;

		}while (true);
	}

	public void backto(View view) {
        this.finish();
	}	
	
	public int getID(String name, String Type) {
		return getBaseContext().getResources().getIdentifier(name, Type, getBaseContext().getPackageName());
	}

}

