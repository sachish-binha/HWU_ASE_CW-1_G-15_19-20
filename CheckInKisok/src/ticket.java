public class ticket{

	private String referenceCode;
	private Name name;
	private String filghtCode;
	private boolean isCheckedIn;
	
	public ticket() {}
	public ticket(String reference, Name name, String code, boolean status) {
		
		setReferenceCode(reference);
		setName(name);
		setFlightCode(code);
		setIsCheckedIn(status);
		
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode= referenceCode;
	}
	
	public String getReferenceCode() {
		return referenceCode;
	}
	
	public void setName(Name name) {
		this.name=name;
	}
	
	public Name getName() {
		return name;
	}
	
	public void setFlightCode(String code) {
		this.filghtCode=code;
	}
	
	public String getFlightCode() {
		return this.filghtCode;
	}
	
	public void setIsCheckedIn(boolean status) {
		this.isCheckedIn= status;
	}
	
	public boolean getIsCheckedIn() {
		return this.isCheckedIn;
	}
}
