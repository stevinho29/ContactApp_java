package pojo;

public class Vcard4 {

	final private String begin= "BEGIN:VCARD\n";
	final private String version= "VERSION:4.0\n";
	//private String n;
	private String fn;
	private String photo;
	final private String photoEncode= "PHOTO:data:image/jpeg;base64,[base64-data]";
	private String tel;
	private String address;
	private String email;
	final private String end= "END:VCARD";
	
public Vcard4(String name, String surname, String urlPhoto,String tel,String address, String email) {
		
		this.fn= "FN:".concat(name).concat(" ").concat(surname).concat("\n");
		this.photo= "PHOTO;MEDIATYPE=image/jpeg:".concat(urlPhoto).concat("\n");
		this.tel="TEL;TYPE=home,voice;VALUE=uri:tel:".concat(tel).concat("\n");
		this.address= "ADR;TYPE=HOME;LABEL=".concat(address).concat("\"").concat("\n");
		this.email="EMAIL:".concat(email).concat("\n");
	}
	
	public Vcard4(String name, String surname, String tel,String address, String email) {
		
		this.fn= "FN:".concat(name).concat(" ").concat(surname).concat("\n");
		this.tel="TEL;TYPE=home,voice;VALUE=uri:tel:".concat(tel).concat("\n");
		this.address= "ADR;TYPE=HOME;LABEL=".concat(address).concat("\"").concat("\n");
		this.email="EMAIL:".concat(email).concat("\n");
	}
	
	
	public String getBegin() {
		return begin;
	}

	public String getVersion() {
		return version;
	}

	public String getEnd() {
		return end;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoEncode() {
		return photoEncode;
	}
	
}
