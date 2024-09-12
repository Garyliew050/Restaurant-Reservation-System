
import java.util.Scanner;

public class ReservationApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// create BookingController object
		BookingController table = new BookingController();

		String choice;
		do {
			choice = "0";
			//display Menu 
			System.out.println("\n---------------------------------------------------------------------------------------------");
			System.out.println("                                 RESTAURANT RESERVATION SYSTEM");
			System.out.println("---------------------------------------------------------------------------------------------");
			System.out.println("                     Press 1     Add new reservation  ");
			System.out.println("                     Press 2     List all reservation ");
			System.out.println("                     Press 3     Search a reservation detail  ");
			System.out.println("                     Press 4     Update reservation ");
			System.out.println("                     Press 5     Delete reservation   ");
			System.out.println("                     Press 6     Exit  ");
			System.out.println("---------------------------------------------------------------------------------------------");
			System.out.print("Please enter your choice: ");		// input choice
			choice = input.nextLine();

			if (choice.isEmpty() || !Character.isDigit(choice.charAt(0))) // check if the choice is valid
				choice = "0";

			switch (Integer.parseInt(choice)) {
			// if the user choose 1 to add new reservation
			case 1:		
				table.add();
				table.save();
				System.out.printf("Press enter to continue...");
				input.nextLine();
				break;

			// if the user choose 2 to List all reservation	
			case 2:
				table.listAll();
				System.out.printf("Press enter to continue...");
				input.nextLine();
				break;

			// if the user entered 3 to Search a reservation detail	
			case 3:
				table.search();
				break;

			// if the user entered 4 to Update reservation	
			case 4:
				table.update();
				break;

			// if the user entered 5 to Update Delete reservation
			case 5:
				table.delete();
				break;

			// if the user entered 6 to exit the system 
			case 6:
				System.out.println("-------------------You have exited the system---------------");
				break;

			// if the user entered anything other than 1 to 6
			default:
				System.out.println("   Invalid input, please re-enter ! .....");
				break;
			}
		} while (Integer.parseInt(choice) != 6); // loop if the user did not entered 6
		input.close();
	}
	
}
