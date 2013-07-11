/*
 * project		AndroidUIDemo
 * 
 * package		com.JerryPennell.androiduidemo
 * 
 * author		Jerry Pennell
 * 
 * date			Jul 10, 2013
 * 
 * This is a simple program that utilizes the view layouts and takes a text input
 * while giving feedback of a random message to win a quarter, dime, nickel or penny
 */
package com.JerryPennell.androiduidemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class AndroidUIDemo.
 */
public class AndroidUIDemo extends Activity {
	
	//Examples of Java variables
	/** The ll. */
	LinearLayout ll;
	
	/** The lp. */
	LinearLayout.LayoutParams lp;
	
	/** The et. */
	EditText et;
	
	/** The tv footer. */
	TextView tvFooter;
	
	//String Variables
	/** The hint. */
	String hint;
	
	/** The txt value. */
	String txtValue ="Nothing";

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	//Creates the layout
    	createLinearLayout();
    	
        super.onCreate(savedInstanceState);
    
    }
    
    
    /**
     * Creates the linear layout.
     */
    private void createLinearLayout (){
    	
    	//Integer Variable
    	int checkIntVal = 4;
    	
    	//Boolean Variable
        boolean isShortText = false;

    	//Passing in variables into function for creating custom text hint based on test integer value
        createCustomTextHint(checkIntVal, isShortText);
   
        
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        
        
        //Header for cost of text
        
        TextView tvHeader = new TextView(this);
        tvHeader.setText("-- Possible Text values --");
        ll.addView(tvHeader);
        
        TextView tv = new TextView(this);
        tv.setText(getString(R.string.quarter)+","+getString(R.string.dime)+","+getString(R.string.nickel)+","+getString(R.string.penny));
        
        //Added Child to View which is text view
        ll.addView(tv);
       
        et = new EditText(this);
        et.setHint(hint);

        //Adds button to the form
        
        Button b = new Button(this);
        b.setText("Check text value");

        
        //Adding to the form view
        
        LinearLayout form = new LinearLayout(this);
        form.setOrientation(LinearLayout.HORIZONTAL);
        form.setLayoutParams(lp);
        form.addView(et);
        form.addView(b);
        
        
        //Footer for randomization of text
        
        tvFooter = new TextView(this);
        tvFooter.setText("Text is currently worth nothing!");
        ll.addView(tvFooter);
        
       
    	
    	//Click Event Handler
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// OnClick Method randomizes a string based on entered text
				testTextNetWorth();
				tvFooter.setText(txtValue);
		    }
		});
		
		 ll.addView(form);
		
		 setContentView(ll);
    }

    
    /**
     * Random fun generator to see how much your text is worth
     *
     * @return the string
     */
    private String testTextNetWorth (){
    	int txtLen = et.getText().length();
    	for(int i = 0; i < txtLen; i ++){
		    //generate random number here, print here
		    int random = (int)(Math.random() * (40) + (-20) );
		    
		    switch (random) {
		    case 25: 
		      txtValue = "You won a "+getString(R.string.quarter);
		      break;
		    case 10: 
		    	txtValue = "You won a "+getString(R.string.dime);
		      break;
		    case 5: 
		    	txtValue = "You won a "+getString(R.string.nickel);
		      break;
		    case 1: 
		    	txtValue = "You won a "+getString(R.string.penny);
		      break;
		    default: 
		      if(txtLen > 25){
		    	  txtValue =txtLen+", You have more than a "+getString(R.string.quarter);
		      }else if(txtLen < 5){
		    	  txtValue =txtLen+" chars, Your a lazy typer. ";
		      }
		  }
		}
    	
    	return txtValue;
    }
    
    /*
     * This is a function to create custom hint based on an integer value
     * the default boolean is set to Type something here
     */
	/**
     * Creates the custom text hint.
     *
     * @param checkValInput the check val input
     * @param checkText the check text
     * @return the string
     */
    private String createCustomTextHint(int checkValInput, boolean checkText) {
		
		//Conditional checking the value passed in if its greater than value of 1 
		//and default checkText is false
		
		if(checkValInput >1 && checkText == false){
    		hint = "Type something here";
    	}else{
    		checkText = true;
    		hint = "Enter short text";
    	}
		return hint;
	}


	
	
    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.android_uidemo, menu);
        return true;
    }
    
}
