package contactViewer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ProfilePhotoService {

	/**
	 * attribute of the ProfilePhotoService Class
	 */
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static Path outputfile;					
	private static Path vcarddirectory;				// dossier contenant les fichiers vcard
	private static Path outputdirectory;			// dossier contenant les images de profile
	private static Path downloadirectory;			// représente le chemin vers download(télechargements de n'importe quel os
	
	
	public ProfilePhotoService() {
		getDownloadDirectoryPath();
		
	}
	/**
	 *  coordinates url photo saving,takes to parameters, unique email address and AbsoluthPath that specify where the profilePhoto(image) is
	 * @param email
	 * @param AbsoluthPath
	 * @throws IOException
	 */
	public void orchestrator( String email,String AbsoluthPath) throws IOException {
		
		if(isVcardDirectoryAlreadyExists()) {
			constructAndSaveUrl(email,AbsoluthPath);
			
		}else {
			createVcardDirectory();
			constructAndSaveUrl(email,AbsoluthPath);
		}
			
	}
	/**
	 * static method that get Download Directory Path in every OS
	 */
	private static void getDownloadDirectoryPath() {
		downloadirectory= Paths.get(System.getProperty("user.home")+"/Downloads");
	}
	/**
	 * constructs and save url with the email and AbsoluthPath parameters 
	 * @param email
	 * @param AbsoluthPath
	 * @throws IOException
	 */
	private void constructAndSaveUrl(String email,String AbsoluthPath) throws IOException {
		String name= AbsoluthPath.substring(AbsoluthPath.lastIndexOf("/")+1);
		
		if(isProfileDirectoryAlreadyExists()) {
			String url= outputdirectory+"/"+name;
			PersonService.addUrlPhoto(email, url);
			copyImage(AbsoluthPath,name);
		}else {
			String url= outputdirectory+"/"+name;
			createProfileDirectory();
			PersonService.addUrlPhoto(email, url);
			copyImage(AbsoluthPath,name);
		}
	}
	/**
	 * copy image file from is input path to a target path, it requires a name and AbsoluthPath as parameters
	 * @param AbsoluthPath
	 * @param name
	 * @throws IOException
	 */
	private void copyImage(String AbsoluthPath,String name) throws IOException {
		System.out.println("outputdirectory "+outputdirectory);
		System.out.println(outputdirectory+name);
		Files. copy(Paths.get(AbsoluthPath), outputdirectory.resolve(name),StandardCopyOption.REPLACE_EXISTING);
	}
	/**
	 * Verify if Vcard directory already exist
	 * @return return true it exits and false if it doesn't
	 */
	private Boolean isVcardDirectoryAlreadyExists() {
		vcarddirectory= downloadirectory.resolve("Vcard");
		if(!Files.exists(vcarddirectory))
			return false;
		else
			return true;
	}
	/**
	 * create VcardDirectory 
	 */
	private void createVcardDirectory() {
		try {
			Files.createDirectory(vcarddirectory);
			System.out.println("Directory created : Vcard");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Verify if Profile directory already exist
	 * @return return true it exits and false if it doesn't
	 */
	private Boolean isProfileDirectoryAlreadyExists() {
		outputdirectory= vcarddirectory.resolve("profile images");
		System.out.println("1 frst outputdirectory "+outputdirectory);
		if(!Files.exists(outputdirectory))
			return false;
		else
			return true;
	}
	/**
	 * create the ProfileDirectory
	 */
	private void createProfileDirectory() {
		try {
			Files.createDirectory(outputdirectory);
			System.out.println("Directory created "+ outputdirectory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
