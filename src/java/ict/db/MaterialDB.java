/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hong
 */
public class MaterialDB {
     private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";
    public MaterialDB(String dburl, String dbUser, String dbPassword){
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
        public boolean createMaterial(int cid,String mateName,String mateDesc){
         boolean success = false;
            Connection cnt = null;
            PreparedStatement pre = null;
            
            
            try
        {
            cnt = getConnection();
            String preQueryStatement = "INSERT INTO material (cid,mateName,mateDesc) VALUES (?,?,?)";
            pre = cnt.prepareStatement(preQueryStatement);
            pre.setInt(1, cid);
            pre.setString(2, mateName);
            pre.setString(3, mateDesc);
            

            int rowCount = pre.executeUpdate();

            if (rowCount >= 1)
            {
                success = true;
            }
            pre.close();
            cnt.close();
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
            
            return success;
    }
}
