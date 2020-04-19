package model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Payment {

	private int id;
    private String patientName;
    private long amount;
    private Date paydate;
    private String address;
    private String contactNo;
    private String email;
	
    public Payment() {
		super();
	}

	public Payment(int id, String patientName, long amount, Date paydate, String address, String contactNo,
			String email) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.amount = amount;
		this.paydate = paydate;
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", patientName=" + patientName + ", amount=" + amount + ", paydate=" + paydate
				+ ", address=" + address + ", contactNo=" + contactNo + ", email=" + email + "]";
	}
	
	
	
    
    

}
