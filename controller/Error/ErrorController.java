package controller.Error;
import java.util.ResourceBundle.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Exceptions.ErrorModel;
import javafx.scene.Node;
import javafx.scene.control.Label;
import model.Exceptions.ErrorModel;
import au.edu.uts.ap.javafx.Controller;



public class ErrorController extends Controller<ErrorModel> {

    @FXML private Label exceptionName;
    @FXML private Label exceptionMessage;
   


    public void initialize(){
       /*  if (model instanceof ErrorModel) {
            ErrorModel errorModel = (errorModel) ;
            exceptionName.setText(errorModel.getException().getClass().getSimpleName());
            exceptionMessage.setText(errorModel.getMessage());
        }*/

        exceptionName.setText(model.getException().getClass().getSimpleName());
        exceptionMessage.setText(model.getMessage());
     

    }

    @FXML
    private void handleCloseButton() {
        //Node source = (Node) event.getSource();
        //Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    
}
