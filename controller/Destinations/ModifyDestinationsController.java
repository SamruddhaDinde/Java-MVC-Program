package controller.Destinations;
import au.edu.uts.ap.javafx.*;
import controller.Trip.BookTripController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;


public class ModifyDestinationsController extends Controller<Object>{
    @FXML private TextField nameTf;
    @FXML private TextField countryTf;
    @FXML private Button addButton;
    @FXML private Button removeButton;
    private Trip tripModel;
    private Agency agencyModel;

    public void setTripModel(Trip trip) {
        this.tripModel = trip;
    }
   

   
    public void initialize(){
        if (model instanceof Agency) {
            agencyModel = (Agency) model;
        } else if (model instanceof Trip) {
            tripModel = (Trip) model;
        }
        if (addButton != null){
            addButton.disableProperty().bind(nameTf.textProperty().isEmpty().or(countryTf.textProperty().isEmpty()));
        }
        if (removeButton != null){
            removeButton.disableProperty().bind(nameTf.textProperty().isEmpty().or(countryTf.textProperty().isEmpty()));
        }
    }

   /*  public ModifyDestinationsController(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }
   */



    @FXML private void handleAddButton() throws DuplicateItemException, ItemNotFoundException{
        String name = nameTf.getText();
        String country = countryTf.getText();
         model.Destination destination = new model.Destination(name, country);
         // model.getDestinations().addDestination(destination);
         try {
            if (agencyModel != null) {
                // Check if the destination is not already in the agency's list
                if (!agencyModel.getDestinations().hasDestination(name, country)) {
                    agencyModel.getDestinations().addDestination(destination);
                    stage.close();
                }  else {
                    throw new DuplicateItemException();
                }
            } 
            else if (tripModel != null) {
               
                if (tripModel.getAgency().getDestinations().hasDestination(name, country)) {
                    tripModel.getDestinations().addDestination(destination);
                    stage.close();

                }  else {
                    throw new ItemNotFoundException();
                }
            }
        } catch (DuplicateItemException e) {
            ErrorModel errorModel = new ErrorModel(e, "Destination Exists");
           ViewLoader.showErrorWindow(errorModel);
           
        } catch (ItemNotFoundException e) {
            ErrorModel errorModel = new ErrorModel(e, "Destination Does Not Exist");
            ViewLoader.showErrorWindow(errorModel);
            
        } finally {
           // stage.close();
        }
    }
    
       
           
            
      
           /*  if (currentTrip != null){
                currentTrip.getDestinations().addDestination(destination);
        stage.close();
            } */
        
            
        
       
        
    

    @FXML private void handleRemoveButton() throws Exception{
        String name = nameTf.getText();
        String country = countryTf.getText();
        Destination destinationToRemove;
    

        //model.Destination destinationToRemove = model.getDestinations().destination(name, country);
        
           // model.getDestinations().removeDestination(destinationToRemove);
         
           try {
            if (agencyModel != null) {
                if (!agencyModel.getDestinations().hasDestination(name, country)) {
                    throw new ItemNotFoundException();
                }
                destinationToRemove = agencyModel.getDestinations().destination(name, country);
                agencyModel.getDestinations().removeDestination(destinationToRemove);
                stage.close();
            } 
            else if (tripModel != null) {
                if (!tripModel.getDestinations().hasDestination(name, country)) {
                    throw new ItemNotFoundException();
                }
                 destinationToRemove = tripModel.getDestinations().destination(name, country);
                tripModel.getDestinations().removeDestination(destinationToRemove);
               // System.out.println("Destination removed");
               stage.close();
            }
        } 
        catch (ItemNotFoundException e) {
          //  System.out.println("Destination does not exist");
          ErrorModel errorModel = new ErrorModel(e, "Destination not found");
          ViewLoader.showErrorWindow(errorModel);
        }
        finally {
           // stage.close();
        }
       
        
    }
    
    @FXML private void handleCloseButton() throws Exception{
        stage.close();
    }
}

