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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a1
 */
public class UserDB {
    
    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public UserDB(String dburl, String dbUser, String dbPassword) {
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
    public Connection getConnection() throws SQLException, IOException {
//        System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception ex){
            
        }
        return DriverManager.getConnection(dburl, dbUser, dbPassword);

    }
    
    public boolean isValidUser(String user, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM USERINFO WHERE username=? and password=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, user);
            pStmnt.setString(2, pwd);
            
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            
            if (rs.next()) {
                isValid = true;
            }
            
            return isValid;

        }catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isValid;
    }
    
    public void CreateUserInfoTable(){
        Statement stmnt = null;
        Connection cnnct = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS userInfo ("
                    + "id varchar(5) NOT NULL, "
                    + "username varchar(25) NOT NULL, "
                    + "password varchar(25) NOT NULL, "
                    + "PRIMARY KEY(id)"
                    + ")";

            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean addUserInfo(String id, String user, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO userInfo (ID, USERNAME, PASSWORD) VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            pStmnt.setString(2, user);
            pStmnt.setString(3, pwd);
            
            int rowCount = pStmnt.executeUpdate();
            
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        }catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public String getUserRole(){
        //...........
        return "student";
    }
}
