package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;


public class FXMLDocumentController implements Initializable {

  

    int online, offline, Available, busy;
    static Stage stage;
    ServerProject view = new ServerProject();

    @FXML
    private TextField textMsg;
    @FXML
    private ImageView imageMsg;
    @FXML
    private Button startServer;
    @FXML
    private Label off;

    @FXML
    private Label on;

    @FXML
    private Button stopServer;
    @FXML
    private BarChart<String, Number> barChart;
    ObservableList<String> categories = FXCollections.observableArrayList();
    @FXML
    private CategoryAxis xAxis;
    String[] arr = {"Online", "Offline", "Available", "Busy"};
    XYChart.Series series1 = new XYChart.Series();
    boolean available = true;
      static void setStage(Stage s) {
          stage=s;
          
    }

    public void sendText(ActionEvent e) {
        if (available) {
            System.out.println(textMsg.getText());
        }

    }

    public void startServer(ActionEvent e) {
        stopServer.setDisable(false);
        startServer.setDisable(true);
        available = true;
        on.setVisible(true);
        off.setVisible(false);
        controller.Controller.start();

    }

    public void stopServer(ActionEvent e) {
        stopServer.setDisable(true);
        startServer.setDisable(false);
        available = false;
        off.setVisible(true);
        on.setVisible(false);
        controller.Controller.stop();

    }

    public void sendImage(ActionEvent e) {
        

    }

    @FXML
   public  void swapScene(ActionEvent event) {
        ServerProject serverControllerRef = new ServerProject();
        serverControllerRef.switchView("Table.fxml");
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }

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
        updateBarChart(online, offline);
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                online = view.getOnlineUsers();
                offline = view.getOfflineUsers();
                Available = view.getAvailableUsers();
                busy = view.getBusyUsers();
                for (XYChart.Series<String, Number> series : barChart.getData()) {
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if (data.XValueProperty().getValue() == "Online") {
                            data.setYValue(online);
                        }
                        if (data.XValueProperty().getValue() == "Offline") {
                            data.setYValue(offline);

                        }
                        if (data.XValueProperty().getValue() == "Available") {
                            data.setYValue(Available);

                        }
                        if (data.XValueProperty().getValue() == "Busy") {
                            data.setYValue(busy);

                        }
                    }
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        System.out.println("init");
        startServer.setDisable(true);
    }

    void updateBarChart(int online, int offline) {
        xAxis.setCategories(categories);
        categories.addAll(Arrays.asList(arr));
        series1.setName("User Status");
        series1.getData().add(new XYChart.Data("Online", online));
        series1.getData().add(new XYChart.Data("Offline", offline));
        series1.getData().add(new XYChart.Data("Available", 10));
        series1.getData().add(new XYChart.Data("Busy", 10));
        barChart.getData().add(series1);
        barChart.setBarGap(10);

        barChart.setMouseTransparent(true);
    }

}
