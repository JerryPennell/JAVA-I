package com.jpennell.java1week2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.java1week2.R;
import com.jpennell.lib.Forms;
import com.jpennell.lib.JSON;



public class MainActivity extends Activity {
    //Creates Radio groups
	RadioGroup weatherLocations;
	TextView resultText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout lLayout = new LinearLayout(this);
		LayoutParams lParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lLayout.setLayoutParams(lParams);
		lLayout.setOrientation(LinearLayout.VERTICAL);

		// String values from resource
		String eHint = getResources().getString(R.string.edit_hint);
		String bText = getResources().getString(R.string.button_text);

		LinearLayout entryBox = Forms.singleEntryWithButton(this, eHint, bText);
		
		//EditText
		Button button1 = (Button) entryBox.findViewById(2);
		button1.setOnClickListener(new View.OnClickListener() {
			// On click listener
			@Override
			public void onClick(View v) {
				EditText userInput1 = (EditText) v.getTag();
				String txt = userInput1.getText().toString();
				//Logging info out
				Log.i("BUTTON CLICKED",txt ); 
				
				//Resulted output

				int selectedRadioId = weatherLocations.getCheckedRadioButtonId();
				RadioButton selectedRadio = (RadioButton) weatherLocations.findViewById(selectedRadioId);
				String selected = selectedRadio.getText().toString();
				resultText.setText(JSON.readJSON(selected)+"\r\nFor zip Code: "+txt);
			}
		});
		// Set radio group
		String[] locationNames = {"Charleston", "Greenville", "Chester","Clover"};
		weatherLocations = Forms.radioGroupOptions(this, locationNames);

		// Set result text view - Pull in JSON data
		resultText = Forms.showResults(this);
		resultText.setText("Make a selection for the current weather");

		// Add UI elements to linear layout
		lLayout.addView(entryBox);
		lLayout.addView(weatherLocations);
		lLayout.addView(resultText);

		setContentView(lLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}