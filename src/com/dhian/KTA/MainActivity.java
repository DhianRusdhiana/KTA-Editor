package com.dhian.KTA;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity
{
	Button bt;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		addListenerButton();
    }
	
	public void addListenerButton() {
		final Context context = this;
		bt = (Button) findViewById(R.id.go);

		bt.setOnClickListener(new OnClickListener () {

				@Override
				public void onClick(View arg0) {

					Intent intent = new Intent(context, KTAEditor.class);
					startActivity(intent);
				}
			});
	}
}
