/*
 * project		WeatherCast
 * 
 * package		com.jpennell.weathercast
 * 
 * author		Jerry Pennell
 * 
 * date			Jul 23, 2013
 */
package com.jpennell.weathercast;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.jpennell.Library.FileSystem;
import com.jpennell.Library.Web;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

    //Global variables
    /** The _context. */
    Context _context;
    
    /** The _app layout. */
    LinearLayout _appLayout;
    
    /** The _history. */
    HashMap<String, String> _history;
    
    /** The _search. */
    SearchForm _search;
    
    /** The _is connected. */
    Boolean _isConnected = false;
    
    /** The _weather. */
    WeatherDisplay _weather;
    //FavDisplay _fav;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Call createLayout method
        createLayout();
    }


    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * Creates the layout.
     */
    public void createLayout() {
        // Declare variables
        _context = this;
        _appLayout = new LinearLayout(this);
        _history = getHistory();
        Log.i("HISTORY READ", _history.toString());

        // Declare resource string
        String _hint = getResources().getString(R.string.search_hint);
        String _buttonText = getResources().getString(R.string.search_button_text);

        _search = new SearchForm(_context, _hint, _buttonText);

        // Add Search Handler
        Button searchButton = _search.getButton();

        // Create onClickListener for searchButton
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("CLICK HANDLER", _search.getField().getText().toString());

                // Check to make sure entered value is valid zip
                if (_search.getField().getText().toString().length() != 5) {
                    // Create toast (popup)
                    Toast toast = Toast.makeText(_context,"Invalid Zip", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // Call getWeather method
                    getWeather(_search.getField().getText().toString());
                }
            }
        });

        // Detect Network Connection
        _isConnected = Web.getConnectionStatus(_context);
        if (_isConnected) {
            Log.i("NETWORK CONNECTION", Web.getConnectionType(_context));

            // Enable button
            searchButton.setClickable(true);

        } else {
            // Alert if not connected
            AlertDialog.Builder alert = new AlertDialog.Builder(_context);
            alert.setTitle("No Network Connection");
            alert.setMessage("Please check your network connections and try again.");
            alert.setCancelable(false);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.show();

            // Disable button
            searchButton.setClickable(false);
        }

        // Add Weather Display
        _weather = new WeatherDisplay(_context);

        // Add views to main view
        _appLayout.addView(_search);
        _appLayout.addView(_weather);

        // Set layout orientation
        _appLayout.setOrientation(LinearLayout.VERTICAL);

        // Set contentView
        setContentView(_appLayout);
    }

    /**
     * Gets the weather.
     *
     * @param zip the zip
     * @return the weather
     */
    private void getWeather(String zip) {
        Log.i("CLICKED", zip);
       
        String baseUrl = "http://api.worldweatheronline.com/free/v1/weather.ashx";
        
        //Requested key for api calls
        String apiKey = "qsxcvw8kpztq9hpwjsm3yaa6";
        String qs = "";
        try {
            qs = URLEncoder.encode(zip, "UTF-8");
        } catch (Exception e) {
            Log.e("BAD URL", "ENCODING PROBLEM");
        }

        URL finalURL;
        try {
            finalURL = new URL(baseUrl + "?q=" + qs + "&format=json&key=" + apiKey);
            // Call weatherRequest method
            weatherRequest wr = new weatherRequest();
            wr.execute(finalURL);
            
        } catch (MalformedURLException e) {
            Log.e("BAD URL", "MalformedURLException");
            finalURL = null;
        }
    }

    /**
     * Gets the history.
     *
     * @return the history
     */
    @SuppressWarnings("unchecked")
	private HashMap<String, String> getHistory() {
        Object stored = FileSystem.readObjectFile(_context, "history", false);

        HashMap<String, String> history;
        if (stored == null) {
            Log.i("HISTORY", "NO HISTORY FILE FOUND");
            history = new HashMap<String, String>();
        } else {
            history = (HashMap<String, String>) stored;
        }
        return history;
    }

    /**
     * The Class weatherRequest.
     */
    private class weatherRequest extends AsyncTask<URL, Void, String> {

        /* (non-Javadoc)
         * @see android.os.AsyncTask#doInBackground(Params[])
         */
        @Override
        protected String doInBackground(URL...urls) {
            String response = "";
            for (URL url:urls) {
                response = Web.getURLStringResponse(url);
            }
            return response;
        }

        /* (non-Javadoc)
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            Log.i("URL RESPONSE", result);

            try {
                // Pull JSON data from API
                JSONObject json = new JSONObject(result);
                JSONObject data = json.getJSONObject("data");
                Boolean error = data.has("error");
                if (error) {
                    // Create toast (popup)
                    Toast toast = Toast.makeText(_context,"Invalid Zip", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // Get JSON data
                    JSONArray results = json.getJSONObject("data").getJSONArray("current_condition");
                    String request = json.getJSONObject("data").getJSONArray("request").getJSONObject(0).getString("query");

                    // Get values from JSON to display
                    String _description = results.getJSONObject(0).getJSONArray("weatherDesc").getJSONObject(0).getString("value");
                    String _tempC = results.getJSONObject(0).getString("temp_C");
                    String _tempF = results.getJSONObject(0).getString("temp_F");
                    String _humidity = results.getJSONObject(0).getString("humidity");
                    String _windSpeed = results.getJSONObject(0).getString("windspeedMiles");
                    String _windDirection = results.getJSONObject(0).getString("winddir16Point");

                    Log.i("RESULTS", results.toString());
                    Log.i("REQUEST", request);
                    Log.i("WEATHER VALUES", _description + _tempC + _tempF + _humidity + _windSpeed + _windDirection);

                    // Set values in WeatherDisplay
                    _weather.setWeatherInfo(_description, _tempC , _tempF, _humidity, _windSpeed, _windDirection);

                    // Create toast (popup)
                    Toast toast = Toast.makeText(_context,"Valid Zip, " + request, Toast.LENGTH_SHORT);
                    toast.show();

                    // Adds JSON data to internal and external storage
                    _history.put(request, results.toString());
                    // Saves to internal
                    FileSystem.storeObjectFile(_context, "history", _history, false);
                    // Saves to external
                    FileSystem.storeStringFile(_context, "temp", results.toString(), true);
                }
            } catch (JSONException e) {
                Log.e("JSON", e.toString());
            }
        }
    }
}
