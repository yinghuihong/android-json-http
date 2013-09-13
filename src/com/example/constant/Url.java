package com.example.constant;

public interface Url {

	String BASE = "http://maps.googleapis.com/maps/api/";

	String OUTPUT = "/json";

	String GENCODE = BASE + "geocode" + OUTPUT;

	String DIRECTIONS = BASE + "directions" + OUTPUT;
}
