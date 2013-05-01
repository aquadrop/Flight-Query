package ece1779.appengine.models;

import com.google.appengine.api.datastore.Entity;

public class Airline {

	private String name;

	private String country;

	private String callassign;

	private String iata;

	private String icao;

	public Airline() {

	}

	public Airline(Entity entity) {
		setCallAssign((String) entity.getProperty("callassign"));
        setCountry((String) entity.getProperty("country"));
        setIATA((String) entity.getProperty("iata"));
        setICAO((String) entity.getProperty("icao"));
        setName((String) entity.getProperty("name"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCallAssign() {
		return callassign;
	}

	public void setCallAssign(String callassign) {
		this.callassign = callassign;
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
}
