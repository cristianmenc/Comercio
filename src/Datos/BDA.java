/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Ruta;
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

    public Trabajador consultar(String dni) throws SQLException {
        Trabajador t = null;
        String consulta = "SELECT * FROM trabajadores";
        PreparedStatement ps = conn.prepareCall(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String  dni1 = rs.getString("dniNie");
            
            String nombre = rs.getString("contraseña");
            System.out.println(rs.getString("dniNie")+rs.getString("contraseña"));
            t = new Trabajador(dni1, nombre);
        }

        return t;
    }

    public void insertarRuta(Integer ruta, Double kilometros, Integer minutos, Double gastos) throws SQLException {
        String consulta = "INSERT INTO rutas(IdRuta,kilometros,Minutos,Gastos) values (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(1, ruta);
        ps.setDouble(2, kilometros);
        ps.setInt(3, minutos);
        ps.setDouble(4, gastos);
        ps.executeUpdate();

    }
    
    public void insertarTrabajador(Integer idTrabajador, String nombre, String puesto, String dniNie, Double salario, String contraseña) throws SQLException {
        String consulta = "INSERT INTO trabajadores (idTrabajador, nombre, puesto, dniNie, salario, contraseña, idTienda1, idHorario, IRPF) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(1, idTrabajador);
        ps.setString(2, nombre);
        ps.setString(3, puesto);
        ps.setString(4, dniNie);
        ps.setDouble(5, salario);
        ps.setString(6, contraseña);
        ps.setInt(7, 1);
        ps.setInt(8, 1);
        ps.setDouble(9, 30);
        ps.executeUpdate();

    }

    public List<Trabajador> listarTrabajadores() throws SQLException {
        List<Trabajador> listaTrabajadores = new ArrayList<>();
        String consulta = "SELECT * FROM trabajadores";
        PreparedStatement ps = conn.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id1 = rs.getInt("idTrabajador");
            String nombre = rs.getNString("nombre");
            String puesto = rs.getNString("puesto");
            String dni = rs.getNString("dniNie");
            Double salario = rs.getDouble("salario");
            String contraseña = rs.getNString("contraseña");
            Trabajador t = new Trabajador(id1, nombre, puesto, dni, salario, contraseña);
            listaTrabajadores.add(t);
        }
        return listaTrabajadores;
    }
    
    public List<Ruta> listarRutas() throws SQLException{
        List<Ruta> listaRutas= new ArrayList<>();
        String consulta= "SELECT * FROM rutas";
        PreparedStatement ps= conn.prepareStatement(consulta);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            int id=rs.getInt("idRuta");
            double kilometros=rs.getDouble("kilometros");
            int minutos=rs.getInt("Minutos");
            double gastos=rs.getDouble("Gastos");
            Ruta r=new Ruta(id,kilometros,minutos,gastos);
            listaRutas.add(r);
        }
        return listaRutas;
    }
    public int borrarRuta(int id) throws SQLException{
        Ruta r = null;
        String consulta="DELETE FROM rutas WHERE idRuta=?";
        PreparedStatement ps= conn.prepareStatement(consulta);
        ps.setInt(1, id);
        int id1=ps.executeUpdate();
        return id1;
    }
    
    public int borrarTrabajador(int id) throws SQLException{
        Trabajador r = null;
        String consulta="DELETE FROM trabajadores WHERE idTrabajador=?";
        PreparedStatement ps= conn.prepareStatement(consulta);
        ps.setInt(1, id);
        int id1=ps.executeUpdate();
        return id1;
    }

}
