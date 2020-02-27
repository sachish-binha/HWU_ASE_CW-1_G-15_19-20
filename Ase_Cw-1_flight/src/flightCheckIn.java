import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class flightCheckIn {

	//static variables for all over program usage
	static Map<String, ticket> ticketsMap = new HashMap<>();
	static Map<String, flight> flightDetailsMap = new HashMap<>();
	static int totalPassengersCheckedIn = 0;
	static int totalExcessBaggageFeeCollected = 0;
	static int totalBaggageWeightCheckedIn = 0;
	static int totalBaggageVolumeCheckedIn = 0;
	static Scanner sc = new Scanner(System.in);

	public static void main(String args[]) throws IOException {
		
		//URL class used for path independence
		URL bookingFilepath = flightCheckIn.class.getResource("bookings.csv");
		URL flightDetailsFilePath = flightCheckIn.class.getResource("flightDetails.csv");
		
		//getting file
		File bookingsFile = new File(bookingFilepath.getFile());
		File flightDetailsFile = new File(flightDetailsFilePath.getFile());
		try {
			//reading file
			FileReader bookingsFileReader = new FileReader(bookingsFile);
			BufferedReader bookingBufferReader = new BufferedReader(bookingsFileReader);
			String ticket;
			//reading bookings list file
			while ((ticket = bookingBufferReader.readLine()) != null) {
				readTheTicket(ticket);
			}

			FileReader flightDetailsFileReader = new FileReader(flightDetailsFile);
			BufferedReader flightDetailsBufferReader = new BufferedReader(flightDetailsFileReader);
			String flightDetails;
			
			//reading flight details file
			while ((flightDetails = flightDetailsBufferReader.readLine()) != null) {

				readFlightDetails(flightDetails);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("----Welcome to Check-In Kisok----");
		
		//checking in method
		doCheckIn();
	}

	//check in method
	private static void doCheckIn() {
		
		String opt="y";
		do {
			
			//getting ticket refrencing number
			System.out.println("Please enter your booking reference number");
			String referenceNumber = sc.next();

			
			if (ticketsMap.containsKey(referenceNumber)) { //verifying reference number

				System.out.println("Plese enter your last name");//getting last name
				String lastName = sc.next();
				checkIsAlphaString(lastName);
				ticket temp = ticketsMap.get(referenceNumber);

				if (temp.getName().getLastName().equalsIgnoreCase(lastName)) {//checking last name

					String flightCode = temp.getFlightCode();
					
					if (flightDetailsMap.get(flightCode) == null) {
						System.out.println("The flight " + temp.getFlightCode() + " is not available for this booking");
						doCheckIn();
					}

					System.out.println("Enter volume of Baggege");//getting volume of baggage
					String tempVal = sc.next();
					checkIsInteger(tempVal);
					int volume = Integer.parseInt(tempVal);
					totalBaggageVolumeCheckedIn += volume; // increasing total volume of baggage

					System.out.println("please enter your baggage weight");//getting baggage weight
					tempVal = sc.next();
					checkIsInteger(tempVal);
					int baggage = Integer.parseInt(tempVal);

					flight flight = flightDetailsMap.get(flightCode);
					int baggageWeight = flight.getMaxBaagageWeight();
					
					//checking excess bagage fee
					if (baggage > baggageWeight) {
						int extraCharge = calcExtraCharge(baggage, flight.getExtraBaggageCharge(),
							flight.getMaxBaagageWeight());
						System.out.println("Your Baggage is extra weight. You have to pay £" + extraCharge);
						totalExcessBaggageFeeCollected += extraCharge;
					}

					//increasing total 
					totalBaggageWeightCheckedIn += baggage;
					totalPassengersCheckedIn += 1;
				
					//exit method
					opt=exitToReport();
				
				} else {
					System.out.println("You have entered wrong last name");
					doCheckIn();
				}
			} else {
				System.out.println("You have entered wrong reference number");
				doCheckIn();
			}
		
		}while ( opt== "Y" || opt=="y");
		
	printSummary();
}

	private static String exitToReport() {
		
		System.out.println("Do you want to continue 'y' or 'n' ");
		String opt=sc.next();
		
		if( ! opt.equalsIgnoreCase("Y") && ! opt.equalsIgnoreCase("N")) {
			System.out.println("Wrong input");
			exitToReport();
		}
		
		return opt;
	}

	private static void checkIsAlphaString(String lastName) {
		if (lastName != null && lastName.chars().allMatch(Character::isLetter)) {
		} else {
			System.out.println("You entered wrong format input");
			doCheckIn();
		}
	}

	private static void checkIsInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			System.out.println("You entered wrong format input");
			doCheckIn();
		}
	}

	private static void printSummary() {

		System.out.println("Summary Report" + "\n Toal No.of Passengers Checked-in: " + totalPassengersCheckedIn
				+ "\n Total Weight Checked-in: " + totalBaggageWeightCheckedIn
				+ "\n Total volume of Baggege checked-in: " + totalBaggageVolumeCheckedIn
				+ "\n Total Excess Baggage Fee Collecgted: " + totalExcessBaggageFeeCollected);

	}

	private static int calcExtraCharge(int enteredBaggage, int extraBaggageCharge, int maxBaagageWeight) {

		int extraWeightage = enteredBaggage - maxBaagageWeight;
		if (extraWeightage <= maxBaagageWeight) {
			return extraBaggageCharge;
		} else {
			return (extraWeightage / maxBaagageWeight) * extraBaggageCharge;
		}
	}

	private static void readFlightDetails(String flightDetails) {

		flight flightObj = new flight();
		String[] details = flightDetails.split(",");
		flightObj.setFlightCode(details[0]);
		System.out.println(flightObj.getFlightCode());
		flightObj.setDAirport(details[1]);
		flightObj.setCarrier(details[2]);
		flightObj.setMaxPassengersCount(Integer.parseInt(details[3]));
		flightObj.setMaxBaggageWeight(Integer.parseInt(details[4]));
		flightObj.setExtraBaggageCharge(Integer.parseInt(details[5]));
		flightDetailsMap.put(flightObj.getFlightCode(), flightObj);
	}

	private static void readTheTicket(String booking) {
		ticket ticketObj = new ticket();
		String[] details = booking.split(",");
		ticketObj.setReferenceCode(details[0]);
		ticketObj.setName(new Name(details[1]));
		ticketObj.setFlightCode(details[2]);
		ticketObj.setIsCheckedIn(Boolean.parseBoolean(details[3]));
		ticketsMap.put(details[0], ticketObj);
	}
}
