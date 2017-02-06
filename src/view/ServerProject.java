package view;

import controller.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ServerProject extends Application {

    static Controller controller;
    Stage stage;
    public ServerProject() {

    }

    @Override
    public void init() throws Exception {
        controller = new Controller(this);
        System.out.println("view");
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        //stage.setResizable(false);
        stage.getIcons().add(new Image("images/chatting.png"));
        stage.setTitle("Connect Me");

        stage.setScene(scene);
        stage.show();
    }

   public void switchView(String fileName){
        try {
            FXMLLoader lodaer = new FXMLLoader();
            Parent root1 = lodaer.load(getClass().getResource(fileName));
            stage.setScene(new Scene(root1));
           
        } catch (IOException ex) {
            Logger.getLogger(ServerProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    int getOnlineUsers() {
        return controller.getOnlineUsers();
    }

    int getOfflineUsers() {
        return controller.getOfflineUsers();
    }

    int getAvailableUsers() {
        return controller.getAvailableUsers();
    }

    int getBusyUsers() {
        return controller.getBusyUsers();
    }

}
