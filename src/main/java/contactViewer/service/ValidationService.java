package contactViewer.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import contactViewer.model.entities.Person;

public class ValidationService {

	/**
	 * attribute of the ValidationService class
	 */
	private static String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
	private static Pattern pattern = Pattern.compile(regex);
	
	private static Map<String, Boolean>  validationMap;
	
	/**
	 * initialize the map with the person variable
	 * @param person
	 * @return
	 */
	public static Map<String,Boolean> registerValidation(Person person){
		
		if(!person.getLastname().isEmpty())
			validationMap.put("lastnameField", true);
		else
			validationMap.put("lastnameField", false);
		
		if(!person.getFirstname().isEmpty())
			validationMap.put("firstnameField", true);
		else
			validationMap.put("firstnameField", false);
		
		if(!person.getNickname().isEmpty())
			validationMap.put("nicknameField", true);
		else
			validationMap.put("nicknameField", false);
		
		if(!person.getAddress().isEmpty())
			validationMap.put("addressField", true);
		else
			validationMap.put("addressField", false);
		
		if(!person.getEmail_address().isEmpty())
			validationMap.put("emailAddressField", true);
		else
			validationMap.put("emailAddressField", false);
		
		//if(!person.getUrl_photo().isEmpty())
		//	validationMap.put("urlPhoto", true);
		//else
		//	validationMap.put("urlPhoto", false);
		
		
		return validationMap;
		
	}
	/**
	 * verify if the phone number typed down by the user is a valid phone number
	 * @param phoneNumber
	 * @return
	 */
	public static Boolean isValidPhoneNumber(String phoneNumber) {
		Integer nb;
		try {
			nb= Integer.valueOf(phoneNumber);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * verify if the email typed down by the user is a valid email address
	 * @param email
	 * @return
	 */
	public static Boolean isValidEmailFormat(String email) {
	
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
}
