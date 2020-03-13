package contactViewer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ProfilePhotoService {

	private static String OS = System.getProperty("os.name").toLowerCase();
	private static Path outputfile;					
	private static Path vcarddirectory;				// dossier contenant les fichiers vcard
	private static Path outputdirectory;			// dossier contenant les images de profile
	private static Path downloadirectory;			// représente le chemin vers download(télechargements de n'importe quel os
	
	public ProfilePhotoService() {
		getDownloadDirectoryPath();
		
	}
	
	public void orchestrator( String email,String AbsoluthPath) throws IOException {
		
		if(isVcardDirectoryAlreadyExists()) {
			constructAndSaveUrl(email,AbsoluthPath);
			
		}else {
			createVcardDirectory();
			constructAndSaveUrl(email,AbsoluthPath);
		}
			
	}
	
	private void getDownloadDirectoryPath() {
		downloadirectory= Paths.get(System.getProperty("user.home")+"/Downloads");
	}
	
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
	
	private void copyImage(String AbsoluthPath,String name) throws IOException {
		System.out.println("outputdirectory "+outputdirectory);
		System.out.println(outputdirectory+name);
		Files. copy(Paths.get(AbsoluthPath), outputdirectory.resolve(name),StandardCopyOption.REPLACE_EXISTING);
	}
	
	private Boolean isVcardDirectoryAlreadyExists() {
		vcarddirectory= downloadirectory.resolve("Vcard");
		if(!Files.exists(vcarddirectory))
			return false;
		else
			return true;
	}
	
	private void createVcardDirectory() {
		try {
			Files.createDirectory(vcarddirectory);
			System.out.println("Directory created : Vcard");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Boolean isProfileDirectoryAlreadyExists() {
		outputdirectory= vcarddirectory.resolve("profile images");
		System.out.println("1 frst outputdirectory "+outputdirectory);
		if(!Files.exists(outputdirectory))
			return false;
		else
			return true;
	}
	
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
