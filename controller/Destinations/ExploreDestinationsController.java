package controller.Destinations;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Agency;
import model.Destinations;
import model.Trip;
import au.edu.uts.ap.javafx.*;


public class ExploreDestinationsController extends Controller<Agency>{
     @FXML private Label userLabel;
      //Trip trip = new Trip(model.getAgency());

    @FXML public void initialize() {
         userLabel.textProperty().bind(Bindings.concat("Hi ", model.getLoggedInUser().getName(),
                ", welcome to Destinations section"));
                   
    }


    @FXML private void handleViewButton() throws Exception{

        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", stage);
    }

    @FXML private void handleFilterViewButton() throws Exception{
         DisplayDestinationsController.setFiltered(true);
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destinations Filtered", stage);

       
        
    }

    @FXML private void handleAddButton() throws Exception{
    
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/AddDestinationView.fxml", "Add Destination", stage);
    }

     @FXML private void handleRemoveButton() throws Exception{

        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", stage);
    }
    @FXML
    private void handleCloseButton(ActionEvent event) {
        
        stage.close();
    }

   
}
