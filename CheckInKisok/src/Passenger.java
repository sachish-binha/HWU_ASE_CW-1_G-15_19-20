
public  class Passenger {
	private Name name;
	private boolean isCheckedIn;
	
	public Passenger() {};
	public Passenger(String name, boolean checkInStatus){
		
	}
	
	public void setNmae(Name name) {
		this.name=name;
	}
	
	public Name getName() {
		return this.name;
	}
	
	public void setIsCheckedIn(boolean status) {
		this.isCheckedIn=status;
	}
	
	public boolean getIsCheckedIn() {
		return this.isCheckedIn;
	}
}
