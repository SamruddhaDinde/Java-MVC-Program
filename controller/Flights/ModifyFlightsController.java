package controller.Flights;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Agency;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyFlightsController extends Controller<Agency> {
    @FXML private TextField airlineTf;
    @FXML private TextField flightNoTf;
    @FXML private TextField takeoffTf;
    @FXML private TextField landingTf;
    @FXML private TextField costTf;
    @FXML private Button addFlightBtn;
    @FXML private Button removeFlightBtn;

    public void initialize(){
        if (addFlightBtn!=null){
            addFlightBtn.disableProperty().bind(airlineTf.textProperty().isEmpty().or(flightNoTf.textProperty().isEmpty())
            .or(takeoffTf.textProperty().isEmpty()).or(landingTf.textProperty().isEmpty())
            .or(costTf.textProperty().isEmpty()));
        }

        if (removeFlightBtn != null){
            removeFlightBtn.disableProperty().bind(takeoffTf.textProperty().isEmpty().or(landingTf.textProperty().isEmpty()));
        }
    }


    @FXML private void handleAddButton() throws Exception{
        String airline = airlineTf.getText();
        Integer flightNo;
        String takeoff = takeoffTf.getText();
        String landing = landingTf.getText();
        double cost;

       // model.Flight flight = new model.Flight(airline, flightNo, takeoff, landing, cost);

        
            //model.getFlights().addFlight(flight);
            //stage.close();
            try {
        flightNo = Integer.parseInt(flightNoTf.getText());
        cost = Double.parseDouble(costTf.getText());

        if (model.getFlights().hasFlight(takeoff, landing)) {
            throw new DuplicateItemException();
        } 

        model.Flight flight = new model.Flight(airline, flightNo, takeoff, landing, cost);
        model.getFlights().addFlight(flight);
        stage.close();
    } catch (NumberFormatException e) {
       //System.out.println("Invalid input");
        ErrorModel errorModel = new ErrorModel(e, "Input Integers");
           ViewLoader.showErrorWindow(errorModel);
    } catch (DuplicateItemException e) {
        //System.out.println("Flight already exists");
        ErrorModel errorModel = new ErrorModel(e, "Flight exists");
        ViewLoader.showErrorWindow(errorModel);
    } finally {
      // stage.close();
    }
}

    @FXML private void handleRemoveButton() throws Exception{
       
        String takeoff = takeoffTf.getText();
        String landing = landingTf.getText();
       

        //model.Flight flightToRemove = model.getFlights().getFlight(takeoff, landing);
        //model.getFlights().removeFlight(flightToRemove);
          //  stage.close();
          try {
       
        if (!model.getFlights().hasFlight(takeoff, landing)) {
            throw new ItemNotFoundException();
        } 

        model.Flight flightToRemove = model.getFlights().getFlight(takeoff, landing);
        model.getFlights().removeFlight(flightToRemove);
        stage.close();

    } catch (ItemNotFoundException e) {
       // System.out.println("Flight does not exist");
       ErrorModel errorModel = new ErrorModel(e, "Flight does not exist");
       ViewLoader.showErrorWindow(errorModel);
    } finally {
        //stage.close();
    }
    }

    @FXML private void handleCloseButton() throws Exception{
        stage.close();
    }
    
}

