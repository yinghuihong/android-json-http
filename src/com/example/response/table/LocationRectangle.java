package com.example.response.table;

public class LocationRectangle {

	private Location northeast;

	private Location southwest;

	public Location getNortheast() {
		return northeast;
	}

	public void setNortheast(Location northeast) {
		this.northeast = northeast;
	}

	public Location getSouthwest() {
		return southwest;
	}

	public void setSouthwest(Location southwest) {
		this.southwest = southwest;
	}

	@Override
	public String toString() {
		return "LocationRectangle [northeast=" + northeast + ", southwest="
				+ southwest + "]";
	}

}
