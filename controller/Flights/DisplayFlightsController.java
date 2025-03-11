package controller.Flights;
import java.util.LinkedList;
import java.util.List;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Agency;
import model.Destination;
import model.Flight;
import model.Flights;
import model.Trip;



public class DisplayFlightsController extends Controller<Agency>  {
    private static boolean isFiltered = false;
    @FXML private TextField countryTf;
    @FXML private TableView<Flight> flightsTv;
    @FXML private TableColumn<Flight, String> airlineClm;
    
    @FXML private TableColumn<Flight, Integer> flightNoClm;
   
    @FXML private TableColumn<Flight, String> takeoffClm;
    @FXML private TableColumn<Flight, String> landingClm;
    @FXML private TableColumn<Flight, Double> costClm;
    private static List<Flight> selectedFlights;
      private Trip tripModel;
   
    public void setTripModel(Trip trip) {
        this.tripModel = trip;
    }

public static void setSelectedFlights(LinkedList<Flight> flights) {
   selectedFlights = flights;
}

    @FXML private void initialize() throws Exception{
       /* if (selectedFlights != null) {
            flightsTv.getItems().setAll(selectedFlights);
            selectedFlights = null;
        } */
       /*  if (model instanceof Agency) {
            agencyModel = (Agency) model;
        } else if (model instanceof Trip) {
            tripModel = (Trip) model;
        } */


      /*   if (tripModel != null){
            flightsTv.setItems(tripModel.getFlights().getFlights());
        } */
        if (selectedFlights != null) {
           setTheTable();
            flightsTv.getItems().setAll(selectedFlights);
            selectedFlights = null;
        } else {
        setTheTable();
        flightsTv.setItems(getFlights().getFlights());
        if (isFiltered) {
        countryTf.textProperty().addListener((o, oldValue, newValue) ->
         {
            try {
                filteredFlights();
            } catch (Exception e) {
                
            }
        });
    }}
    for (Object item:flightsTv.getColumns()){
        TableColumn column = (TableColumn) item;
        column.prefWidthProperty().bind(flightsTv.widthProperty().divide(5));
        
    }
}
    private void setTheTable(){
        airlineClm.setCellValueFactory(cellData -> cellData.getValue().airlineProperty());
        flightNoClm.setCellValueFactory(cellData -> cellData.getValue().flightNumberProperty().asObject());
        takeoffClm.setCellValueFactory(cellData -> cellData.getValue().takeoffProperty());
        landingClm.setCellValueFactory(cellData -> cellData.getValue().landingProperty());
        costClm.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());

    }
    

    public final Flights getFlights() throws Exception {
         return model.getFlights(); }


    private void filteredFlights() throws Exception{
        flightsTv.setItems(getFlights().getFilteredFlights(countryTf.getText()));
    }
    
    public static void setFiltered(boolean value) {
        isFiltered = value;
    }
    @FXML private void handleCloseButton(ActionEvent event) throws Exception{
        stage.close();
        setFiltered(false);
    }
}
