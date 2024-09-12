
public class Reservation {
	
	// instance variables
	private String reservationNumber;
	private String time;
	private String date;
	private String tableSize;
	private String remark;
	
	// Access methods
	public String getReservationNumber() {return reservationNumber;}
	public String getTime() {return time;}
	public String getDate() {return date;}
	public String getTableSize() {return tableSize;}
	public String getRemark() {return remark;}
	
	// Modifier methods
	public void setTime(String time) {this.time = time;}
	public void setDate(String date) {this.date = date;}
	public void setTableSize(String tableSize) {this.tableSize = tableSize;}
	public void setRemark(String remark) {this.remark = remark;}
	public void setReservationNumber(String reservationNumber) {this.reservationNumber = reservationNumber;}
	
	//constructor
	public Reservation(){}
}
