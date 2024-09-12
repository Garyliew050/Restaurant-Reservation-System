
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BookingController {

	// Arraylist
	private ArrayList<String> content = new ArrayList<>();
	private ArrayList<Customer> customerList = new ArrayList<>();
	private ArrayList<Reservation> reservationList = new ArrayList<>(); 

	// constructor
	public BookingController() {recordAll();}

	// add record
	@SuppressWarnings("deprecation")
	public void add() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Customer customer = new Customer(); // create Customer object
		Reservation reservation = new Reservation(); // create Reservation object

		// create a Reservation Number
		do {
			Random r = new Random(); // new random object
			reservation.setReservationNumber("R" + String.format("%03d", r.nextInt(100) + 1));
		} while (isExist(reservation.getReservationNumber()));

		int len = 0;
		int alpha = 0;
		int space = 0;
		int sum = 0;

		// check validation for name
		do {
			System.out.print("Enter Customer name: ");
			customer.setName(input.nextLine());
			len = customer.getName().length();
			alpha = 0;
			space = 0;
			for (int i = 0; i < len; i++) {
				if (Character.isAlphabetic(customer.getName().charAt(i)))
					alpha++;
				if (Character.isSpace(customer.getName().charAt(i)))
					space++;
			}
			sum = alpha + space;
			if (len == 0 || sum != len)
				System.out.println("   Invalid input, please re-enter!...");
		} while (len == 0 || sum != len);

		// check validation for phone number
		do {
			System.out.print("Enter Customer phone number: ");
			customer.setPhoneNumber(input.nextLine());
			len = customer.getPhoneNumber().length();
			alpha = 0;
			space = 0;
			for (int i = 0; i < len; i++) {
				if (Character.isAlphabetic(customer.getPhoneNumber().charAt(i))) // if enter number
					alpha++;
				if (Character.isSpace(customer.getPhoneNumber().charAt(i)))
					space++;
			}
			if (len == 0 || alpha > 0 || space > 0 || len < 10)
				System.out.println("   Invalid input, please re-enter!...");
		} while (len == 0 || alpha > 0 || space > 0 || len < 10);

		int digit = 0;
		int t;

		// check validation for Customer table size
		do {
			t = 0;
			System.out.print("Enter Customer table size (4, 6, 12): ");
			reservation.setTableSize(input.nextLine());
			try {
				t = Integer.parseInt(reservation.getTableSize());
				if (t != 4 && t != 6 && t != 12) {
					System.out.println("   Invalid input, please re-enter!...");
					t = 0;
				}

			} catch (Exception e) {System.out.println("   Invalid input, please re-enter!...");}

		} while (t == 0);

		int x = 0, y = 0;

		// check validation for date of reservation
		do {
			System.out.print("Enter the date of reservation(exp 13/01/22): ");
			reservation.setDate(input.nextLine());
			len = reservation.getDate().length();
			digit = 0;
			for (int i = 0; i < len; i++) {
				if (Character.isDigit(reservation.getDate().charAt(i)))
					digit++;
			}
			x = reservation.getDate().indexOf("/");
			y = reservation.getDate().lastIndexOf("/");
			if (len == 0 || (digit + 2) != len || x != 2 || y != 5 || len != 8)
				System.out.println("   Invalid input, please re-enter!...");
		} while (len == 0 || (digit + 2) != len || x != 2 || y != 5 || len != 8);

		// check validation for time of reservation
		do {
			System.out.print("Enter the time of resevation(exp 14:00): ");
			reservation.setTime(input.nextLine());
			len = reservation.getTime().length();
			digit = 0;
			for (int i = 0; i < len; i++) {
				if (Character.isDigit(reservation.getTime().charAt(i)))
					digit++;
			}
			x = reservation.getTime().indexOf(":");
			if (len == 0 || (digit + 1) != len || x != 2 || len != 5)
				System.out.println("   Invalid input, please re-enter!...");
		} while (len == 0 || (digit + 1) != len || x != 2 || len != 5);
		len = 0;

		// check validation for remark
		do {
			System.out.print("Enter remark (if don't have enter '-'): ");
			reservation.setRemark(input.nextLine());
			len = reservation.getRemark().length();

			if (len == 0)
				System.out.println("   Invalid input, please re-enter!...");
		} while (len == 0);

		// display reservation number to user
		System.out.println("\n**************** The Reservation  Number for this customer is '"+ reservation.getReservationNumber() + "' ****************\n");

		// add data to arraylist
		customerList.add(customer);
		reservationList.add(reservation);

	}

	// list all reservation in a simple form
	public void listAll() {
		// display header
		System.out.println("\n---------------------------------------------------------------------------------------------");
		System.out.println("                                  RESERVATION LIST");
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("   No.   ||  Reservation Number ||  \t   Name  \t ||   Date    ||  \tTime");
		System.out.println("---------------------------------------------------------------------------------------------");
		// display No., Reservation Number, Name, Date, Time for each record
		for (int i = 0; i < customerList.size(); i++) {
			System.out.printf(" %-2d.             ", (i + 1));
			System.out.printf("%-22s ", reservationList.get(i).getReservationNumber());
			System.out.printf("%-20s", customerList.get(i).getName());
			System.out.printf("%-20s", reservationList.get(i).getDate());
			System.out.printf("%-10s", reservationList.get(i).getTime());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------------------------");
	}

	// read from text file
	public boolean isExist(String Nor) {return content.contains(Nor);}

	// record all data in to arraylist
	public void recordAll() {

		File file = new File("Customer.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Successfully create a new File.");
			}
		}

		try {
			FileReader fileR = new FileReader(file.getName());
			BufferedReader buffR = new BufferedReader(fileR);
			String temp;
			while ((temp = buffR.readLine()) != null) {
				String[] param = temp.split("\\*");
				content.add(param[0].trim());
				content.add(param[1].trim());
				content.add(param[2].trim());
				content.add(param[3].trim());
				content.add(param[4].trim());
				content.add(param[5].trim());
				content.add(param[6].trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
		for (int i = 0; i < content.size();) {
			Customer data = new Customer();
			Reservation data1 = new Reservation();
			data1.setReservationNumber(content.get(i++));
			data.setName(content.get(i++));
			data.setPhoneNumber(content.get(i++));
			data1.setDate(content.get(i++));
			data1.setTime(content.get(i++));
			data1.setTableSize(content.get(i++));
			data1.setRemark(content.get(i++));

			customerList.add(data);
			reservationList.add(data1);
		}
	}

	// get number of customer
	public int getNumOfCustomer() {return customerList.size();}

	// update record
	@SuppressWarnings("deprecation")
	public void update() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String count1, count2;
		listAll();
		// identify which record to update
		do {
			System.out.print("Which one you want to update? (Enter the No.): ");
			count1 = input.nextLine();
			if (count1.isEmpty() || !Character.isDigit(count1.charAt(0))) {
				count1 = "0";
				System.out.println("   Invalid input, please re-enter!...");
			} else if (Integer.parseInt(count1) > getNumOfCustomer()) {
				count1 = "0";
				System.out.println("   Invalid input, please re-enter!...");
			}
		} while (count1 == "0");

		int num1 = Integer.parseInt(count1) - 1;
		boolean check = false;
		do {
			do {
				// display the record in detail
				System.out.println("\n---------------------------------------------------------------------------------------------");
				System.out.println("                                RESERVATION DETAIL FOR No." + (num1 + 1));
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.printf(" Name: %-49sReservation Number: %s\n", customerList.get(num1).getName(), reservationList.get(num1).getReservationNumber());
				System.out.printf(" Phone Number: %-41sDate: %s\n", customerList.get(num1).getPhoneNumber(),reservationList.get(num1).getDate());
				System.out.printf(" Reservation Time: %-37sTable size: %s\n", reservationList.get(num1).getTime(),reservationList.get(num1).getTableSize());
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.println(" Remarks: " + reservationList.get(num1).getRemark());
				System.out.println("\n---------------------------------------------------------------------------------------------");
				System.out.print("which one you want to update?\n1. Name\n2. Phone number\n3. Date\n4. Time\n5. Table Size\n6. Remark\nEnter your choice:");
				count2 = input.nextLine();
				if (count2.isEmpty() || !Character.isDigit(count2.charAt(0))) { // verify the input of choice
					count2 = "0";
					System.out.println("   Invalid input, please re-enter!...");
				}
			} while (count2 == "0");

			int num2 = Integer.parseInt(count2);
			String change = null;
			int len = 0;
			int alpha = 0;
			int space = 0;
			int sum = 0;
			int digit = 0;
			int x = 0, y = 0;
			int t;
			switch (num2) {
			case 1: // if user choose 1 for update Customer name
				// validation for name
				do {
					System.out.print("Enter new Customer name: ");
					change = input.nextLine();
					len = change.length();
					alpha = 0;
					space = 0;
					for (int i = 0; i < len; i++) {
						if (Character.isAlphabetic(change.charAt(i)))
							alpha++;
						if (Character.isSpace(change.charAt(i)))
							space++;
					}
					sum = alpha + space;
					if (len == 0 || sum != len)
						System.out.println("   Invalid input, please re-enter!...");
				} while (len == 0 || sum != len);
				customerList.get(num1).setName(change);
				check = false;
				break;

			case 2: // if user choose 2 for update Customer phone number
				// validation for phone number
				do {
					System.out.print("Enter new Customer phone number: ");
					change = input.nextLine();
					len = change.length();
					alpha = 0;
					space = 0;
					for (int i = 0; i < len; i++) {
						if (Character.isAlphabetic(change.charAt(i)))
							alpha++;
						if (Character.isSpace(change.charAt(i)))
							space++;
					}
					if (len == 0 || alpha > 0 || space > 0 || len < 10)
						System.out.println("   Invalid input, please re-enter!...");
				} while (len == 0 || alpha > 0 || space > 0 || len < 10);

				customerList.get(num1).setPhoneNumber(change);
				check = false;
				break;

			case 3: // if user choose 3 for update date of reservation
				// validation for date of reservation
				do {
					System.out.print("Enter new date of reservation(exp 13/01/22): ");
					change = input.nextLine();
					len = change.length();
					digit = 0;
					for (int i = 0; i < len; i++) {
						if (Character.isDigit(change.charAt(i)))
							digit++;
					}
					x = change.indexOf("/");
					y = change.lastIndexOf("/");
					if (len == 0 || (digit + 2) != len || x != 2 || y != 5 || len != 8)
						System.out.println("   Invalid input, please re-enter!...");
				} while (len == 0 || (digit + 2) != len || x != 2 || y != 5 || len != 8);
				reservationList.get(num1).setDate(change);
				check = false;
				break;

			case 4:// if user choose 4 for update time of reservation
					// validation for time of reservation
				do {
					System.out.print("Enter the new time of resevation(exp 14:00): ");
					change = input.nextLine();
					len = change.length();
					digit = 0;
					for (int i = 0; i < len; i++) {
						if (Character.isDigit(change.charAt(i)))
							digit++;
					}
					x = change.indexOf(":");
					if (len == 0 || (digit + 1) != len || x != 2 || len != 5)
						System.out.println("   Invalid input, please re-enter!...");
				} while (len == 0 || (digit + 1) != len || x != 2 || len != 5);
				reservationList.get(num1).setTime(change);
				check = false;
				break;

			case 5:// if user choose 5 for update table size
					// validation for table size
				do {
					t = 0;
					System.out.print("Enter new Customer table size (4, 6, 12): ");
					change = input.nextLine();
					try {
						t = Integer.parseInt(change);
						if (t != 4 && t != 6 && t != 12) {
							System.out.println("   Invalid input, please re-enter!...");
							t = 0;
						}
					} catch (Exception e) {

						System.out.println("   Invalid input, please re-enter!...");
					}
				} while (t == 0);
				reservationList.get(num1).setTableSize(change);
				check = false;
				break;

			case 6:// if user choose 6 for remark
					// validation for remark
				do {
					System.out.print("Enter new remark (if don't have enter '-'): ");
					change = input.nextLine();
					len = change.length();

					if (len == 0)
						System.out.println("   Invalid input, please re-enter!...");
				} while (len == 0);
				reservationList.get(num1).setRemark(change);
				check = false;
				break;

			default: // if the user entered anything other than 1 to 6
				System.out.println("   Invalid input, please re-enter!...");
				check = true;
			}
		} while (check);

		// save data 
		save();
	}

	// search record
	public void search() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int num = 0;
		String temp = null;
		boolean isIn = false;
		// identify which record to search
		do {
			listAll();
			isIn = false;
			System.out.print(" Enter the reservation number to search: ");
			temp = input.nextLine();
			for (int i = 0; i < customerList.size(); i++) {
				if (temp.equals(reservationList.get(i).getReservationNumber())) {
					num = i;
					isIn = true;
					break;
				}
			}
			if (!isIn) {
				// if the reservation number is not found
				System.out.println("Not Found!");
				System.out.print("Still have customer to find? ( Y /Press enter to menu):");
				temp = input.nextLine();
			} else {
				// if the reservation number entered is found
				System.out.println("\n---------------------------------------------------------------------------------------------");
				System.out.println("                                RESERVATION DETAIL FOR '" + temp + "'");
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.printf(" Name: %-49sReservation Number: %s\n", customerList.get(num).getName(),reservationList.get(num).getReservationNumber());
				System.out.printf(" Phone Number: %-41sDate: %s\n", customerList.get(num).getPhoneNumber(),reservationList.get(num).getDate());
				System.out.printf(" Reservation Time: %-37sTable size: %s\n", reservationList.get(num).getTime(),reservationList.get(num).getTableSize());
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.println(" Remarks: " + reservationList.get(num).getRemark());
				System.out.println("\n---------------------------------------------------------------------------------------------");
				System.out.print("Still have customer to find? ( Y /Press enter to menu):");
				temp = input.nextLine();
			}
		} while ((temp.equals("Y") || temp.equals("y"))); // loop if the user want to continue search

	}

	// delete a record
	public void delete() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		listAll();
		String choice;
		// identify which record to delete and check for validation
		do {
			System.out.print("Which one you want to delete?(Enter the No.): ");
			choice = input.nextLine();

			if (choice.isEmpty() || !Character.isDigit(choice.charAt(0))) {
				choice = "0";
				System.out.println("   Invalid input, please re-enter!...");
			} else if (Integer.parseInt(choice) > getNumOfCustomer()) {
				choice = "0";
				System.out.println("   Invalid input, please re-enter!...");
			}
		} while (choice == "0");
		customerList.remove((Integer.parseInt(choice) - 1));
		reservationList.remove((Integer.parseInt(choice) - 1));
		save();
	}

	// save record
	public void save() {
		// save record in to text file
		try {
			File file = new File("Customer.txt");
			FileWriter FileW = new FileWriter(file);
			PrintWriter of = new PrintWriter(FileW);
			of.write("");
			of.flush();
			for (int i = 0; i < customerList.size(); i++) {
				of.printf("%-4s* %-24s* %-15s* %-15s* %-15s* %-15s* %-5s* %n",
						reservationList.get(i).getReservationNumber().trim(), customerList.get(i).getName().trim(),
						customerList.get(i).getPhoneNumber().trim(), reservationList.get(i).getDate().trim(),
						reservationList.get(i).getTime().trim(), reservationList.get(i).getTableSize().trim(),
						reservationList.get(i).getRemark().trim());
			}
			of.close();
		} catch (IOException e) {}
	}
}
