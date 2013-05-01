package ece1779.appengine.models;

public class Route {

	private int airlineId;
	private String airline;

	private int deptAirportId;
	private String deptAirport;

	private int destAirportId;
	private String destAirport;

	private String equipment;

	public int getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getDeptAirportId() {
		return deptAirportId;
	}

	public void setDeptAirportId(int deptAirportId) {
		this.deptAirportId = deptAirportId;
	}

	public String getDeptAirport() {
		return deptAirport;
	}

	public void setDeptAirport(String deptAirport) {
		this.deptAirport = deptAirport;
	}

	public int getDestAirportId() {
		return destAirportId;
	}

	public void setDestAirportId(int destAirportId) {
		this.destAirportId = destAirportId;
	}

	public String getDestAirport() {
		return destAirport;
	}

	public void setDestAirport(String destAirport) {
		this.destAirport = destAirport;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
}
