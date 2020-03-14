package contactViewer.util;


import contactViewer.model.entities.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class PersonValueFactory implements Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>> {


	/**
	 * @param cellData
	 * @return return SimpleStringPropert with CellDataFeatures<Person, String> parameter
	 */
	@Override
	public ObservableValue<String> call(CellDataFeatures<Person, String> cellData) {
		return new SimpleStringProperty(cellData.getValue().getLastname().concat(" ").concat(cellData.getValue().getFirstname()));
	}
	
	}
