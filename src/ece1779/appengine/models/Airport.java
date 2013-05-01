package ece1779.appengine.models;

import com.google.appengine.api.datastore.Entity;

public class Airport {

	private int id;

	private String city;

	private String country;

	private String iata;

	private String icao;

	private String name;

	public Airport() {

	}

	public Airport(Entity entity) {
		setCity((String) entity.getProperty("city"));
        setCountry((String) entity.getProperty("country"));
        setIATA((String) entity.getProperty("iata"));
        setICAO((String) entity.getProperty("icao"));
        setName((String) entity.getProperty("name"));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIATA() {
		return iata;
	}

	public void setIATA(String iata) {
		this.iata = iata;
	}

	public String getICAO() {
		return icao;
	}

	public void setICAO(String icao) {
		this.icao = icao;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
