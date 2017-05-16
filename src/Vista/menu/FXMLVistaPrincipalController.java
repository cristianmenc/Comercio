/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.menu;

import Datos.BDA;
import Vista.GestionTrabajadores.GestionTrabajadoresController;
import Vista.rutas.RutasController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FXMLVistaPrincipalController implements Initializable {

    BDA bda;

    @FXML
    private Button rutas;
    Stage escenario;
    @FXML
    private Button trabajadores;

    public void setBda(BDA bda) {
        this.bda = bda;
    }

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clicarRutas(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Vista/rutas/Rutas.fxml"));
        Parent root = loader.load();
        ((RutasController) loader.getController()).setBda(bda);
        escenario = new Stage();
        escenario.setTitle("Menu principal");
        escenario.initModality(Modality.APPLICATION_MODAL);
        escenario.setScene(new Scene(root));
        escenario.showAndWait();
        Stage stage = (Stage) rutas.getScene().getWindow();
    }

    @FXML
    private void clicarTrabajadores(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Vista/GestionTrabajadores/GestionTrabajadores.fxml"));
        Parent root = loader.load();
        ((GestionTrabajadoresController) loader.getController()).setBda(bda);
        escenario = new Stage();
        escenario.setTitle("Menu principal");
        escenario.initModality(Modality.APPLICATION_MODAL);
        escenario.setScene(new Scene(root));
        escenario.showAndWait();
        Stage stage = (Stage) trabajadores.getScene().getWindow();
    }

}
