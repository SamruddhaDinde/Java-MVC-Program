package controller.Destinations;
import java.util.LinkedList;
import java.util.List;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Destination;
import model.Agency;
import model.Destinations;
import model.Trip;


public class DisplayDestinationsController extends Controller<Agency>  {
    private static  boolean isFiltered = false;
    @FXML private TextField countryTf; 
           
    @FXML private TableView<Destination> destinationsTv;
    @FXML private TableColumn<Destination, String> nameClm;
    
    @FXML private TableColumn<Destination, String> countryClm;
   //  private Trip tripModel;
    private Agency agencyModel;
    private static List<Destination> selectedDestinations;
  //  private static List<Destination> selectedDestinations;

/*public static void setSelectedDestinations(ObservableList<Destination> destinations) {
    this.selectedDestinations = destinations;
    destinationsTv.setItems(this.selectedDestinations);
}*/ 
/*public static void setSelectedDestinations(List<Destination> destinations) {
   selectedDestinations = destinations;
} */
    
//public void setTripModel(Trip trip) {
  //  this.tripModel = trip;
//}

    public static void setSelectedDestinations(LinkedList<Destination> destinations) {
        selectedDestinations = destinations;
    }

    @FXML private void initialize() throws Exception{
        /*if (model instanceof Agency) {
            agencyModel = (Agency) model;
        } else if (model instanceof Trip) {
            tripModel = (Trip) model;
        }
        if (tripModel != null){
            destinationsTv.setItems(tripModel.getDestinations().getDestinations());
        } */

        if(selectedDestinations != null){
            setTheTable();
            destinationsTv.getItems().setAll(selectedDestinations);
            selectedDestinations = null;
        } else {
       setTheTable();
        destinationsTv.setItems(getDestinations().getDestinations());
        if (isFiltered){
              
        
        countryTf.textProperty().addListener((o, oldValue, newValue) ->
         {
          
            try {
                filteredDestinations();
            } catch (Exception e) {
                
            }
        }); }}
       /*  if (selectedDestinations != null) {
            destinationsTv.getItems().setAll(selectedDestinations);
            selectedDestinations = null;  // Clear it to avoid using it again unintentionally
        } */
        for (Object item: destinationsTv.getColumns()){
            TableColumn column = (TableColumn) item;
            column.prefWidthProperty().bind(destinationsTv.widthProperty().divide(2));
            
        }

    }
        private void setTheTable(){
            nameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            countryClm.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        }

        
    
    

        public static  void setFiltered(boolean value){
           isFiltered = value;
        }


        public final Destinations getDestinations() throws Exception {
            return model.getDestinations();
        }

        private void filteredDestinations() throws Exception{
            destinationsTv.setItems(getDestinations().getFilteredDestinations(countryTf.getText()));
        }

        @FXML private void handleCloseButton(ActionEvent event) throws Exception{
            stage.close();
            setFiltered(false);
        }
    
}
