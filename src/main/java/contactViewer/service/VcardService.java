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

	private static String OS = System.getProperty("os.name").toLowerCase();
	private static Path outputfile;				//représente le fichier 
	private static Path outputdirectory;		// représente le dossier Vcard dans lequel sera enregistré tous les fichiers Vcard
	private static Path downloadirectory;			// représente le chemin vers download(télechargements de n'importe quel os
	public static int counter=0;				// counter utile pour garder le nombre de contacts update
	private Vcard4 vcardObject;
	
	
	public VcardService() {
		getDownloadDirectoryPath();
	}
	
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
	
	private void instantiateVcardObject(Person person) {
		this.vcardObject = new Vcard4(person.getLastname(),person.getFirstname(),person.getPhone_number().toString(),person.getAddress(),person.getEmail_address());
	}
	
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
	
	private void getDownloadDirectoryPath() {
		downloadirectory= Paths.get(System.getProperty("user.home")+"/Downloads");
	}
	
	private Boolean isVcardDirectoryAlreadyExists() {
		outputdirectory= downloadirectory.resolve("Vcard");
		if(!Files.exists(outputdirectory))
			return false;
		else
			return true;
	}
	
	private void createVcardDirectory() {
		try {
			Files.createDirectory(downloadirectory.resolve("Vcard"));
			System.out.println("Directory created ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Boolean isVcardFileAlreadyExists(String name) {
		outputfile= outputdirectory.resolve(vcardObject.getFn().replace("FN:"," ").trim()+".vcf");
		if(!Files.exists(outputfile))
			return false;
		else
			return true;
	}
	
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
