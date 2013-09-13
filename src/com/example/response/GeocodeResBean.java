package com.example.response;

import java.util.ArrayList;

import com.example.response.table.GeocodeTable;
import com.open.jsonhttp.ResponseBean;

/**
 * geocode api 响应数据的封装类
 * 
 * @author yinghui.hong
 */
public class GeocodeResBean extends ResponseBean {

	private ArrayList<GeocodeTable> results;

	public ArrayList<GeocodeTable> getResults() {
		return results;
	}

	public void setResults(ArrayList<GeocodeTable> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "GencodeResBean [results=" + results + "]";
	}

}
