package br.com.javamon.admin.domain;

import java.util.Map;

public class History {

	private String url;
	private Map<String, String[]> parameterMaps;
	
	public History(String url, Map<String, String[]> parameterMaps) {
		this.url = url;
		this.parameterMaps = parameterMaps;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String[]> getParameterMaps() {
		return parameterMaps;
	}

	public void setParameterMaps(Map<String, String[]> parameterMaps) {
		this.parameterMaps = parameterMaps;
	}

	@Override
	public String toString() {
		return "History [url=" + url + "]";
	}
}
