package controller.Flights;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Agency;
import au.edu.uts.ap.javafx.*;


public class ExploreFlightsController extends Controller<Agency> {
    @FXML private Label userLabel;

    @FXML public void initialize() {
         userLabel.textProperty().bind(Bindings.concat("Hi ", model.getLoggedInUser().getName(),
                ", welcome to Flights section"));
    }


    @FXML private void handleViewButton() throws Exception{

        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Flights/DisplayFlightsView.fxml", "Display Flights", stage);
    }


    @FXML private void handleFilterViewButton() throws Exception{
        DisplayFlightsController.setFiltered(true);
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Flights Filtered", stage);

    }

    @FXML private void handleAddButton() throws Exception{

        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Flights/AddFlightView.fxml", "Add Flight", stage);
    }

    @FXML private void handleRemoveButton() throws Exception{

        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Flights/RemoveFlightView.fxml", "Remove Flight", stage);
    }
    @FXML
    private void handleCloseButton(ActionEvent event) {
        
        stage.close();
    }

}
