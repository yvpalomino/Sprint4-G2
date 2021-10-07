/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.ConexionBD;

/**
 *
 * @author DJ Solutions 2
 */
public class provedores {
    

    private int id_provedor;
    private String nombre;
    private String telefono;
    private String email;
    
    public provedores() {
    }

    public provedores getProvedor(int idProvedor) throws SQLException {
        this.id_provedor = idProvedor;
        return this.getProvedores();
    
        
    }

    public int getId() {
        return id_provedor;
    }

    public void setId(int idProvedor) {
        this.id_provedor = idProvedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

 
    public boolean guardarProvedor() {
        ConexionBD conexion = new ConexionBD();
        String sentencia = "INSERT INTO provedor(id_provedor, nombre, telefono, email) "
                + " VALUES ( '" + this.id_provedor + "','" + this.nombre + "',"
                + "'" + this.telefono + "','" + this.email +  "');  ";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean borrarProvedor(int id_provedor) {
        String Sentencia = "DELETE FROM `provedor` WHERE `id_provedor`='" + id_provedor + "'";
        ConexionBD conexion = new ConexionBD();
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean actualizarProvedor() {
        ConexionBD conexion = new ConexionBD();
        String Sentencia = "UPDATE `provedor` SET nombre='" + this.nombre + "',telefono='" + this.telefono 
                +  "',email='" + this.email
                +  "' WHERE id_provedor=" + this.id_provedor + ";";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public List<provedores> listarContactos() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        List<provedores> listaContactos = new ArrayList<>();
        String sql = "select * from provedor order by id_provedor asc";
        ResultSet rs = conexion.consultarBD(sql);
        provedores c;
        while (rs.next()) {
            c = new provedores();
            c.setId(rs.getInt("id_provedor"));
            c.setNombre(rs.getString("nombre"));
            c.setTelefono(rs.getString("telefono"));
            c.setEmail(rs.getString("email"));
            listaContactos.add(c);
        }
        conexion.cerrarConexion();
        return listaContactos;
    }

    public provedores getProvedores() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        String sql = "select * from provedor where id_provedor='" + this.id_provedor + "'";
        ResultSet rs = conexion.consultarBD(sql);
        if (rs.next()) {
            this.id_provedor = rs.getInt("id_provedor");
            this.nombre = rs.getString("nombre");
            this.telefono = rs.getString("telefono");
            this.email = rs.getString("email");
            conexion.cerrarConexion();
            return this;

        } else {
            conexion.cerrarConexion();
            return null;
        }

    }

    @Override
    public String toString() {
        return "provedor{" + "id_provedro=" + id_provedor + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
}

