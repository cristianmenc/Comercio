/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Trabajador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borja
 */
public class BDA {

    private Connection conn;

    public void conectar() throws SQLException {
        String bd = "comercio";
        String url = "jdbc:mysql://localhost:3306/" + bd;
        conn = DriverManager.getConnection(url, "root", "root");
    }

    public Trabajador consultar(int id) throws SQLException {
        Trabajador t = null;
        String consulta = "SELECT * FROM trabajadores";
        PreparedStatement ps = conn.prepareCall(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id1 = rs.getInt("idTrabajador");
            String nombre = rs.getString("contraseña");
            t = new Trabajador(id1, nombre);
        }
        
        return t;
    }

    public void insertarRuta(Integer ruta, Double kilometros, Integer trabajador, Integer minutos, Double gastos) throws SQLException {
        String consulta = "INSERT INTO rutas(IdRuta,kilometros,idTrabajador,Minutos,Gastos) values (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(1, ruta);
        ps.setDouble(2, kilometros);
        ps.setInt(3, trabajador);
        ps.setInt(4, minutos);
        ps.setDouble(5, gastos);
        ps.executeUpdate();
        
    }
    
    public List<Trabajador> listarTrabajadores() throws SQLException{
        List<Trabajador> listaTrabajadores = new ArrayList<>();
        String consulta = "SELECT * FROM trabajadores";
        PreparedStatement ps=conn.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=ps.executeQuery();
        rs.first();
        while(rs.next()){
            int id1=rs.getInt("idTrabajador");
            String nombre=rs.getString("nombre");
            String puesto=rs.getString("puesto");
            String dni=rs.getString("dniNie");
            Double salario=rs.getDouble("salario");
            String contraseña=rs.getString("contraseña");
            Trabajador t= new Trabajador(id1,nombre,puesto,dni,salario,contraseña);
            listaTrabajadores.add(t);
        }
        return listaTrabajadores;
    }
}
