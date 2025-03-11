package controller.Trip;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Agency;
import model.Destinations;
import model.Flights;
import model.Trip;

import model.Exceptions.ErrorModel;
import model.Exceptions.InsufficientDestinationsException;

import au.edu.uts.ap.javafx.*;
import controller.Destinations.ModifyDestinationsController;


public class BookTripController extends Controller<Trip> {
      @FXML private Label userLabel;
      // private Destinations destinations;
      // private Flights flights;
     // private Trip trip;

     /*  public BookTripController() {
        this.trip = new Trip(model);
      } */
      /*private Agency tripAgency;
       public static boolean isTripSelected = false;
       private Trip currentTrip;

      public static boolean getTripStatus(){
      return isTripSelected;
      }*/

      
    
    @FXML public void initialize() {
        
         userLabel.textProperty().bind(Bindings.concat("Hi ", model.getAgency().getLoggedInUser().getName(),
        ", welcome to Trip section"));
        //destinations = model.getDestinations();
       // flights = model.getFlights();
         
    }

    @FXML public void handleAddButton() throws Exception{
       // isTripSelected = true;
        //Destinations destinations = model.getDestinations();
       // ModifyDestinationsController controller = new ModifyDestinationsController(currentTrip);
        
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/trip_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/AddDestinationView.fxml", "Add Destination for Trip", stage);
    }

    @FXML public void handleRemoveButton() throws Exception{
        //isTripSelected = true;
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/trip_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination from Trip", stage);
    }

    @FXML public void handleAddFlightButton() throws Exception{
        try{
            model.addConnectingFlights();
          //  System.out.println("Connecting flights added");
        } 
        catch (InsufficientDestinationsException e){
             ErrorModel errorModel = new ErrorModel(e, "Not enough destinations");
           ViewLoader.showErrorWindow(errorModel);

        }
    }

    @FXML public void handleViewTripButton() throws Exception{
        
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/trip_icon.png"));
        ViewLoader.showStage(model, "/view/Trip/DisplayTripView.fxml", "View Trip", stage);
    }

    /*@FXML public void handleAddButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Destinations/AddDestinationView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new javafx.scene.image.Image("/image/destinations_icon.png"));
        
        ModifyDestinationsController modifyDestinationsController = new ModifyDestinationsController(trip);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Destinations/AddDestinationView.fxml"));
        loader.setController(modifyDestinationsController);
        Parent root1 = loader.load();
        stage.setScene(new Scene(root1));
        stage.show();
    } */

    /*@FXML public void handleAddConnectingFlightsButton() throws Exception {
        trip.addConnectingFlights();
    } */
    @FXML
    private void handleCloseButton(ActionEvent event) {
        
        stage.close();
    }


}
