import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

public class CheckInMachine {
	
	static Map<String, ticket> ticketsMap = new HashMap<>();
	static Map<String, flight> flightDetailsMap = new HashMap<>();
	static int totalPassengersCheckedIn = 0;
	static double totalExcessBaggageFeeCollected = 0;
	static int totalBaggageWeightCheckedIn = 0;
	static int totalBaggageVolumeCheckedIn = 0;
	
	public static void main(String args[]) throws IOException {
		
		//URL class used for path independence
				URL bookingFilepath = CheckInMachine.class.getResource("bookings.csv");
				URL flightDetailsFilePath = CheckInMachine.class.getResource("flightDetails.csv");
				
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
		
		kisok kisokObj = new kisok();
		kisokObj.createWindowOne();
		
		
	}


	private static void readFlightDetails(String flightDetails) {

		flight flightObj = new flight();
		String[] details = flightDetails.split(",");
		flightObj.setFlightCode(details[0]);
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
	public boolean verifyReferenceNumber(String enteredReferenceNumber) {

		if (ticketsMap.containsKey(enteredReferenceNumber)) {
			return true;
		}
		else {
			return false;
		}
		
	}


	public boolean isCheckin(String enteredReferenceNumber) {
		
		if(ticketsMap.get(enteredReferenceNumber).getIsCheckedIn()) {
			return true;
		}
		return false;
	}


	public boolean checkLastName(String enteredReferenceNumber, String enteredLastName ) {

		
		if((ticketsMap.get(enteredReferenceNumber).getName().getLastName()).equalsIgnoreCase(enteredLastName)) {
			return true;
		}
		return false;
	}


	public String calcExtraCharge(String enteredReferenceNumber, String enteredBaggageWeight, String vol) {
		
		
		String chargeLabel="";
		double chargePayable=0;
	    int maxWeight=flightDetailsMap.get(ticketsMap.get(enteredReferenceNumber).getFlightCode()).getMaxBaagageWeight();
		int charge= flightDetailsMap.get(ticketsMap.get(enteredReferenceNumber).getFlightCode()).getExtraBaggageCharge();
		
		double extraWeightage = Double.parseDouble(enteredBaggageWeight) - maxWeight;
		
		if ((extraWeightage>0) && (extraWeightage <= maxWeight)) {
			chargePayable= charge;
		} else {
			double temp= maxWeight;
			double temp_one= extraWeightage;
			chargePayable= (temp_one / temp) * charge;
		}
		
		chargeLabel= ""+chargePayable;
		
		totalExcessBaggageFeeCollected+=chargePayable;
		totalBaggageWeightCheckedIn+=Double.parseDouble(enteredBaggageWeight);
		totalBaggageVolumeCheckedIn+=Double.parseDouble(vol);
		return chargeLabel;
	}
	
}
