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

// TODO: Auto-generated Javadoc
/**
 * The Enum Enum.
 */
public enum Enum {

	/** The Charleston. */
	Charleston(90,90.5,"NE","10"),
	
	/** The Greenville. */
	Greenville(87,93.2,"NE","10"),
	
	/** The Chester. */
	Chester(97,100.8,"NE","14"),
	
	/** The Clover. */
	Clover(95,95,"NE","13");

	/** The humidity. */
	private final int humidity;
	
	/** The temp. */
	private final double temp;
	
	/** The wind dir. */
	private final String windDir;
	
	/** The wind speed. */
	private final String windSpeed;


	/**
	 * Instantiates a new enum.
	 *
	 * @param humidity the humidity
	 * @param temp the temp
	 * @param windDir the wind dir
	 * @param windSpeed the wind speed
	 */
	private Enum(int humidity, double temp, String windDir, String windSpeed) {
		this.humidity = humidity;
		this.temp = temp;
		this.windDir = windDir;
		this.windSpeed = windSpeed;
	}

	/**
	 * Sets the humidity.
	 *
	 * @return the string
	 */
	public int setHumidity() {
		return humidity;
	}

	/**
	 * Sets the temp.
	 *
	 * @return the string
	 */
	public double setTemp() {
		return temp;
	}

	/**
	 * Sets the wind dir.
	 *
	 * @return the string
	 */
	public String setWindDir() {
		return windDir;
	}

	/**
	 * Sets the wind speed.
	 *
	 * @return the string
	 */
	public String setWindSpeed() {
		return windSpeed;
	}
}