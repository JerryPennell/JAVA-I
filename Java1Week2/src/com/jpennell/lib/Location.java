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
 * The Class Location
 */
public class Location implements Weather {

	/** The zip. */
	String zip;

	/**
	 * Instantiates a new location.
	 *
	 * @param zip the zip
	 */
	public Location(String zip) {
		setZip(zip);
	}

	/* (non-Javadoc)
	 * @see com.jpennell.lib.Weather#setZip(java.lang.String)
	 */
	@Override
	public boolean setZip(String zip) {
		this.zip = zip;
		return true;
	}

	/* (non-Javadoc)
	 * @see com.jpennell.lib.Weather#getZip()
	 */
	@Override
	public String getZip() {
		return this.zip;
	}

}
