/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField textMsg;
    @FXML
    private ImageView imageMsg;
    @FXML
    private Button startServer;

    @FXML
    private Button stopServer;
    boolean available = true;

    public void sendText(ActionEvent e) {
        if (available) {
            System.out.println(textMsg.getText());
        }

    }

    public void startServer(ActionEvent e) {
        stopServer.setDisable(false);
        startServer.setDisable(true);
        available = true;
        controller.Conroller.start();

    }

    public void stopServer(ActionEvent e) {
        stopServer.setDisable(true);
        startServer.setDisable(false);
        available = false;
        controller.Conroller.stop();

    }

    public void sendImage(ActionEvent e) {

    }

    public void chooseFile(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {

            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageMsg.setImage(image);
            } catch (IOException ex) {
                System.out.println("mafesh sora");

            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("init");
        startServer.setDisable(true);
    }

}
