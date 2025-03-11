package controller;



import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Administrator;
import model.Agency;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidCredentialsException;

public class LoginController extends Controller<Agency> {

    @FXML private TextField usernameTf;
    @FXML private TextField passwordTf;
    @FXML private Button loginBtn;
   
    public LoginController(){
      
       
    }
    public void initialize() {
        loginBtn.disableProperty().bind(usernameTf.textProperty().isEmpty().or(passwordTf.textProperty().isEmpty()));
    }


    public String getUsername() {
        return usernameTf.getText();
    }
    
    public String getPassword() {
        return passwordTf.getText();
    }
    
    @FXML private void handleLoginButton() throws Exception{
        String username = getUsername();
        String password = getPassword();

        try {
            Administrator admin = model.getAdministrators().getAdministrator(username, password);
           model.setLoggedInUser(admin);
           nextView();
           stage.close();
        } catch (InvalidCredentialsException e) {
             
           ErrorModel errorModel = new ErrorModel(e, "Invalid Credentials");
           ViewLoader.showErrorWindow(errorModel);
            
          
        }
    }


    private void nextView() throws Exception{
        
        Stage stage = new Stage();
        stage.getIcons().add(new javafx.scene.image.Image("/image/agency_icon.png"));
        ViewLoader.showStage(model, "/view/AgencyView.fxml", "Agency", stage);
        
       
    }

    
    

    @FXML private void handleExitButton() {
        stage.close();
       
    }
}