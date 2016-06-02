
import java.sql.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author slagogonzalez
 */
public class Metodos {
    private String sql;
    

    public class conectar {

      
        com.mysql.jdbc.Connection conn;
 /**
     * Conecta la base de datos con el programa
     * @param servidor pasamos el nombre del host donde tenemos nuestra base de datos
     * @param base el nombre de la base de datos 
     * @param usuario con el que accedemos a el servidor donde esta nuestra base de datos
     * @param contraseña contraseña para el usuario
     * @return 
     */
        public Connection Conectar() {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://10.0.0.254/slagogonzalez", "slagogonzalez", "slagogonzalez");
                System.out.println("Conexion estalbecida");
                JOptionPane.showMessageDialog(null, "Conexion establecida");
            } catch (Exception conectar) {
                System.out.println(conectar.getMessage());
            }
            return conn;
        }
    }
/**
     * Insiere el/los dato/datos que deseemos en la tabla.
     * @param tabla marcamos cual es la tabla en la cual deseamos insertar los datos
     * @param nomColumna pasamos las columnas que posee dicha tabla
     * @param valores damos los valores a las columnas respetando el orden de como hayamos puesto las columnas anteriormente
     */
    public void Insertar(String tabla, String nomeTabla, String valores) {

        java.sql.PreparedStatement ps;
        try {
            ps = conn.prepareStatement("Insert into " + tabla + "(" + nomeTabla + ") values (" + valores + ")");
            ps.execute();
            System.out.println("Insercion realizada");

        } catch (Exception insertar) {
            System.out.println(insertar.getMessage());
        }
    }
/**
     * Muestra la información guardada en la base de datos, solo debemos pasarle el numero de columnas que posee la tabla de la base
     * @param columna 
     */
    public void mostrardatos(int columna) {
        String[] data = new String[columna];
        try {
            Statement stm = (Statement) conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                for (int x = 0; x < +data.length; x++) {
                    data[x] = rs.getString(x + 1);
                    System.out.println(data[x]);
                }
            }

        } catch (SQLException show) {
            System.out.println(show.getMessage());
        }
    }
    /**
     * Permite buscar información en la base de datos mediante consultas con select, solo necesita saber el primary key
     * @param parametro enviaremos aquí el parametro que deseemos seleccionar
     * @param nomTabla nombre de la tabla en la cual deseamos buscar el parametro
     * @param ID nombre de el campo que contenga la primary key
     * @param primaryKey primary key que posea el campo que deseemos buscar
     */
    public void buscar(String parametro,String nomTabla,String ID,String primaryKey){
        
        
            sql="Select "+parametro+" from "+nomTabla+" where "+ID+"='"+primaryKey+"'";
            mostrardatos(6);
        
    }  
    /**
       * Borra un registro de la tabla de la base de datos
       * @param nomTabla marcamos el nombre de la tabla de la cual deseamos eliminar 
       * @param primary_Key enviamos el nombre de la columna que sea la primary key
       * @param id enviamos la primary key del registro que deseemos borrar
       */
    public void delete(String nomeTabla, String primary_Key, int id) {

        try {
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("Delete from " + nomeTabla + " where " + primary_Key + "='" + id + "'");
            ps.executeUpdate();
            mostrardatos(6);
            System.out.println("Borrado exitoso");
        } catch (SQLException delete) {
            System.out.println(delete.getMessage());
        }
    }
/**
      * Cierra la conexion con la base de datos
      */
    public void CerrarConnexion() {
         try {
            conn.close();
        } catch (SQLException closeConnection) {
            System.out.println(closeConnection.getMessage());
        }
      }
      
}
