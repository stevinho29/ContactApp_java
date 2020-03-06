package contactViewer.model.entities;

import java.time.LocalDate;

public class Person {

	private Integer idperson;
	private String lastname;
	private String firstname;
	private String nickname;
	private Integer phone_number;
	private String address;
	private String email_address;
	private LocalDate birth_date;
	private String url_photo;
	
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
	public Person( String lastname, String firstname, String nickname) {
		this.lastname= lastname;
		this.firstname= firstname;
		this.nickname= nickname;
		this.phone_number= 12;
		this.address= "anywhere";
		this.email_address= "email";
		this.birth_date= LocalDate.now();
		this.url_photo= "url";
	
	}
	public Integer getIdperson() {
		return idperson;
	}
	public void setIdperson(Integer idperson) {
		this.idperson = idperson;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public LocalDate getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}
	public String getUrl_photo() {
		return url_photo;
	}
	public void setUrl_photo(String url_photo) {
		this.url_photo = url_photo;
	}
	
	
	
}
