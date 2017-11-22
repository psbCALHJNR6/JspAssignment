/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hong
 */
public class QuizDB {
      private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public QuizDB(String dburl, String dbUser, String dbPassword)
    {
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
        public Connection getConnection() throws SQLException, IOException
    {
//        System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex)
        {

        }
        return DriverManager.getConnection(dburl, dbUser, dbPassword);

    }
       
     public void CreateUserTable()
    {
        Statement stmnt = null;
        Connection cnnct = null;

        try
        {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS user ("
                    + "id varchar(5) NOT NULL, "
                    + "username varchar(25) NOT NULL, "
                    + "password varchar(25) NOT NULL, "
                    + "role varchar(25) NOT NULL, "
                    + "email varchar(255) NOT NULL, "
                    + "PRIMARY KEY(id)"
                    + ")";

            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();

        }
        catch (SQLException ex)
        {
            while (ex != null)
            {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
