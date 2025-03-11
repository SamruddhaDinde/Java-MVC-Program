package controller;
import javafx.event.*;
import javafx.fxml.*;

import javafx.scene.control.*;
import javafx.stage.*;
import model.Agency;
import model.Destinations;
import model.Trip;
import javafx.beans.binding.*;

import au.edu.uts.ap.javafx.*;



public class AgencyController extends Controller<Agency> {
    
    
    
    @FXML private Label userLabel;
   

    @FXML private void initialize() {
         userLabel.textProperty().bind(Bindings.concat("Hi ", model.getLoggedInUser().getName(),
        ", welcome to Prog2 Travel Agency"));
        
    }

	


    @FXML private void handleFlightButton() throws Exception{
       
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Flights/ExploreFlightsView.fxml"));
        Parent root = loader.load();
        ExploreFlightsController controller = loader.getController();
        controller.setAgencyModel(agency);

         Scene scene = new Scene(root);
         
         stage.setScene(scene);
         
            stage.setTitle("Explore Flights");
            stage.sizeToScene();
            stage.show(); */
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/flights_icon.png"));
       ViewLoader.showStage(model, "/view/Flights/ExploreFlightsView.fxml", "Explore Flights", stage);


       

    }

    @FXML private void handleDestinationsButton() throws Exception{
       
      

         Destinations destinations = new Destinations(model);
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/destinations_icon.png"));
       ViewLoader.showStage(model, "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations", stage);


     

    }

     @FXML private void handleTripButton() throws Exception{
       /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Trip/BookTripView.fxml"));
        Parent root = loader.load();
        BookTripController controller = loader.getController();
        controller.setAgencyModel(model);

         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.getIcons().add(new javafx.scene.image.Image("/image/trip_icon.png"));
            stage.setTitle("Book a Trip");
            stage.sizeToScene();
            stage.show();


      
*/       Trip trip = new Trip(model);
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/trip_icon.png"));
         ViewLoader.showStage(trip, "/view/Trip/BookTripView.fxml", "Book a Trip", stage);



        
    }

    @FXML private void handleExitButton(ActionEvent event) {
        System.exit(0);
       
}
}