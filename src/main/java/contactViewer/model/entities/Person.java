package contactViewer.model.entities;

import java.time.LocalDate;

public class Person {
	
	/**
	 * attribute of the Person class
	 */
	private Integer idperson;
	private String lastname;
	private String firstname;
	private String nickname;
	private Integer phone_number;
	private String address;
	private String email_address;
	private LocalDate birth_date;
	private String url_photo;
	
	/**
	 * parameter constructor of the Person class
	 * @param lastname
	 * @param firstname
	 * @param nickname
	 * @param number
	 * @param address
	 * @param email
	 * @param birth_date
	 * @param url_photo
	 */
	public Person(String lastname, String firstname, String nickname, Integer number,
			String address, String email, LocalDate birth_date,String url_photo) {
		
		this.lastname= lastname;
		this.firstname= firstname;
		this.nickname= nickname;
		this.phone_number= number;
		this.address= address;
		this.email_address= email;
		this.birth_date= birth_date;
		this.url_photo= url_photo;
	}
	/**
	 * parameter constructor of the person class
	 * @param lastname
	 * @param firstname
	 * @param nickname
	 */
	public Person( String lastname, String firstname, String nickname) {
		this.lastname= lastname;
		this.firstname= firstname;
		this.nickname= nickname;
		this.phone_number= 00;
		this.address= "anywhere";
		this.email_address= "noemail";
		this.birth_date= LocalDate.now();
		this.url_photo= "nourl";
	
	}
	/**
	 * default constructor of the Person class
	 */
	public Person() {}
	/**
	 * return the person id
	 * @param idperson
	 */
	public Integer getIdperson() {
		return idperson;
	}
	/**
	 * set the person id
	 * @return
	 */
	public void setIdperson(Integer idperson) {
		this.idperson = idperson;
	}
	/**
	 * return the person last name
	 * @param lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * set the person last name
	 * @return
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * return the person first name
	 * @return
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * set the person first name
	 * @return
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * return the person nickname
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * set the person nickname
	 * @return
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * return the person phone number
	 * @return
	 */
	public Integer getPhone_number() {
		return phone_number;
	}
	/**
	 * set the person phone number
	 * @return
	 */
	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}
	/**
	 * return the person address
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * set the person address
	 * @return
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * return the person email address
	 * @return
	 */
	public String getEmail_address() {
		return email_address;
	}
	/**
	 * set the person email address
	 * @return
	 */
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	/**
	 * return the person birth date
	 * @return
	 */
	public LocalDate getBirth_date() {
		return birth_date;
	}
	/**
	 * set the person birth date
	 * @return
	 */
	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}
	/**
	 * return the person url photo
	 * @return
	 */
	public String getUrl_photo() {
		return url_photo;
	}
	/**
	 * set the person url photo
	 * @return
	 */
	public void setUrl_photo(String url_photo) {
		this.url_photo = url_photo;
	}
	
	
	
}
