
package gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DAM
 */
public class Connexiobd {

    /**
     *
     * @return
     * @throws SQLException
     */
    public Connection conectar() throws SQLException{
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gimnasio", "root", "");
            System.out.println("conectado con exito");
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
