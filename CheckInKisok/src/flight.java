
public class flight {

	private String flightCode;
	private String carrier;
	private int maxPassengersCount;
	private int maxBaggageWeight;
	private String dAirport;
	private int extraBaggageCharge;
	
	public flight() {};
	
	public void setFlightCode(String code) {
		this.flightCode=code;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setCarrier(String carrier) {
		this.carrier=carrier;
	}
	public String getCarrier() {
		return this.carrier;
	}
	public void setMaxPassengersCount(int count) {
		this.maxPassengersCount=count;
	}
	public int getMaxPassengersCount() {
		return this.maxPassengersCount;
	}
	public void setMaxBaggageWeight(int baggage) {
		this.maxBaggageWeight=baggage;
	}
	public int getMaxBaagageWeight() {
		return this.maxBaggageWeight;
	}
	public void setDAirport(String dairport) {
		this.dAirport=dairport;
	}
	public String getDAirport() {
		return this.dAirport;
	}
	
	public void setExtraBaggageCharge(int charge) {
		this.extraBaggageCharge=charge;
	}
	
	public int getExtraBaggageCharge() {
		return this.extraBaggageCharge;
	}
	
}
