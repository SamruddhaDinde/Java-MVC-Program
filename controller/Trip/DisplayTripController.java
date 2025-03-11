package controller.Trip;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import au.edu.uts.ap.javafx.*;
import controller.Destinations.DisplayDestinationsController;
import controller.Flights.DisplayFlightsController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import model.Exceptions.ErrorModel;



public class DisplayTripController extends Controller<Trip> {
    @FXML ListView<Itinery> tripLv;


    @FXML public void initialize() {
        tripLv.setItems(model.getItinery());
        tripLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML public void handleIndividualViewButton() throws InputMismatchException, IOException {
        try {
            ObservableList<Itinery> selectedItems = tripLv.getSelectionModel().getSelectedItems();
    
          //  boolean hasDestination = false;
            //boolean hasFlight = false;
            LinkedList<Destination> selectedDestinations = new LinkedList<Destination>();
            LinkedList<Flight> selectedFlights = new LinkedList<Flight>();
    
            for (Object item : selectedItems) {
                if (item instanceof Destination) {
                    selectedDestinations.add((Destination) item);
                } 
                 else if (item instanceof Flight) {
                  selectedFlights.add((Flight) item);
                }
    
               
               /*  if (hasDestination && hasFlight) {
                    throw new InputMismatchException();
                }*/
                if (selectedDestinations.size() > 0 && selectedFlights.size() > 0) {
                    throw new InputMismatchException();
                }
            }
    
            if (!selectedDestinations.isEmpty()) {
               // DisplayDestinationsController.setSelectedDestinations((ObservableList<Itinery>) selectedItems);
               DisplayDestinationsController.setSelectedDestinations(selectedDestinations);
                Stage stage = new Stage();
                stage.getIcons().add(new javafx.scene.image.Image("/image/destinations_icon.png"));
                ViewLoader.showStage(model.getAgency(), "/view/Destinations/DisplayDestinationsView.fxml", "Display Destination", stage);
               
            } else if (!selectedFlights.isEmpty())  {
                DisplayFlightsController.setSelectedFlights(selectedFlights);
                 Stage stage = new Stage();
                stage.getIcons().add(new javafx.scene.image.Image("/image/flights_icon.png"));
                ViewLoader.showStage(model.getAgency(), "/view/Flights/DisplayFlightsView.fxml", "Display Destination", stage);
               
            }
        } catch (InputMismatchException e) {
           // System.out.println("You can only select either a destination or a flight");
            ErrorModel errorModel = new ErrorModel(e, "Select Only Flight or Destinations");
           ViewLoader.showErrorWindow(errorModel);
         
        } 
        

        
            
       
    }

    @FXML public void handleCloseButton(){
        stage.close();
    }

}
