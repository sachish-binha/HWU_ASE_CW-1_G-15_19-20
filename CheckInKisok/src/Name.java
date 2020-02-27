//Basics 6
public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	public Name(String fullName) {
		int spacePos1 = fullName.indexOf(' ');
		if (spacePos1 == -1) {
			firstName = fullName;
			middleName = "";
			lastName = "";
		} else {
			firstName = fullName.substring(0, spacePos1);
			int spacePos2 = fullName.lastIndexOf(' ');
			  if (spacePos1 == spacePos2)
				  middleName = "";
			  else 
				  middleName = fullName.substring(spacePos1+1, spacePos2);
			  lastName = fullName.substring(spacePos2 + 1);
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String fn) {
		firstName = fn;
	}

	public void setMiddleName(String mn) {
		middleName = mn;
	}

	public void setLastName(String ln) {
		lastName = ln;
	}

	public String getFirstAndLastName() {
		return firstName + " " + lastName;
	}

	public String getLastCommaFirst() {
		return lastName + ", " + firstName;
	}

	public String getFullName() {
		String result = firstName + " ";
		if (!middleName.equals("")) {
			result += middleName + " ";
		}
		result += lastName;
		return result;
	}

	public String getInitials() {
		String initials = "";
		initials += getFirstName().charAt(0);
		initials += (getMiddleName().isEmpty()) ? "" : getMiddleName().charAt(0);
		initials += (getLastName().isEmpty()) ? "" : getLastName().charAt(0);
		return initials.trim();
	}
}
