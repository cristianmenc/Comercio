/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.GestionTrabajadores;

import Datos.BDA;
import Modelo.Trabajador;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author daw
 */
public class GestionTrabajadoresController implements Initializable {

    BDA bda;
    @FXML
    private Tab infoTrabajador;
    @FXML
    private TextField id;
    @FXML
    private TextField nombre;
    @FXML
    private TextField puesto;
    @FXML
    private TextField dni;
    @FXML
    private TextField salario;
    @FXML
    private TextField contraseña;
    @FXML
    private Button modificarTrabajador;
    @FXML
    private Button eliminarTrabajador;
    @FXML
    private TextField idModif;
    @FXML
    private TextField idEliminar;

    public void setBda(BDA bda) {
        this.bda = bda;
    }

    private ObservableList<Trabajador> listaTrabajadores;

    @FXML
    private Button añadirTrabajor;
    @FXML
    private TableView<Trabajador> tableView;
    @FXML
    private TableColumn<Trabajador, Integer> idColum;
    @FXML
    private TableColumn<Trabajador, String> nombreColum;
    @FXML
    private TableColumn<Trabajador, String> puestoColum;
    @FXML
    private TableColumn<Trabajador, String> dniColum;
    @FXML
    private TableColumn<Trabajador, Double> salarioColum;
    @FXML
    private TableColumn<Trabajador, String> ContraseñaColum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void añadirTrabajador(ActionEvent event) throws SQLException {
        try {
            Integer idTrabajador = Integer.parseInt(id.getText());
            String nombreTrabajador = nombre.getText();
            String puestoTrabajador = puesto.getText();
            String dniNieTrabajador = dni.getText();
            Double salarioTrabajador = Double.parseDouble(salario.getText());
            String contraseñaTrabajador = contraseña.getText();

            bda.insertarTrabajador(idTrabajador, nombreTrabajador, puestoTrabajador, dniNieTrabajador, salarioTrabajador, contraseñaTrabajador);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Trabajador");
            a.setHeaderText("El trabajador a sido introducida con exito");
            a.show();
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Trabajador");
            a.setHeaderText("Error al insertar el trabajador : "+ex.getMessage());
            a.show();
        }
    }
    
    

    @FXML
    public void visualizarLista() throws SQLException {
        List<Trabajador> listaTrab;
        listaTrab = bda.listarTrabajadores();
        listaTrabajadores = FXCollections.observableArrayList(listaTrab);
        tableView.setItems(listaTrabajadores);

        idColum.setCellValueFactory(new PropertyValueFactory<>("idTrabajador"));
        nombreColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        puestoColum.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        dniColum.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        salarioColum.setCellValueFactory(new PropertyValueFactory<>("salario"));
        ContraseñaColum.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
    }

    @FXML
    private void modificarTrabajador(ActionEvent event) {
        
    }

    @FXML
    private void eliminarTrabajador(ActionEvent event) {
        try {
            int idTrabajador = Integer.parseInt(idEliminar.getText());
            bda.borrarTrabajador(idTrabajador);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Trabajador");
            a.setHeaderText("El trabajador ha sido eliminado con exito");
            a.show();
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error al borrar el trabajador: "+ex.getMessage());
            a.show();
        }
    }
}
