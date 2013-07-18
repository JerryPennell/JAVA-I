/*
 * project		Java1Week2
 * 
 * package		com.jpennell.lib
 * 
 * author		Jerry Pennell
 * 
 * date			Jul 17, 2013
 */
package com.jpennell.lib;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class Forms.
 */
public class Forms {
	
	/**
	 * Single entry with button.
	 *
	 * @param context the context
	 * @param hint the hint
	 * @param buttonText the button text
	 * @return the linear layout
	 */
	public static LinearLayout singleEntryWithButton(Context context, String hint, String buttonText) {
		// Create linear layout and parameters
		LinearLayout lLayout = new LinearLayout(context);
		LayoutParams lParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lLayout.setLayoutParams(lParams);

		// Create edit text
		EditText editText = new EditText(context);
		
		// Set parameters for edit text to take up full width
		lParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		editText.setHint(hint);
		editText.setLayoutParams(lParams);
		editText.setId(1);

		// Create button
		Button getButton = new Button(context);
		getButton.setText(buttonText);
		getButton.setId(2);
		// Tag button with edit text object
		getButton.setTag(editText);

		// Add UI elements to linear layout
		lLayout.addView(editText);
		lLayout.addView(getButton);

		return lLayout;
	}
	
	/**
	 * Radio group options.
	 *
	 * @param context the context
	 * @param location the location
	 * @return the radio group
	 */
	public static  RadioGroup radioGroupOptions(Context context, String[] location) {
		RadioGroup rGroup = new RadioGroup(context);

		for (int i = 0; i < location.length; i++) {
			RadioButton rButton = new RadioButton(context);
			
			rButton.setText(location[i]);
            if (i==0){
				rButton.setChecked(true);
			}
			rButton.setId(i+1);
			rGroup.addView(rButton);
		}
		

		return rGroup;
	}
	
	/**
	 * Show results.
	 *
	 * @param context the context
	 * @return the text view
	 */
	public static TextView showResults(Context context) {
		TextView textView = new TextView(context);
		textView.setId(3);

		return textView;
	}
}
