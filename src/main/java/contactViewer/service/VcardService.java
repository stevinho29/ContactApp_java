package contactViewer.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import contactViewer.model.entities.Person;
import pojo.Vcard4;

public class VcardService {

	/**
	 * attribut of the VcardService class
	 */
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static Path outputfile;				//représente le fichier 
	private static Path outputdirectory;		// représente le dossier Vcard dans lequel sera enregistré tous les fichiers Vcard
	private static Path downloadirectory;			// représente le chemin vers download(télechargements de n'importe quel os
	public static int counter=0;				// counter utile pour garder le nombre de contacts update
	private Vcard4 vcardObject;
	
	/**
	 * constructor of the VcardService class
	 */
	public VcardService() {
		getDownloadDirectoryPath();
	}
	/**
	 * coordinates the export of contacts
	 * @param List<Person> personList
	 * @throws IOException
	 */
	public void orchestrator(List<Person> personList) throws IOException { // coordonne l'exportation des contacts
		counter = 0;			// on réinitialise le counter à zero
		if(isVcardDirectoryAlreadyExists()) {
			for(Person p: personList) {
				instantiateVcardObject(p);
				exportVcardFile();
			}	
		}else {
			createVcardDirectory();
			for(Person p: personList) {
				instantiateVcardObject(p);
				exportVcardFile();
			}
		}
		
	}
	/**
	 * export of a single person
	 * @param person
	 * @throws IOException
	 */
	
	public void orchestrator( Person person) throws IOException {		// export d'une seule personne
		counter = 0;			// on réinitialise le counter à zero
		if(isVcardDirectoryAlreadyExists()) {
			instantiateVcardObject(person);
			exportVcardFile();
		}
		else {
			createVcardDirectory();
			instantiateVcardObject(person);
			exportVcardFile();
		}
	}
	/**
	 * 
	 * Instantiates a vcardObject
	 * @param person
	 */
	private void instantiateVcardObject(Person person) {
		this.vcardObject = new Vcard4(person.getLastname(),person.getFirstname(),person.getPhone_number().toString(),person.getAddress(),person.getEmail_address());
	}
	/**
	 * export VcardFile
	 * @throws IOException
	 */
	private void exportVcardFile() throws IOException {
		
			if(isVcardFileAlreadyExists(vcardObject.getFn())) {
				counter++;
				fillinVcardFile();
			}
			else {
				createVcardFile(vcardObject.getFn());
				fillinVcardFile();
			}
	}
	/**
	 * this method fill in a Vcard file by writing in bufferedWriter 
	 * @throws IOException
	 */
	private void fillinVcardFile() throws IOException {

		BufferedWriter bufferedWriter= Files.newBufferedWriter(outputfile, StandardCharsets.UTF_8);
		try {
				bufferedWriter.write(vcardObject.getBegin());
				bufferedWriter.write(vcardObject.getVersion());
				bufferedWriter.write(vcardObject.getFn());
				bufferedWriter.write(vcardObject.getTel());
				bufferedWriter.write(vcardObject.getAddress());
				bufferedWriter.write(vcardObject.getEmail());
				bufferedWriter.write(vcardObject.getEnd());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
		
			bufferedWriter.flush();
			bufferedWriter.close();
		}
	}
	/**
	 * static method to get Download directory on every OS
	 */
	private static void getDownloadDirectoryPath() {
		downloadirectory= Paths.get(System.getProperty("user.home")+"/Downloads");
	}
	/**
	 * verify if Vcard directory already exist or not
	 * @return
	 */
	private Boolean isVcardDirectoryAlreadyExists() {
		outputdirectory= downloadirectory.resolve("Vcard");
		if(!Files.exists(outputdirectory))
			return false;
		else
			return true;
	}
	/**
	 * create Vcard Directory
	 */
	private void createVcardDirectory() {
		try {
			Files.createDirectory(downloadirectory.resolve("Vcard"));
			System.out.println("Directory created ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * verify if Vcard File specify by the parameter name Already Exists 
	 * @param name
	 * @return a boolean true if it exist or false if it doesn't
	 */
	private Boolean isVcardFileAlreadyExists(String name) {
		outputfile= outputdirectory.resolve(vcardObject.getFn().replace("FN:"," ").trim()+".vcf");
		if(!Files.exists(outputfile))
			return false;
		else
			return true;
	}
	/**
	 * create Vcard File specify by the parameter name
	 * @param name
	 */
	private void createVcardFile(String name) {
		try {
			Files.createFile(outputfile);
			System.out.println("File created "+ outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
