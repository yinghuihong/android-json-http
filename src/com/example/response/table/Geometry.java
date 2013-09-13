package com.example.response.table;

public class Geometry {

	private LocationRectangle bounds;

	private Location location;

	private String location_type;

	private LocationRectangle viewport;

	public LocationRectangle getBounds() {
		return bounds;
	}

	public void setBounds(LocationRectangle bounds) {
		this.bounds = bounds;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public LocationRectangle getViewport() {
		return viewport;
	}

	public void setViewport(LocationRectangle viewport) {
		this.viewport = viewport;
	}

}
