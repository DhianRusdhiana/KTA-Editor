package com.dhian.KTA;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class Quote
extends TextView {
    String kata;
    TextView quote;

    public Quote(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.quote = (TextView)this.findViewById(getID("quote","id"));
        this.kata = context.getSharedPreferences("EvoPrefsFile", 0).getString("quoteKTA", "No Quote Today");
        this.quote.setText((CharSequence)this.kata);
        context.registerReceiver((BroadcastReceiver)new BroadcastReceiver(){

									 public void onReceive(Context context2, Intent intent) {
										 Quote.this.kata = intent.getStringExtra("QUOTE");
										 Quote.this.quote.setText((CharSequence)Quote.this.kata);
										 SharedPreferences.Editor editor = context.getSharedPreferences("EvoPrefsFile", 0).edit();
										 editor.putString("quoteKTA", Quote.this.kata);
										 editor.commit();
									 }
								 }, new IntentFilter("com.dhian.KTA.CHANGE_QUOTE_KTA"));
    }
	public int getID(String kata, String Type) {
		return getContext().getResources().getIdentifier(kata, Type, getContext().getPackageName());
	}
}
